package com.algaworks.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.controller.validator.VendaValidator;
import com.algaworks.model.Cerveja;
import com.algaworks.model.Venda;
import com.algaworks.repository.Cervejas;
import com.algaworks.security.UsuarioSistema;
import com.algaworks.service.VendaService;
import com.algaworks.session.TabelaItemSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private TabelaItemSession tabelaItem;
	
	@Autowired
	private VendaValidator vendaValidator;
	
	@InitBinder
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(vendaValidator);
	}

	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/cadastro");
		
		if(ObjectUtils.isEmpty(venda.getUuid())) {
			venda.setUuid(UUID.randomUUID().toString());
		}
		
		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotalItens", tabelaItem.getValorTotal(venda.getUuid()));
		
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		venda.adicionarItens(tabelaItem.getItens(venda.getUuid()));
		venda.calcularValorTotal();
		
		vendaValidator.validate(venda, result);
		
		if(result.hasErrors()) {
			return nova(venda);
		}
		
		venda.setUsuario(usuarioSistema.getUsuario());
		
		vendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso");
		
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		Cerveja cerveja = cervejas.findById(codigoCerveja).orElse(null);
		tabelaItem.adicionarItem(uuid, cerveja, 1);
		
		return mvTabelaItemVenda(uuid);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Cerveja cerveja, Integer quantidade, String uuid) {
		tabelaItem.alterarQuantidadeItens(uuid, cerveja, quantidade);

		return mvTabelaItemVenda(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}") 
	public ModelAndView excluirItem(@PathVariable("codigoCerveja") Cerveja cerveja, @PathVariable String uuid) {
		tabelaItem.excluirItem(uuid, cerveja);
		
		return mvTabelaItemVenda(uuid);
	}

	private ModelAndView mvTabelaItemVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/tabela");
		mv.addObject("itens", tabelaItem.getItens(uuid));
		mv.addObject("valorTotal", tabelaItem.getValorTotal(uuid));
		
		return mv;
	}
}

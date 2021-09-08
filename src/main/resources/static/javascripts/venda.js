Brewer.Venda = (function() {
	
	function Venda(tabelaItem) {
		this.tabelaItem = tabelaItem;
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorFreteInput = $('#valorFrete');
		this.valorDescontoInput = $('#valorDesconto');
		
		this.valorTotalItens = 0;
		this.valorFrete = 0;
		this.valorDesconto = 0;
	}
	
	Venda.prototype.iniciar = function() {
		this.tabelaItem.on('tabela-item-atualizada', onTabelaItemAtualizada.bind(this));
		this.valorFreteInput.on('keyup', onValorFreteAlterado.bind(this));
		this.valorDescontoInput.on('keyup', onValorDescontoAlterado.bind(this));
		
		this.tabelaItem.on('tabela-item-atualizada', onValoresAlterados.bind(this));
		this.valorFreteInput.on('keyup', onValoresAlterados.bind(this));
		this.valorDescontoInput.on('keyup', onValoresAlterados.bind(this));
	}

	function onTabelaItemAtualizada(event, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
	}
	
	function onValorFreteAlterado(event) {
		this.valorFrete = Brewer.recuperarValor($(event.target).val());
	}
	
	function onValorDescontoAlterado(event) {
		this.valorDesconto = Brewer.recuperarValor($(event.target).val());
	}

	function onValoresAlterados() {
		var valorTotal = this.valorTotalItens + this.valorFrete - this.valorDesconto;
		this.valorTotalBox.html(Brewer.formatarMoeda(valorTotal));
	}

	return Venda;
	
}());

$(function() {
	
	var autocomplete = new Brewer.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItem = new Brewer.TabelaItem(autocomplete);
	tabelaItem.iniciar();
	
	var venda = new Brewer.Venda(tabelaItem);
	venda.iniciar();
	
});
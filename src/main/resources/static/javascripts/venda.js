Brewer.Venda = (function() {
	
	function Venda(tabelaItem) {
		this.tabelaItem = tabelaItem;
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorFreteInput = $('#valorFrete');
		this.valorDescontoInput = $('#valorDesconto');
		this.valorTotalBoxContainer = $('.js-valor-total-box-container');
		
		this.valorTotalItens = this.tabelaItem.valorTotal();
		this.valorFrete = this.valorFreteInput.data('valor');
		this.valorDesconto = this.valorDescontoInput.data('valor');
	}
	
	Venda.prototype.iniciar = function() {
		this.tabelaItem.on('tabela-item-atualizada', onTabelaItemAtualizada.bind(this));
		this.valorFreteInput.on('keyup', onValorFreteAlterado.bind(this));
		this.valorDescontoInput.on('keyup', onValorDescontoAlterado.bind(this));
		
		this.tabelaItem.on('tabela-item-atualizada', onValoresAlterados.bind(this));
		this.valorFreteInput.on('keyup', onValoresAlterados.bind(this));
		this.valorDescontoInput.on('keyup', onValoresAlterados.bind(this));
		
		onValoresAlterados.call(this);
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
		var valorTotal = numeral(this.valorTotalItens) + numeral(this.valorFrete) - numeral(this.valorDesconto);
		this.valorTotalBox.html(Brewer.formatarMoeda(valorTotal));
		
		this.valorTotalBoxContainer.toggleClass('negativo', valorTotal < 0);
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
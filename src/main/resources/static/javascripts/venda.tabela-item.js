Brewer.TabelaItem = (function() {
	
	function TabelaItem(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaCervejasContainer = $('.js-tabela-cervejas-container');
	}
	
	TabelaItem.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));	
	}
	
	function onItemSelecionado(event, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoCerveja: item.codigo
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaCervejasContainer.html(html);
		$('.js-tabela-cerveja-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
	}
	
	function onQuantidadeItemAlterado(event) {
		var input = $(event.target);
		var quantidade = input.val();
		var codigoCerveja = input.data('codigo-cerveja');
		
		var resposta = $.ajax({
			url: 'item/' + codigoCerveja,
			method: 'PUT',
			data: {
				quantidade: quantidade
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onDoubleClick(event) {
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(event) {
		var codigoCerveja = $(event.target).data('codigo-cerveja');
		var resposta = $.ajax({
			url: 'item/' + codigoCerveja,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	return TabelaItem;
	
}());

$(function() {
	
	var autocomplete = new Brewer.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItem = new Brewer.TabelaItem(autocomplete);
	tabelaItem.iniciar();
	
});
Brewer = Brewer || {};

Brewer.DialogoExcluir = (function() {
	
	function DialogoExcluir() {
		this.exclusaoBtn = $('.js-exclusao-btn');
		
		this.totalElementos = $('input[name=registrosPagina]').val();
    	this.paginaAtual = $('input[name=atual]');
    	this.ultimaPagina = $('input[name=ultima]').val();
	}
	
	DialogoExcluir.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
		
		if (window.location.search.indexOf('excluido') > -1) {
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		}
	}
	
	function onExcluirClicado(event) {
		event.preventDefault();
		var botaoClicado = $(event.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		
		swal({
			title: 'Tem certeza?',
			text: 'Excluir "' + objeto + '"? Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora!',
			closeOnConfirm: false
		}, onExcluirConfirmado.bind(this, url));
	}
	
	function onExcluirConfirmado(url) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this)
		});
	}
	
	var urlPaginacao = null;
	var urlFinal = null;
	
	function onExcluidoSucesso() {
	    var urlAtual = window.location.href;
	    var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
			
	    if (this.ultimaPagina = 'true' && this.totalElementos == '1') {
			var paginaAnterior = this.paginaAtual.val() - 1;
			urlPaginacao = 'cervejas?page=' + paginaAnterior.toString();
			urlFinal = urlPaginacao.indexOf('excluido') > -1 ? urlPaginacao : urlPaginacao + separador + 'excluido';
	    } else {
			urlFinal = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
	    }
			
	    window.location = urlFinal;
	}
	
	function onErroExcluir(e) {
		swal('Oops!', e.responseText, 'error');
	}
	
	return DialogoExcluir;
	
}());

$(function() {
	var dialogo = new Brewer.DialogoExcluir();
	dialogo.iniciar();
});

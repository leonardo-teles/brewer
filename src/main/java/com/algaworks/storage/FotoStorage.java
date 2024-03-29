package com.algaworks.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);

	public void salvar(String foto);

	public byte[] recuperarFoto(String nome);
	
	public byte[] recuperarThumbnail(String fotoCerveja);

	public void excluir(String foto);
}

package br.carloskafka.planetstarwars.dominio;

import java.util.List;

public class ResultadoEdicaoPlanetaDTO {
	private Planeta planeta;
	private List<String> erros;
	private boolean sucesso;
	
	public void efetuadoComSucesso(Planeta planeta) {
		this.planeta = planeta;
		marcarComoSucesso();
	}

	private void marcarComoSucesso() {
		sucesso = true;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	
	public List<String> getErros() {
		return erros;
	}

	public boolean sucesso() {
		return sucesso;
	}

	public Planeta getPlaneta() {
		return planeta;
	}
}
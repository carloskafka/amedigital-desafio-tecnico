package br.carloskafka.planetstarwars.dominio;

import java.util.List;

public class ResultadoConsultaPlanetaDTO {
	private List<Planeta> planetas;
	private List<String> erros;
	private boolean sucesso;
	
	public ResultadoConsultaPlanetaDTO() {
	}

	public ResultadoConsultaPlanetaDTO(List<Planeta> planetas) {
		this.planetas = planetas;
	}

	public void efetuadoComSucesso(List<Planeta> planetas) {
		this.planetas = planetas;
		marcarComoSucesso();
	}
	
	public void efetuadoComSucesso(Planeta planeta) {
		this.planetas.add(planeta);
		marcarComoSucesso();
	}

	private void marcarComoSucesso() {
		sucesso = true;
	}

	public void adicionarErro(String erro) {
		this.erros.add(erro);
	}

	public boolean sucesso() {
		return sucesso;
	}

	public List<Planeta> getPlanetas() {
		return planetas;
	}
}

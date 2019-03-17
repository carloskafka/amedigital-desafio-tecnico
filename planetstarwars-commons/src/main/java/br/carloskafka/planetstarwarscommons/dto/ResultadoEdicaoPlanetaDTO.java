package br.carloskafka.planetstarwarscommons.dto;

import java.util.List;

public class ResultadoEdicaoPlanetaDTO {
	private PlanetaDTO planetaDto;
	private List<String> erros;
	private boolean sucesso;

	public void efetuadoComSucesso(PlanetaDTO planetaDto) {
		setPlanetaDto(planetaDto);
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

	public boolean isSucesso() {
		return sucesso;
	}

	public void setPlanetaDto(PlanetaDTO planetaDto) {
		this.planetaDto = planetaDto;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public PlanetaDTO getPlanetaDto() {
		return planetaDto;
	}
}
package br.carloskafka.planetstarwarscommons.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultadoConsultaPlanetaDTO {
	@JsonProperty("results")
	private List<PlanetaDTO> planetasDto;
	private List<String> erros;
	@JsonProperty("next")
	private String urlProximaPagina;
	@JsonProperty("count")
	private Integer quantidadeTotalPlanetasASerRetornado;
	private boolean sucesso;

	public ResultadoConsultaPlanetaDTO() {
		this.erros = new ArrayList<>();
		this.planetasDto = new ArrayList<>();
	}

	public ResultadoConsultaPlanetaDTO(List<PlanetaDTO> planetasDto) {
		efetuadoComSucesso(planetasDto);
	}

	public void efetuadoComSucesso(List<PlanetaDTO> planetasDto) {
		this.planetasDto = planetasDto;
		marcarComoSucesso();
	}

	public void efetuadoComSucesso(PlanetaDTO planetaDto) {
		this.planetasDto.add(planetaDto);
		marcarComoSucesso();
	}

	private void marcarComoSucesso() {
		setSucesso(true);
	}

	public void adicionarErro(String erro) {
		this.erros.add(erro);
	}

	public List<PlanetaDTO> getPlanetasDto() {
		return planetasDto;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public void setPlanetasDto(List<PlanetaDTO> planetasDto) {
		this.planetasDto = planetasDto;
	}

	public String getUrlProximaPagina() {
		return urlProximaPagina;
	}

	public void setUrlProximaPagina(String urlProximaPagina) {
		this.urlProximaPagina = urlProximaPagina;
	}

	public boolean possuiProximaPagina() {
		return urlProximaPagina != null && !urlProximaPagina.isEmpty();
	}

	public Integer getQuantidadeTotalPlanetasASerRetornado() {
		return quantidadeTotalPlanetasASerRetornado;
	}

	public void setQuantidadeTotalPlanetasASerRetornado(Integer quantidadeTotalPlanetasASerRetornado) {
		this.quantidadeTotalPlanetasASerRetornado = quantidadeTotalPlanetasASerRetornado;
	}

}

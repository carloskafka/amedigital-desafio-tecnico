package br.carloskafka.planetstarwarscommons.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetaDTO {
	private Long id;
	private String nome;
	private String clima;
	private String terreno;
	private List<String> filmesAparecidos;
	private int quantidadeDeAparicoesEmFilmes;

	public PlanetaDTO() {
		filmesAparecidos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonProperty("climate")
	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	@JsonProperty("terrain")
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	@JsonProperty("films")
	public List<String> getFilmesAparecidos() {
		return filmesAparecidos;
	}

	public void setFilmesAparecidos(List<String> filmesAparecidos) {
		this.filmesAparecidos = filmesAparecidos;
		setQuantidadeDeAparicoesEmFilmes(filmesAparecidos.size());
	}

	public int getQuantidadeDeAparicoesEmFilmes() {
		return quantidadeDeAparicoesEmFilmes;
	}

	public void setQuantidadeDeAparicoesEmFilmes(int quantidadeDeAparicoesEmFilmes) {
		this.quantidadeDeAparicoesEmFilmes = quantidadeDeAparicoesEmFilmes;
	}

	@Override
	public String toString() {
		return "PlanetaDTO [id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno
				+ ", filmesAparecidos=" + filmesAparecidos + ", quantidadeDeAparicoesEmFilmes="
				+ getQuantidadeDeAparicoesEmFilmes() + "]";
	}

}

package br.carloskafka.planetstarwars.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inscricao")
public class Planeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "clima")
	private String clima;
	@Column(name = "terreno")
	private String terreno;
	@Column(name = "quantidade_de_aparicoes_em_filmes")
	private String quantidadeDeAparicoesEmFilmes;
	private boolean validado;
	private List<String> erros;

	public Planeta() {
		erros = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public String getQuantidadeDeAparicoesEmFilmes() {
		return quantidadeDeAparicoesEmFilmes;
	}

	public void setQuantidadeDeAparicoesEmFilmes(String quantidadeDeAparicoesEmFilmes) {
		this.quantidadeDeAparicoesEmFilmes = quantidadeDeAparicoesEmFilmes;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public void adicionarErro(String erro) {
		getErros().add(erro);
	}

	public void validarObrigatoriedadeDeDados() {
		if (nome == null || nome.isEmpty()) {
			adicionarErro("Informe um nome válido.");
		}
		if (clima == null || clima.isEmpty()) {
			adicionarErro("Informe um clima válido.");
		}
		if (terreno == null || terreno.isEmpty()) {
			adicionarErro("Informe um terreno válido.");
		}
	}

	@Override
	public String toString() {
		return "Planeta [id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno
				+ ", quantidadeDeAparicoesEmFilmes=" + quantidadeDeAparicoesEmFilmes + ", validado=" + validado
				+ ", erros=" + erros + "]";
	}

}
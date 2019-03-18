package br.carloskafka.planetstarwars.dominio;

import org.junit.Assert;
import org.testng.annotations.Test;

import br.carloskafka.planetstarwarsserver.dominio.Planeta;

public class TestesPlaneta {

	@Test
	public void dado_um_planeta_quando_for_criado_um_novo_planeta_entao_retorna_um_novo_planeta() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";
		int quantidadeDeAparicoesEmFilmes = 5;
		
		Planeta planeta = new Planeta();

		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setQuantidadeDeAparicoesEmFilmes(quantidadeDeAparicoesEmFilmes);
		
		Assert.assertEquals(planeta.getNome(), nome);
		Assert.assertEquals(planeta.getClima(), clima);
		Assert.assertEquals(planeta.getTerreno(), terreno);
		Assert.assertEquals(planeta.getQuantidadeDeAparicoesEmFilmes(), quantidadeDeAparicoesEmFilmes);
	}
	
	@Test
	public void dado_um_planeta_quando_for_validar_um_novo_planeta_entao_retorna_um_novo_planeta_valido() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";
		int quantidadeDeAparicoesEmFilmes = 5;
		
		Planeta planeta = new Planeta();

		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setQuantidadeDeAparicoesEmFilmes(quantidadeDeAparicoesEmFilmes);
		
		planeta.validarObrigatoriedadeDeDados();
		
		Assert.assertTrue(planeta.isValidado());
		Assert.assertEquals(planeta.getErros().size(), 0);
	}
	
	@Test
	public void dado_um_planeta_quando_for_validar_um_novo_planeta_invalido_entao_retorna_um_novo_planeta_invalido() {
		String nome = "";
		String clima = "";
		String terreno = "";
		int quantidadeDeAparicoesEmFilmes = 0;
		
		Planeta planeta = new Planeta();

		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setQuantidadeDeAparicoesEmFilmes(quantidadeDeAparicoesEmFilmes);
		
		planeta.validarObrigatoriedadeDeDados();
		
		Assert.assertFalse(planeta.isValidado());
		Assert.assertEquals(planeta.getErros().size(), 3);
	}
	
	@Test
	public void dado_um_planeta_quando_for_validar_se_um_planeta_e_igual_ao_outro_planeta_entao_retorna_verdadeiro() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";
		int quantidadeDeAparicoesEmFilmes = 5;
		
		Planeta planeta = new Planeta();

		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setQuantidadeDeAparicoesEmFilmes(quantidadeDeAparicoesEmFilmes);
		
		Planeta segundoPlaneta = new Planeta();

		segundoPlaneta.setNome(nome);
		segundoPlaneta.setClima(clima);
		segundoPlaneta.setTerreno(terreno);
		segundoPlaneta.setQuantidadeDeAparicoesEmFilmes(quantidadeDeAparicoesEmFilmes);
		
		Assert.assertTrue(planeta.equals(segundoPlaneta));
	}
}

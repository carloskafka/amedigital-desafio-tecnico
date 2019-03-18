package br.carloskafka.planetstarwars.repositorios;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import br.carloskafka.planetstarwarsserver.dominio.Planeta;
import br.carloskafka.planetstarwarsserver.repositorios.RepositorioPlaneta;

@SpringBootTest
public class TestesRepositorioPlaneta {

	@Autowired
	private RepositorioPlaneta repositorioPlaneta;
	
	@Test
	public void dado_um_repositorio_planeta_quando_adicionar_um_novo_planeta_entao_retorna_um_planeta_salvo() {
		Planeta planetaASalvar = new Planeta();
		
		Planeta planetaSalvo = repositorioPlaneta.adicionarPlaneta(planetaASalvar);
		Assert.assertFalse(planetaSalvo.getId().toString().isEmpty());
	}
	
	@Test
	public void dado_um_repositorio_planeta_quando_buscar_um_planeta_por_id_entao_retorna_o_planeta_obtido() {
		Long id = 1L;
		
		Planeta planetaObtido = repositorioPlaneta.buscarPorId(id);
		Assert.assertEquals(planetaObtido.getId(), id);
	}
	
	@Test
	public void dado_um_repositorio_planeta_quando_buscar_um_planeta_por_nome_entao_retorna_o_planeta_obtido() {
		String nome = "";
		
		Planeta planetaObtido = repositorioPlaneta.buscarPorNome(nome);
		
		Assert.assertEquals(planetaObtido.getNome(), nome);
	}
	@Test
	public void dado_um_repositorio_planeta_quando_listar_planetas_entao_retorna_os_planetas_obtidos() {
		List<Planeta> planetasObtidos = repositorioPlaneta.listarPlanetas();
		
		Assert.assertEquals(planetasObtidos.size(), 0);
	}
}

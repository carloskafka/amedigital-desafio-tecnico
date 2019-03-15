package br.carloskafka.planetstarwars;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.carloskafka.planetstarwars.dominio.ResultadoConsultaPlanetaDTO;

public class TestesControladorPlaneta {

	private TestRestTemplate restTemplate;

	public TestesControladorPlaneta() {
		restTemplate = new TestRestTemplate();
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_entao_retorna_a_listagem_de_planetas() {
		int quantidadeDePlanetasEsperado = 5;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity("http://localhost:8080/planetas", ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetas().size(), quantidadeDePlanetasEsperado);
	}

}

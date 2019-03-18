package br.carloskafka.planetstarwars.controladores;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import br.carloskafka.planetstarwars.TestesIntegracaoAbstrato;
import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoEdicaoPlanetaDTO;

@SpringBootTest
public class TestesControladorPlaneta extends TestesIntegracaoAbstrato{
	private TestRestTemplate restTemplate;
	
	public TestesControladorPlaneta() {
		super("DadosTestesControladorPlaneta.xml");
		restTemplate = new TestRestTemplate();
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_entao_retorna_a_listagem_de_planetas() {
		int quantidadeDePlanetasEsperado = 3;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS, ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_adicionar_um_novo_planeta_entao_retorna_o_novo_planeta() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = restTemplate
				.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, new HttpEntity<PlanetaDTO>(novoPlanetaDto), ResultadoEdicaoPlanetaDTO.class).getBody();

		Assert.assertTrue(resultadoEdicaoPlanetaDTO.isSucesso());
		Assert.assertFalse(resultadoEdicaoPlanetaDTO.getPlanetaDto().getId().toString().isEmpty());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getNome(), novoPlanetaDto.getNome());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getClima(), novoPlanetaDto.getClima());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getTerreno(), novoPlanetaDto.getTerreno());		
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_adicionar_um_planeta_invalido_entao_retorna_erros() {
		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome("");
		novoPlanetaDto.setClima("");
		novoPlanetaDto.setTerreno("");
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = restTemplate
				.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, new HttpEntity<PlanetaDTO>(novoPlanetaDto), ResultadoEdicaoPlanetaDTO.class).getBody();

		Assert.assertFalse(resultadoEdicaoPlanetaDTO.isSucesso());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getErros().size(), 3);
	}

}

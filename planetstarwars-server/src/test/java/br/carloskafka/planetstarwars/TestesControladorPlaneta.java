package br.carloskafka.planetstarwars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.planetstarwarsserver.utilitario.HttpUtils;
import br.carloskafka.planetstarwarsserver.utilitario.Registro;

@SpringBootTest
public class TestesControladorPlaneta {

	@Autowired
	private TestRestTemplate restTemplate;

	public TestesControladorPlaneta() {
		Registro.inicializarContexto();
		restTemplate = new TestRestTemplate();
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_entao_retorna_a_listagem_de_planetas() {
		int quantidadeDePlanetasEsperado = 1;

		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);

		restTemplate
				.postForEntity("http://localhost:8080/planetas/", HttpUtils.criar(novoPlanetaDto), 
						ResultadoEdicaoPlanetaDTO.class);

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity("http://localhost:8080/planetas/", ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}

}

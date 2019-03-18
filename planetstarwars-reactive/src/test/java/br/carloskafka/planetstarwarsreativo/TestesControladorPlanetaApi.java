package br.carloskafka.planetstarwarsreativo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestesControladorPlanetaApi {

	@Autowired
	private WebTestClient client;

	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_planetas_na_api_reativa_entao_retorna_varios_resultados() {
		int quantidadeResultados = 7;
		
		Flux<ResultadoConsultaPlanetaDTO> resultadoConsultaPlanetaApiFlux = client
			.get().uri(ContratoRest.URL_PLANETA_API_REATIVA_PLANETAS)
			.accept(MediaType.TEXT_EVENT_STREAM)
			.exchange()
			.expectStatus().isOk()
			.returnResult(ResultadoConsultaPlanetaDTO.class)
			.getResponseBody();
		
		StepVerifier.create(resultadoConsultaPlanetaApiFlux)
		.expectNextCount(quantidadeResultados)
		.thenCancel()
		.verify();
	}
}

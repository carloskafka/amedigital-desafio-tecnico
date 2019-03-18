package br.carloskafka.planetstarwarsreativo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarsreativo.servicos.ServicoPlanetaAPI;
import reactor.core.publisher.Flux;

@RestController
public class ControladorPlanetaApi {
	
	@Autowired
	private ServicoPlanetaAPI servicoPlanetaApi;

	@GetMapping(path = ContratoRest.URL_PLANETA_API_REATIVA_PLANETAS, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ResultadoConsultaPlanetaDTO> executarConsultaPlanetaAPI() {
		return servicoPlanetaApi.executarConsultaPlanetaApi();
	}

}
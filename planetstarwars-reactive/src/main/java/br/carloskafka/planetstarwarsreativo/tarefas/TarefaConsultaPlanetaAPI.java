package br.carloskafka.planetstarwarsreativo.tarefas;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import reactor.core.publisher.FluxSink;

public class TarefaConsultaPlanetaAPI implements Runnable {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

	private static final String URL_BASE_HTTPS_SWAPI_CO_API_PLANETS = "https://swapi.co/api/planets/";
	
    private FluxSink<ResultadoConsultaPlanetaDTO> resultadosConsultaPlanetaDTO;

    public TarefaConsultaPlanetaAPI(FluxSink<ResultadoConsultaPlanetaDTO> sink) {
        this.resultadosConsultaPlanetaDTO = sink;
    }

    public void run() {
        ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDTO = new ResultadoConsultaPlanetaDTO();
        
        do {
        	resultadoConsultaPlanetaDTO = WebClient.create()
        	 .get()
        	 .uri(resultadoConsultaPlanetaDTO.possuiProximaPagina() ? 
        			 resultadoConsultaPlanetaDTO.getUrlProximaPagina() : URL_BASE_HTTPS_SWAPI_CO_API_PLANETS)
        	 .header(HttpHeaders.USER_AGENT, USER_AGENT)
        	 .accept(MediaType.APPLICATION_JSON)
        	 .exchange()	            	 
        	 .flatMap(resposta -> resposta.bodyToMono(ResultadoConsultaPlanetaDTO.class))
        	 .block();
        	 
        	 resultadosConsultaPlanetaDTO.next(resultadoConsultaPlanetaDTO);
        } while (resultadoConsultaPlanetaDTO.possuiProximaPagina());
        
        resultadosConsultaPlanetaDTO.complete();
    }
}

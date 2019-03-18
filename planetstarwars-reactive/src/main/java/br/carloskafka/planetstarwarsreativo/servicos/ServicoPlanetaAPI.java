package br.carloskafka.planetstarwarsreativo.servicos;

import org.springframework.stereotype.Component;

import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarsreativo.tarefas.TarefaConsultaPlanetaAPI;
import reactor.core.publisher.Flux;

@Component
public class ServicoPlanetaAPI {

	public Flux<ResultadoConsultaPlanetaDTO> executarConsultaPlanetaApi() {
		Flux<ResultadoConsultaPlanetaDTO> resultadosConsultaPlanetaDtos = Flux.create(resultadosConsultaPlanetaDto -> {
			new Thread(new TarefaConsultaPlanetaAPI(resultadosConsultaPlanetaDto)).start();
		});
		
		return resultadosConsultaPlanetaDtos;
	}
}

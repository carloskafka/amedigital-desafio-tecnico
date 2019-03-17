package br.carloskafka.planetstarwarsserver.servicos.planeta;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarsserver.dominio.Planeta;

@Component
public class ServicoListagemPlanetaApiStarWars {
	private static final Logger logger = LoggerFactory.getLogger(ServicoListagemPlanetaApiStarWars.class);

	private static List<Planeta> planetas = new ArrayList<>();
	
	public List<Planeta> listarPlanetas() {
		WebClient.create()
				.get()
				.uri(ContratoRest.URL_PLANETA_API_REATIVA_LISTAGEM_PLANETAS)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.flatMapMany(resposta -> resposta.bodyToFlux(ResultadoConsultaPlanetaDTO.class))
				.subscribe((resultadoConsultaPlanetaDTO) -> {
					for (PlanetaDTO planetaDto : resultadoConsultaPlanetaDTO.getPlanetasDto()) {
						Planeta planeta = new Planeta();

						planeta.setNome(planetaDto.getNome());
						planeta.setClima(planetaDto.getClima());
						planeta.setTerreno(planetaDto.getTerreno());
						planeta.setQuantidadeDeAparicoesEmFilmes(planetaDto.getQuantidadeDeAparicoesEmFilmes());

						if (!planetas.contains(planeta)) {
							planetas.add(planeta);
						}
					}
				});
		
		return planetas;
	}

	public Planeta buscarPlanetaPorNome(String nome) {
		Planeta planetaObtido = planetas.stream().filter(planeta -> planeta.getNome().equals(nome)).findFirst()
				.orElse(new Planeta());

		planetaObtido.validarObrigatoriedadeDeDados();

		if (!planetaObtido.isValidado()) {
			logger.error("Nenhum planeta encontrado para o nome " + nome);
		}

		return planetaObtido;
	}

}
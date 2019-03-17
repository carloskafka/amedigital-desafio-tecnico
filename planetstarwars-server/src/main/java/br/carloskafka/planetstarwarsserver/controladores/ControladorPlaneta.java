package br.carloskafka.planetstarwarsserver.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.planetstarwarsserver.fachadas.FachadaPlaneta;

@RestController
public class ControladorPlaneta {

	@Autowired
	private FachadaPlaneta fachadaPlaneta;

	@PostMapping(ContratoRest.URL_PLANETAS)
	public ResultadoEdicaoPlanetaDTO adicionarPlaneta(@RequestBody PlanetaDTO planetaDto) {
		return fachadaPlaneta.adicionarPlaneta(planetaDto);
	}

	@GetMapping(ContratoRest.URL_PLANETAS)
	public ResultadoConsultaPlanetaDTO listarPlanetas() {
		return fachadaPlaneta.listarPlanetas();
	}

	@GetMapping(ContratoRest.URL_PLANETAS_API)
	public ResultadoConsultaPlanetaDTO listarPlanetasApi() {
		return fachadaPlaneta.listarPlanetasApi();
	}

	@GetMapping(ContratoRest.URL_PLANETAS_POR_NOME)
	public ResultadoConsultaPlanetaDTO buscarPorNome(@PathVariable(ContratoRest.PARAMETRO_NOME) String nome) {
		return fachadaPlaneta.buscarPorNome(nome);
	}

	@GetMapping(ContratoRest.URL_PLANETAS_POR_ID)
	public ResultadoConsultaPlanetaDTO buscarPorId(@PathVariable(ContratoRest.PARAMETRO_ID) Long id) {
		return fachadaPlaneta.buscarPorId(id);
	}
}

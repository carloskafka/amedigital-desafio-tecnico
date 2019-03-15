package br.carloskafka.planetstarwars.fachadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.carloskafka.planetstarwars.dominio.Planeta;
import br.carloskafka.planetstarwars.dominio.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwars.dominio.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.planetstarwars.repositorios.planeta.RepositorioPlaneta;
import br.carloskafka.planetstarwars.servicos.planeta.ServicoListagemPlanetaApiStarWars;

@Component
public class FachadaPlaneta {
	@Autowired
	private RepositorioPlaneta repositorioPlaneta;
	@Autowired
	private ServicoListagemPlanetaApiStarWars servicoListagemPlanetaApiStarWars;

	public ResultadoEdicaoPlanetaDTO adicionarPlaneta(Planeta planeta) {
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = new ResultadoEdicaoPlanetaDTO();
		
		planeta.validarObrigatoriedadeDeDados();

		if (planeta.isValidado()) {
			Planeta planetaAdicionado = repositorioPlaneta.adicionarPlaneta(planeta);
			resultadoEdicaoPlanetaDTO.efetuadoComSucesso(planetaAdicionado);
		} else {
			resultadoEdicaoPlanetaDTO.setErros(planeta.getErros());
		}

		return resultadoEdicaoPlanetaDTO;
	}

	public ResultadoConsultaPlanetaDTO listarPlanetas() {
		return new ResultadoConsultaPlanetaDTO(repositorioPlaneta.listarPlanetas());
	}

	public ResultadoConsultaPlanetaDTO buscarPorNome(String nome) {
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();

		Planeta planetaEncontrado = repositorioPlaneta.buscarPorNome(nome);

		if (planetaEncontrado != null) {
			resultadoConsultaPlanetaDto.efetuadoComSucesso(planetaEncontrado);
		} else {
			resultadoConsultaPlanetaDto.adicionarErro("Nenhum planeta foi encontrado com esse nome.");
		}

		return resultadoConsultaPlanetaDto;
	}

	public ResultadoConsultaPlanetaDTO buscarPorId(Long id) {
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();

		Planeta planetaEncontrado = repositorioPlaneta.buscarPorId(id);

		if (planetaEncontrado != null) {
			resultadoConsultaPlanetaDto.efetuadoComSucesso(planetaEncontrado);
		} else {
			resultadoConsultaPlanetaDto.adicionarErro("Nenhum planeta foi encontrado com esse id.");
		}

		return resultadoConsultaPlanetaDto;
	}
	
	public ResultadoConsultaPlanetaDTO listarPlanetasApiStarWars() {
		return new ResultadoConsultaPlanetaDTO(servicoListagemPlanetaApiStarWars.listarPlanetas());
	}

}

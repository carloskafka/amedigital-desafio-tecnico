package br.carloskafka.planetstarwarsserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarsserver.dominio.Planeta;

public class FabricaPlaneta {

	public static PlanetaDTO converterParaDTO(Planeta planeta) {
		PlanetaDTO planetaDto = null;

		if (planeta != null) {
			planetaDto = new PlanetaDTO();

			planetaDto.setId(planeta.getId());
			planetaDto.setNome(planeta.getNome());
			planetaDto.setClima(planeta.getClima());
			planetaDto.setTerreno(planeta.getTerreno());
			planetaDto.setQuantidadeDeAparicoesEmFilmes(planeta.getQuantidadeDeAparicoesEmFilmes());
		}
		return planetaDto;
	}

	public static List<PlanetaDTO> converterParaDTO(List<Planeta> planetas) {
		List<PlanetaDTO> planetasDto = new ArrayList<>();

		for (Planeta planeta : planetas) {
			planetasDto.add(converterParaDTO(planeta));
		}

		return planetasDto;
	}

	public static Planeta converterParaDominio(PlanetaDTO planetaDto) {
		Planeta planeta = null;

		if (planetaDto != null) {
			planeta = new Planeta();

			planeta.setId(planetaDto.getId());
			planeta.setNome(planetaDto.getNome());
			planeta.setClima(planetaDto.getClima());
			planeta.setTerreno(planetaDto.getTerreno());
			planeta.setQuantidadeDeAparicoesEmFilmes(planetaDto.getFilmesAparecidos().size());
		}
		return planeta;
	}

}

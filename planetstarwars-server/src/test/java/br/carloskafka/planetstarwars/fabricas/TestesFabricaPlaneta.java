package br.carloskafka.planetstarwars.fabricas;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarsserver.dominio.Planeta;
import br.carloskafka.planetstarwarsserver.fabricas.FabricaPlaneta;

public class TestesFabricaPlaneta {

	@Test
	public void dado_uma_fabrica_planeta_quando_converter_varios_planetas_dominio_para_varios_planetas_dto_entao_retorna_varios_planeta_dto() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";
		
		Planeta planeta = new Planeta();

		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setQuantidadeDeAparicoesEmFilmes(5);
		
		List<Planeta> planetas = Arrays.asList(planeta);
		
		List<PlanetaDTO> planetasDto = FabricaPlaneta.converterParaDTO(planetas);
		
		PlanetaDTO planetaDtoObtido = planetasDto.get(0);
		
		Assert.assertEquals(planetasDto.size(), 1);
		Assert.assertEquals(planeta.getNome(), planetaDtoObtido.getNome());
		Assert.assertEquals(planeta.getClima(), planetaDtoObtido.getClima());
		Assert.assertEquals(planeta.getTerreno(), planetaDtoObtido.getTerreno());
	}
	
	@Test
	public void dado_uma_fabrica_planeta_quando_converter_um_planeta_dominio_para_planeta_dto_entao_retorna_um_planeta_dto() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";
		
		Planeta planeta = new Planeta();

		planeta.setNome(nome);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);
		planeta.setQuantidadeDeAparicoesEmFilmes(5);
		
		PlanetaDTO planetaDtoObtido = FabricaPlaneta.converterParaDTO(planeta);
		
		Assert.assertEquals(planeta.getNome(), planetaDtoObtido.getNome());
		Assert.assertEquals(planeta.getClima(), planetaDtoObtido.getClima());
		Assert.assertEquals(planeta.getTerreno(), planetaDtoObtido.getTerreno());
	}
	
	@Test
	public void dado_uma_fabrica_planeta_quando_converter_um_planeta_dto_para_planeta_dominio_entao_retorna_um_planeta_dominio() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";
		
		PlanetaDTO planetaDto = new PlanetaDTO();

		planetaDto.setNome(nome);
		planetaDto.setClima(clima);
		planetaDto.setTerreno(terreno);
		
		List<String> filmesAparecidos = Arrays.asList("https://swapi.co/api/films/5/", 
		        "https://swapi.co/api/films/4/", 
		        "https://swapi.co/api/films/6/", 
		        "https://swapi.co/api/films/3/", 
		        "https://swapi.co/api/films/1/");
		
		planetaDto.setFilmesAparecidos(filmesAparecidos);
		
		Planeta planetaObtido = FabricaPlaneta.converterParaDominio(planetaDto);
		
		Assert.assertEquals(planetaObtido.getNome(), planetaDto.getNome());
		Assert.assertEquals(planetaObtido.getClima(), planetaDto.getClima());
		Assert.assertEquals(planetaObtido.getTerreno(), planetaDto.getTerreno());
		Assert.assertEquals(planetaObtido.getQuantidadeDeAparicoesEmFilmes(), planetaDto.getQuantidadeDeAparicoesEmFilmes());
	}
}

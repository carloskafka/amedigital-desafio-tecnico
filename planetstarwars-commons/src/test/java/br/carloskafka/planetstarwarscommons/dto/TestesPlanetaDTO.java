package br.carloskafka.planetstarwarscommons.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestesPlanetaDTO {
	@Test
	public void dado_um_novo_planetadto_quando_for_criado_entao_retorna_um_novo() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		
		List<String> filmesAparecidos = Arrays.asList("https://swapi.co/api/films/5/", 
		        "https://swapi.co/api/films/4/", 
		        "https://swapi.co/api/films/6/", 
		        "https://swapi.co/api/films/3/", 
		        "https://swapi.co/api/films/1/");
		
		novoPlanetaDto.setFilmesAparecidos(filmesAparecidos);
		
		Assert.assertNull(novoPlanetaDto.getId());
		Assert.assertEquals(novoPlanetaDto.getNome(), nome);
		Assert.assertEquals(novoPlanetaDto.getClima(), clima);
		Assert.assertEquals(novoPlanetaDto.getTerreno(), terreno);
		Assert.assertEquals(novoPlanetaDto.getFilmesAparecidos().size(), 5);
		Assert.assertEquals(novoPlanetaDto.getQuantidadeDeAparicoesEmFilmes(), 5);
	}
	
	@Test
	public void dado_um_novo_planetadto_quando_for_criado_invalido_entao_retorna_invalido() {
		String nome = "";
		String clima = "";
		String terreno = "";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		novoPlanetaDto.setFilmesAparecidos(Arrays.asList());
		
		Assert.assertNull(novoPlanetaDto.getId());
		Assert.assertEquals(novoPlanetaDto.getNome(), nome);
		Assert.assertEquals(novoPlanetaDto.getClima(), clima);
		Assert.assertEquals(novoPlanetaDto.getTerreno(), terreno);
		Assert.assertEquals(novoPlanetaDto.getFilmesAparecidos().size(), 0);
		Assert.assertEquals(novoPlanetaDto.getQuantidadeDeAparicoesEmFilmes(), 0);
	}
}

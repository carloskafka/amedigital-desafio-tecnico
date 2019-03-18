package br.carloskafka.planetstarwarscommons.dto;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TestesResultadoConsultaPlanetaDTO {
	@Test
	public void dado_um_novo_resultado_consulta_planeta_quando_for_criado_entao_retorna_um_novo() {
		String urlProximaPagina = "https://swapi.co/api/planets/?page=2";
		
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();
		
		resultadoConsultaPlanetaDto.efetuadoComSucesso(Arrays.asList(novoPlanetaDto));
		resultadoConsultaPlanetaDto.setQuantidadeTotalPlanetasASerRetornado(1);
		resultadoConsultaPlanetaDto.setUrlProximaPagina(urlProximaPagina);
		
		Assert.assertTrue(resultadoConsultaPlanetaDto.isSucesso());
		Assert.assertEquals(resultadoConsultaPlanetaDto.getQuantidadeTotalPlanetasASerRetornado().intValue(), 1);
		Assert.assertEquals(resultadoConsultaPlanetaDto.getUrlProximaPagina(), urlProximaPagina);
		Assert.assertEquals(resultadoConsultaPlanetaDto.getPlanetasDto().size(), 1);
	}
	
	@Test
	public void dado_um_novo_resultado_consulta_planeta_quando_for_criado_invalido_entao_retorna_invalido() {
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();
		
		resultadoConsultaPlanetaDto.setQuantidadeTotalPlanetasASerRetornado(0);
		resultadoConsultaPlanetaDto.setUrlProximaPagina(null);
		
		Assert.assertFalse(resultadoConsultaPlanetaDto.isSucesso());
		Assert.assertEquals(resultadoConsultaPlanetaDto.getPlanetasDto().size(), 0);
		Assert.assertFalse(resultadoConsultaPlanetaDto.possuiProximaPagina());
	}
}

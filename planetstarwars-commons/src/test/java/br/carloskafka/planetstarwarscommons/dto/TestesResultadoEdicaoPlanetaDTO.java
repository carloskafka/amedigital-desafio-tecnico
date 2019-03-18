package br.carloskafka.planetstarwarscommons.dto;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestesResultadoEdicaoPlanetaDTO {
	
	@Test
	public void dado_um_novo_resultado_edicao_planeta_quando_for_criado_entao_retorna_um_novo() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDto = new ResultadoEdicaoPlanetaDTO();
		
		resultadoEdicaoPlanetaDto.efetuadoComSucesso(novoPlanetaDto);
		
		Assert.assertTrue(resultadoEdicaoPlanetaDto.isSucesso());
		Assert.assertNotNull(resultadoEdicaoPlanetaDto.getPlanetaDto());
	}
	
	@Test
	public void dado_um_novo_resultado_edicao_planeta_quando_for_criado_invalido_entao_retorna_invalido() {
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDto = new ResultadoEdicaoPlanetaDTO();
		
		Assert.assertFalse(resultadoEdicaoPlanetaDto.isSucesso());
		Assert.assertNull(resultadoEdicaoPlanetaDto.getPlanetaDto());
	}
}
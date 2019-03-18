package br.carloskafka.planetstarwars.utilitario;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;

public class JsonUtils {

	public static String criarJson(ResultadoConsultaPlanetaDTO objeto) {
		String json = "";

		try {
			json = new ObjectMapper().writeValueAsString(objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
}

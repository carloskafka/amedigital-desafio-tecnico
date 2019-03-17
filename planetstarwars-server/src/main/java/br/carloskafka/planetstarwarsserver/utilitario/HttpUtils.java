package br.carloskafka.planetstarwarsserver.utilitario;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtils {

	public static HttpEntity<String> criarCabecalho() {
		HttpHeaders headers = criarCabecalhoJson();
		
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}

	public static HttpHeaders criarCabecalhoJson() {
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return headers;
	}
	
	public static <T> HttpEntity<T> criar(T objeto) {
		return new HttpEntity<>(objeto, criarCabecalhoJson());
	}
}

package br.carloskafka.planetstarwarscommons.contrato;

public class ContratoRest {
	public static final String PARAMETRO_ID = "id";
	public static final String PARAMETRO_NOME= "nome";
	
	public static final String URL_PLANETAS = "/planetas/";
	public static final String URL_PLANETAS_API = URL_PLANETAS + "api/";
	public static final String URL_PLANETAS_POR_NOME = "/planetas/nome/{nome}";
	public static final String URL_PLANETAS_POR_ID = "/planetas/id/{id}";
	
	public static final String URL_PLANETA_API_REATIVA_PLANETAS = "/planetas/";
	public static final String URL_PLANETA_API_REATIVA_LISTAGEM_PLANETAS = "http://localhost:9090/" + URL_PLANETA_API_REATIVA_PLANETAS;
}

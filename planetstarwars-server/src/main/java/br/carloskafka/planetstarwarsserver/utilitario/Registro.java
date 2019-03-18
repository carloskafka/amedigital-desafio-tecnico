package br.carloskafka.planetstarwarsserver.utilitario;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.carloskafka.planetstarwarsserver.AplicacaoPlanetaStarWars;

public class Registro {
	private static ApplicationContext contexto;
	
	public static final String NOME_DATA_SOURCE_PLANETSTARWARS = "dataSource";
	
	public static void inicializarContexto() {
		if (contexto == null) {
			contexto = (ApplicationContext) new SpringApplication(AplicacaoPlanetaStarWars.class).run();
		}
	}

	public static void setContexto(ApplicationContext contexto) {
		Registro.contexto = contexto;
	}

	public static void finalizarContexto() {
		((AbstractApplicationContext) contexto).close();
	}

	public static final DataSource obterDataSourcePlanetStarWars() {
		inicializarContexto();
		return (DataSource) (contexto.getBean(NOME_DATA_SOURCE_PLANETSTARWARS));
	}

}
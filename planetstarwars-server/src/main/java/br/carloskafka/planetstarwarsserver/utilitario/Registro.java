package br.carloskafka.planetstarwarsserver.utilitario;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.carloskafka.planetstarwarsserver.AplicacaoPlanetaStarWars;

public class Registro {
	private static ApplicationContext contexto;

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

}
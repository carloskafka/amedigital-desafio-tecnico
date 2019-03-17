package br.carloskafka.planetstarwarsserver;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.carloskafka.planetstarwarsserver.utilitario.Registro;

@SpringBootApplication
public class AplicacaoPlanetaStarWars implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(AplicacaoPlanetaStarWars.class);

	@Autowired
	private ApplicationContext contexto;
	
	@PostConstruct
	public void inicializar() {
		Registro.setContexto(contexto);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Registro.inicializarContexto();
		logger.warn("Serviço do Planeta Star Wars iniciado.");
	}

	public static void main(String[] args) throws Exception {
		new SpringApplication(AplicacaoPlanetaStarWars.class).run(args);
	}
}

package br.carloskafka.planetstarwars.controladores;

import java.time.ZoneId;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.TestPropertySource;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import com.wix.mysql.distribution.Version;

import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.planetstarwarsserver.utilitario.HttpUtils;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestesControladorPlaneta {

	static MysqldConfig config = MysqldConfig.aMysqldConfig(Version.v5_7_19).withPort(3307)
			.withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC"))).withUser("test", "test")
			.withServerVariable("bind-address", "localhost").build();

	private static SchemaConfig schemaConfig = SchemaConfig.aSchemaConfig("test_database").build();

	private static EmbeddedMysql embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config).addSchema(schemaConfig).start();

	@Autowired
	private TestRestTemplate restTemplate;

	public TestesControladorPlaneta() {
		restTemplate = new TestRestTemplate();
	}

	@BeforeMethod
	public void iniciarBancoDeDados() {
		try {
			embeddedMysql.dropSchema(schemaConfig);
			embeddedMysql = embeddedMysql.addSchema(schemaConfig);
		} catch (Exception e) {
			embeddedMysql = embeddedMysql.addSchema(schemaConfig);
		}
	}

	@AfterMethod
	public void pararBancoDeDados() {
		embeddedMysql.stop();
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_entao_retorna_a_listagem_de_planetas() {
		int quantidadeDePlanetasEsperado = 1;

		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);

		restTemplate.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, HttpUtils.criar(novoPlanetaDto),
				ResultadoEdicaoPlanetaDTO.class);

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS, ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_entao_retorna_a_listagem_vazia_de_planetas() {
		int quantidadeDePlanetasEsperado = 0;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS, ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_adicionar_um_novo_planeta_entao_retorna_o_novo_planeta() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = restTemplate
				.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, new HttpEntity<PlanetaDTO>(novoPlanetaDto), ResultadoEdicaoPlanetaDTO.class).getBody();

		Assert.assertTrue(resultadoEdicaoPlanetaDTO.isSucesso());
		Assert.assertFalse(resultadoEdicaoPlanetaDTO.getPlanetaDto().getId().toString().isEmpty());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getNome(), novoPlanetaDto.getNome());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getClima(), novoPlanetaDto.getClima());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getTerreno(), novoPlanetaDto.getTerreno());		
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_adicionar_um_planeta_invalido_entao_retorna_erros() {
		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome("");
		novoPlanetaDto.setClima("");
		novoPlanetaDto.setTerreno("");
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = restTemplate
				.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, new HttpEntity<PlanetaDTO>(novoPlanetaDto), ResultadoEdicaoPlanetaDTO.class).getBody();

		Assert.assertFalse(resultadoEdicaoPlanetaDTO.isSucesso());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getErros().size(), 3);
	}

}

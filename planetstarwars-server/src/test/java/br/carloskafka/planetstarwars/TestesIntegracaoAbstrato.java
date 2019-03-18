package br.carloskafka.planetstarwars;

import java.io.File;
import java.sql.Connection;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import br.carloskafka.planetstarwarsserver.utilitario.Registro;

public abstract class TestesIntegracaoAbstrato {
	private static final Logger logger = LoggerFactory.getLogger(TestesIntegracaoAbstrato.class);
	
	private Connection conexao;
	private File arquivoParaInsersaoDeDados;
	private IDatabaseConnection conexaoDbUnit;
	private IDataSet dataSet;
	
	public TestesIntegracaoAbstrato(String nomeDoArquivoParaInsercao) {
		try {
			arquivoParaInsersaoDeDados = new File("src/test/resources/" + nomeDoArquivoParaInsercao);
		} catch (Exception e) {
			logger.error("Erro durante o preparo dos arquivos de dados para teste.", e);
		}
	}
	
	private IDataSet obterDataSetDBUnit() {
		if (dataSet == null) {
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			try {
				dataSet = builder.build(arquivoParaInsersaoDeDados);
			} catch (Exception e) {
				logger.error("Erro durante a criação do arquivo de dados de testes do DBUnit.");
			}
		}
		return dataSet;
	}
	
	private Connection obterConexaoPlanetStarwars() {
		if (conexao == null) {
			conexao = DataSourceUtils.getConnection(Registro.obterDataSourcePlanetStarWars());
		}
		return conexao;
	}
	
	private IDatabaseConnection obterConexaoDBUnit() {
		if (conexaoDbUnit == null) {
			try {
				conexaoDbUnit = new DatabaseConnection(obterConexaoPlanetStarwars());
				DatabaseConfig configuracao = conexaoDbUnit.getConfig();
				configuracao.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());				
			} catch (Exception e) {
				logger.error("Erro durante a configuração do DBUnit", e);
			}
		}
		return conexaoDbUnit;
	}
	
	@Before
	public void efetuarInsercaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.INSERT.execute(obterConexaoDBUnit(), obterDataSetDBUnit());
		} catch (Exception e) {
			logger.error("Erro durante a inserção de dados de teste.", e);
		}
	}
	
	@After
	public void efetuarRemocaoDeDadosDeTesteNoDB() {
		try {
			DatabaseOperation.DELETE_ALL.execute(obterConexaoDBUnit(), obterDataSetDBUnit());
		} catch (Exception e) {
			logger.error("Erro durante a remoção de dados de teste.", e);
		}
	}

}

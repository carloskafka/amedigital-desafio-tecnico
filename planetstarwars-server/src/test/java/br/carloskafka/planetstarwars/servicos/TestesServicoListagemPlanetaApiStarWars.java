package br.carloskafka.planetstarwars.servicos;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.carloskafka.planetstarwars.utilitario.JsonUtils;
import br.carloskafka.planetstarwarscommons.contrato.ContratoRest;
import br.carloskafka.planetstarwarscommons.dto.PlanetaDTO;
import br.carloskafka.planetstarwarscommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwarsserver.controladores.ControladorPlaneta;
import br.carloskafka.planetstarwarsserver.fachadas.FachadaPlaneta;

@WebMvcTest(ControladorPlaneta.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestesServicoListagemPlanetaApiStarWars {
	
	@InjectMocks
	private ControladorPlaneta controladorPlaneta;
	private FachadaPlaneta fachadaPlaneta = Mockito.mock(FachadaPlaneta.class);
	
	private MockMvc mockMvc;
	
	@BeforeClass
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
                .standaloneSetup(controladorPlaneta)
                .build();
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_os_planetas_da_api_star_wars_entao_retorna_todos_os_planetas() throws Exception {
		List<PlanetaDTO> planetasDto = new ArrayList<>();
		
		for (int i = 0; i < 61; i++) {
			PlanetaDTO planetaDto = new PlanetaDTO();
			
			planetaDto.setNome("Tatooine");
			planetaDto.setClima("arid");
			planetaDto.setTerreno("desert");
			
			List<String> filmesAparecidos = Arrays.asList("https://swapi.co/api/films/5/", 
			        "https://swapi.co/api/films/4/", 
			        "https://swapi.co/api/films/6/", 
			        "https://swapi.co/api/films/3/", 
			        "https://swapi.co/api/films/1/");
			
			planetaDto.setFilmesAparecidos(filmesAparecidos);
			
			planetasDto.add(planetaDto);
		}
		
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO(planetasDto);

		String jsonPlanetasObtidos = JsonUtils.criarJson(resultadoConsultaPlanetaDto);
		
		when(fachadaPlaneta.listarPlanetasApi()).thenReturn(resultadoConsultaPlanetaDto);
		
		mockMvc.perform(
							get(ContratoRest.URL_COMPLETA_PLANETAS_API))
							.andExpect(status().isOk())
							.andExpect(content().json(jsonPlanetasObtidos));
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_os_planetas_da_api_star_wars_e_estiver_fora_do_ar_entao_retorna_erro() throws Exception {
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();

		String jsonPlanetasObtidos = JsonUtils.criarJson(resultadoConsultaPlanetaDto);
		
		when(fachadaPlaneta.listarPlanetasApi()).thenReturn(resultadoConsultaPlanetaDto);
		
		mockMvc.perform(
							get(ContratoRest.URL_COMPLETA_PLANETAS_API))
							.andExpect(status().isOk())
							.andExpect(content().json(jsonPlanetasObtidos));
	}
}

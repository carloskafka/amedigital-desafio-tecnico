package br.carloskafka.planetstarwars.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.carloskafka.planetstarwars.dominio.Planeta;
import br.carloskafka.planetstarwars.dominio.ResultadoConsultaPlanetaDTO;
import br.carloskafka.planetstarwars.dominio.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.planetstarwars.fachadas.FachadaPlaneta;

@RestController("planetas")
public class ControladorPlaneta {
	
	@Autowired
	private FachadaPlaneta fachadaPlaneta;
	
	@PostMapping("/")
	public ResultadoEdicaoPlanetaDTO adicionarPlaneta(Planeta planeta) {
		return fachadaPlaneta.adicionarPlaneta(planeta);
	}
	
	@GetMapping("/")
	public ResultadoConsultaPlanetaDTO listarPlanetas() {
		return fachadaPlaneta.listarPlanetas();
	}
	
	@GetMapping("/{:nome}")
	public ResultadoConsultaPlanetaDTO buscarPorNome(@PathVariable("nome") String nome) {
		return fachadaPlaneta.buscarPorNome(nome);
	}
	
	@GetMapping("/{:id}")
	public ResultadoConsultaPlanetaDTO buscarPorId(@PathVariable("id") Long id) {
		return fachadaPlaneta.buscarPorId(id);
	}
}

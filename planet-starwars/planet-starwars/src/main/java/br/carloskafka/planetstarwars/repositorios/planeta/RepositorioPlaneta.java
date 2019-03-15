package br.carloskafka.planetstarwars.repositorios.planeta;

import java.util.List;

import br.carloskafka.planetstarwars.dominio.Planeta;

public interface RepositorioPlaneta {
	public Planeta adicionarPlaneta(Planeta planetaASalvar);
	public List<Planeta> listarPlanetas();
	public Planeta buscarPorNome(String nome);
	public Planeta buscarPorId(Long id);
}

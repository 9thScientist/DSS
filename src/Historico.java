import java.util.*;

public class Historico {
	private Map<Movimento, Morador> historico;
	private Map<Despesa, Morador> prestacoes;
	private Map<Integer, Categoria> categorias;

	public Historico() {
		historico = new HashMap<>();
		prestacoes = new HashMap<>();
		categorias = new HashMap<>();
	}

	public void addCategoria(Categoria c) {
		Categoria n_categoria = new Categoria(c);
		categorias.put(n_categoria.getId(), n_categoria);
	}

	public void addCategoria(int id, String d, boolean r) {
		Categoria n_categoria = new Categoria(id, d, r);
		categorias.put(n_categoria.getId(), n_categoria);
	}

	public void editCategoria(int categoria, String d, boolean r) {
		Categoria n_categoria = categorias.get(categoria);

		n_categoria.setDescricao(d);
		n_categoria.setRecorrente(r);
	}

	public void removerCategoria(Categoria c) {
		categorias.remove(c);
	}

	public Map<Movimento, Morador> getMovimentos() {
		Map<Movimento, Morador> ret = new HashMap<>();

		ret.putAll(historico);

		return historico;
	}

	public void addMovimento(Movimento m, Morador mo) {
		historico.put(m, mo);
	}

	public void editMovimento(int mov_id, Movimento n_movimento) {
		for (Movimento m : historico.keySet())
			if (m.getId() == mov_id) {
				m.update(n_movimento);
				break;
			}
	}

	public void removerMovimento(Movimento mov) {
		historico.remove(mov);
	}

	public int getMovimentoId() {
		return historico.size();
	}

	public int getCategoriaId() {
		return categorias.size();
	}

	public Movimento getMovimento(int id) throws MovimentoNaoExisteException {
		for (Movimento m : historico.keySet())
			if (m.getId() == id)
				return m;

		throw new MovimentoNaoExisteException("Não existe nenhum movimento " + id);
	}
}

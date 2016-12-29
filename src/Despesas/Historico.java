package Despesas;

import java.util.*;
import data.MovimentoDAO;
import data.DespesaDAO;
import data.CategoriaDAO;

public class Historico {
	private MovimentoDAO historico;
	private DespesaDAO despesas;
	private CategoriaDAO categorias;

	public Historico() {
		historico = new MovimentoDAO();
                despesas = new DespesaDAO();
		categorias = new CategoriaDAO();
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

	public Set<Movimento> getMovimentos() {
		Set<Movimento> ret = new TreeSet<>();

		for (Movimento movimento : historico.values())
			ret.add(movimento);

		return ret;
	}

	public Set<Movimento> getMovimentos(Date from, Date to) {
		Set<Movimento> ret = new TreeSet<>();

		for (Movimento m : historico.values())
			if (m.getData().compareTo(from) >= 0 && m.getData().compareTo(to) <= 0 )
				ret.add(m);

		return ret;
	}

	public Set<Movimento> getMovimentos(Date from, Date to, Categoria categoria) {
		Set<Movimento> ret = new TreeSet<>();

		for (Movimento m : historico.values())
			if (m.getData().compareTo(from) >= 0 && m.getData().compareTo(to) <= 0 && m.getClass().getSimpleName().equals("Despesa")) {
				Despesa d = (Despesa) m;
				if (d.getCategoria().equals(categoria))
					ret.add(m);
			}

		return ret;
	}

	public void addMovimento(Movimento m) {
		if (m.getClass().getSimpleName().equals("Despesa")) {
                    Despesa d = (Despesa) m;
                    despesas.put(d.getId(), d);
                }
		
		else historico.put(m.getId(), m);
	}

	public void editMovimento(int movimento, Movimento n_movimento) {
		historico.get(movimento).update(n_movimento);
	}

	public void removerMovimento(Movimento mov) {
		historico.remove(mov);
	}

	public int genMovimentoId() {
		return historico.size();
	}

	public int genCategoriaId() {
		return categorias.size();
	}

	public Movimento getMovimento(int id) throws MovimentoNaoExisteException {
		Movimento movimento = historico.get(id);

		if (movimento == null)
			throw new MovimentoNaoExisteException("Não existe nenhum movimento " + id);

		return movimento;
	}
}

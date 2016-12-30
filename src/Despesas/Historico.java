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
            categorias.remove(c.getId());
	}
        
        public Set<String> getCategorias() {
		Set<String> ret = new TreeSet<>();
                for (Categoria cat : categorias.values()) 
                    ret.add(cat.getDescricao());
                return ret;
	}
        


        
        public List<Movimento> getMovimentosList() {
		return new ArrayList<Movimento>(historico.values());
	}

	public List<Movimento> getMovimentos(Date from, Date to) {
		List<Movimento> ret = new ArrayList<>();

		for (Movimento m : historico.values())
			if (m.getData().compareTo(from) >= 0 && m.getData().compareTo(to) <= 0 )
				ret.add(m);

		return ret;
	}

	public List<Movimento> getMovimentos(Date from, Date to, Categoria categoria) {
		List<Movimento> ret = new ArrayList<>();

		for (Movimento m : historico.values())
			if (m.getData().compareTo(from) >= 0 && m.getData().compareTo(to) <= 0 && m.getClass().getSimpleName().equals("Despesa")) {
				Despesa d = (Despesa) m;
				if (d.getCategoria().equals(categoria))
					ret.add(m);
			}

		return ret;
	}
        
	public void addMovimento(Movimento m) {
            historico.put(m.getId(), m);
	}
        
        public void addDespesa(Despesa d) {
            despesas.put(d.getId(), d);
        }

	public void editMovimento(int movimento, Movimento n_movimento) {
		historico.get(movimento).update(n_movimento);
	}

	public void removerMovimento(Movimento mov) {
		historico.remove(mov.getId());
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
        
        public Categoria getCategoriaNome(String nome){
            for (Categoria c : categorias.values())
                if(c.getDescricao().equals(nome))
                    return c;
            
            return null;
        }
        
        public int getIdCategoria(String nome){
            CategoriaDAO dao = new CategoriaDAO();
            Categoria c = dao.get(nome);
            return c.getId();
        }
        
        
}

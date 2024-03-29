package Despesas;

import data.CategoriaDAO;

public class Categoria {
	private int id;
	private String descricao;
	private boolean recorrente;
        private boolean ativo;

	public Categoria(int id, String d, boolean r) {
                this.id = id;
		descricao = d;
		recorrente = r;
                ativo = true;
                
	}
        	
        public Categoria(int id, String d, boolean r, boolean at) {
                this.id = id;
		descricao = d;
		recorrente = r;
                ativo = at;
	}


	public Categoria(Categoria c){
                id = c.getId();
                descricao = c.getDescricao();
		recorrente = c.isRecorrente();
        }

        public boolean isAtivo() {
            return ativo;
        }
        
        public void desativar() {
            ativo = false;
        }
        
	public int getId() {
		return id;
	}

	public boolean isRecorrente() {
		return recorrente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String d) {
		descricao = d;
	}

	public void setRecorrente(boolean r) {
		recorrente = r;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (obj == null || (obj.getClass() != this.getClass())) return false;

		if (obj == this) return true;

		Categoria c = (Categoria) obj;
		return c.getId() == id &&
			   c.isRecorrente() == this.isRecorrente() &&
		       c.getDescricao().equals(this.getDescricao());
	}
}

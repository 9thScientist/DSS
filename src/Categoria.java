public class Categoria {
	private int id;
	private String descricao;
	private boolean recorrente;

	public Categoria(int id, String d, boolean r) {
		this.id = id;
		descricao = d;
		recorrente = r;
	}

	public Categoria(Categoria c){
		descricao = c.getDescricao();
		recorrente = c.isRecorrente();
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

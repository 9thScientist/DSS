import java.util.*;
import java.sql.Date;

public class Despesa extends Movimento {
	private int id;
	private String descricao;
	private Map<Morador, Float> racios;
	private Categoria categoria;

	public Despesa(int id, Apartamento a, Morador m, String d, float valor, Date date,
			boolean tr, Categoria c, Map<Morador, Float> rcs) {
		super(id, a, m, valor, date, tr);
		this.id = id;
		descricao = d;
		racios = new HashMap<>();
		racios.putAll(rcs);
		categoria = new Categoria(c);

	}

	public Despesa(int id, Apartamento a, Morador m, String d, float valor,
			GregorianCalendar date, boolean tr, Categoria c, Map<Morador, Float> rcs) {
		super(id, a, m, date, valor, tr);
		this.id = id;
		descricao = d;
		racios = new HashMap<>();
		racios.putAll(rcs);
		categoria = new Categoria(c);

	}

	public Despesa(Despesa d) {
		super((Movimento) d);
		id = d.getId();
		descricao = d.getDescricao();
		racios = d.getRacios();
		categoria = d.getCategoria();
		descricao = d.getDescricao();
//		super(id, d.getApartamento(), d.getApartamento(), d.getValor(), d.getDate(), d.isTransacao());
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Map<Morador, Float> getRacios() {
		//Map<Morador, float> r = new HashMap<>();

		//return r.putAll(racios);
		return racios;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setDescricao(String d) {
		descricao = d;
	}

	public void setRacios(Map<Morador, Float> rcs) {
		racios.putAll(rcs);
	}

	public void setCategoria(Categoria c) {
		categoria = new Categoria(c);
	}

	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) return false;

		if (o == this) return true;

		Despesa d = (Despesa) o;
		return d.getDescricao().equals(descricao) &&
		       d.getRacios().equals(racios) &&
			   d.getCategoria().equals(categoria);
	}

	public Despesa clone(){
		return new Despesa(this);
	}

	public void update(Despesa d) {
		descricao = d.getDescricao();
		racios.putAll(d.getRacios());
		categoria = d.getCategoria();

		super.update((Movimento) d);
	}
}

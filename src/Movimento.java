import java.util.*;

public class Movimento {
	private int id;
	private Apartamento apartamento;
	private Morador morador;
	private GregorianCalendar data;
	private float valor;
	private boolean transacao;

	public Movimento(int id, Apartamento a, Morador m, float v, Date d, boolean t) {
		this.id = id;
		apartamento = a;
		morador = m;
		valor = v;
		data = new GregorianCalendar();
		data.setTime(d);
		transacao = t;
	}

	public Movimento(int id, Apartamento a, Morador m, GregorianCalendar d, float v, boolean t) {
		this.id = id;
		apartamento = a;
		morador = m;
		valor = v;
		data = (GregorianCalendar) d.clone();
		transacao = t;
	}

	public Movimento(Movimento m) {
		id = m.getId();
		apartamento = m.getApartamento();
		morador = m.getMorador();
		data = m.getData();
		valor = m.getValor();
		transacao = m.isTransacao();
	}

	public int getId() {
		return id;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public Morador getMorador() {
		return morador;
	}

	public GregorianCalendar getData() {
		return (GregorianCalendar) data.clone();
	}

	public float getValor() {
		return valor;
	}

	public boolean isTransacao() {
		return transacao;
	}

	public void setId(int n_id) {
		id = n_id;
	}

	public void setApartamento(Apartamento n_apartamento) {
		apartamento = n_apartamento;
	}

	public void setMorador(Morador n_morador) {
		morador = n_morador;
	}

	public void setData(GregorianCalendar d) {
		data = (GregorianCalendar) d.clone();
	}

	public void setValor(float v) {
		valor = v;
	}

	public void setTransacao(boolean t) {
		transacao = t;
	}

	public void update(Movimento m) {
		morador = m.getMorador();
		data = m.getData();
		valor = m.getValor();
		transacao = m.isTransacao();
	}
}

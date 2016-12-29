package Despesas;

import java.util.*;
import Moradores.Apartamento;
import Moradores.Morador;
import java.sql.Date;

public class Movimento implements Comparable<Movimento> {
	private int id;
	private Apartamento apartamento;
	private Morador morador;
	private Date data;
	private float valor;
	private boolean transacao;

	public Movimento(int id, Apartamento a, Morador m, float v, Date d, boolean t) {
		this.id = id;
		apartamento = a;
		morador = m;
		valor = v;
		data = d;
		transacao = t;
	}

	public Movimento(int id, Apartamento a, Morador m, float v, GregorianCalendar d, boolean t) {
		this.id = id;
		apartamento = a;
		morador = m;
		valor = v;
		data = (Date) d.clone();
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

	public Date getData() {
		return (Date) data.clone();
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

	public void setData(Date d) {
		data = d;
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

	public int compareTo(Movimento mov) {
		return data.compareTo(mov.data);
	}
}

import java.util.*;

public class Apartamento {
	private int id;
	private float saldo;
	private Map<Integer, Morador> moradores;

	public Apartamento(int id, float s) {
		this.id = id;
		saldo = s;
	}

	public int getId() {
		return id;
	}

	public float getSaldo() {
		return saldo;
	}

	public Morador getMorador(int id) {
		return moradores.get(id);
	}

	public Map<Integer, Morador> getMoradores() {
		return moradores;
	}

	public void setId(int n_id) {
		id = n_id;
	}

	public void setSaldo(int n_saldo) {
		saldo = n_saldo;
	}

	public void addMorador(Morador m) {
		Morador morador = new Morador(m);

		moradores.put(m.getId(), m);
	}

	public void editMorador(Morador m, String nome, String contacto, String img) {
		Morador morador = moradores.get(m.getId());

		morador.setNome(nome);
		morador.setContacto(contacto);
		morador.setImagem(img);
	}

	public void removerMorador(Morador m) {
		moradores.remove(m);
	}

	public void updateSaldos(float valor, Map<Morador, Float> racios) {
		for (Morador m : moradores.values())
			m.updateSaldo(valor * racios.get(m));
	}

	public void addSaldo(float valor) {
		saldo += valor;
	}

	public void decSaldo(float valor) {
		saldo -= valor;
	}

	public int getMoradorId() {
		return moradores.size();
	}
}

package Moradores;

public class Morador {
	private int id;
	private String nome;
	private String contacto;
	private String imagem;
	private float saldo;
	private boolean ativo;
	private Apartamento apartamento;

	public Morador(int id, Apartamento a, String n, String c, float s, String img) {
		this.id = id;
		apartamento = a;
		nome = n;
		contacto = c;
		imagem = img;
		saldo = s;
		ativo = true;
	}

	public Morador(Morador m) {
		id = m.getId();
		nome = m.getNome();
		contacto = m.getContacto();
		imagem = m.getImagem();
		saldo = m.getSaldo();
		ativo = m.ativo();
		apartamento = m.getApartamento();
	}

	public int getId() {
		return id;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public String getNome() {
		return nome;
	}

	public String getContacto() {
		return contacto;
	}

	public String getImagem() {
		return imagem;
	}

	public float getSaldo() {
		return saldo;
	}

	public boolean ativo() {
		return ativo;
	}

	public void desativar() {
		ativo = false;
	}

	public void ativar() {
		ativo = true;
	}

	public void setId(int n_id) {
		id = n_id;
	}

	public void setApartamento(Apartamento a) {
		apartamento = a;
	}

	public void setNome(String n) {
		nome = n;
	}

	public void setContacto(String c) {
		contacto = c;
	}

	public void setImagem(String img) {
		imagem = img;
	}

	public float addSaldo(float m) throws MontanteInvalidoException {
		if (m < 0)
			throw new MontanteInvalidoException("O montante a adicionar deve ser positivo");

		saldo += m;
		return saldo;
	}

	public float decSaldo(float m) throws MontanteInvalidoException {
		if (m < 0)
			throw new MontanteInvalidoException("O montante a decrementar deve ser positivo");

		saldo -= m;
		return saldo;
	}

	public float updateSaldo(float valor) {
		return (saldo += valor);
	}

	public void update(Morador m) {
		nome = m.getNome();
		contacto = m.getContacto();
		imagem = m.getImagem();
		saldo = m.getSaldo();
		ativo = m.ativo();
		apartamento = m.getApartamento();
	}
}

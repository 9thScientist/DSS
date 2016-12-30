package Moradores;
import Despesas.MontanteInvalidoException;


public class Morador {
	private int id;
	private String nome;
	private String contacto;
	private float saldo;
	private boolean ativo;
	private Apartamento apartamento;

	public Morador(int id, Apartamento a, String n, String c, float s, Boolean ativo) {
		this.id = id;
		apartamento = a;
		nome = n;
		contacto = c;
		saldo = s;
		this.ativo = ativo;
	}

	public Morador(Morador m) {
		id = m.getId();
		nome = m.getNome();
		contacto = m.getContacto();
		saldo = m.getSaldo();
		ativo = m.isAtivo();
		apartamento = m.getApartamento();
	}
        
        public Morador() {
		id = 0;
		nome ="";
		contacto = "";
		saldo = 0;
		ativo = true;
		apartamento = null;
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

	public Boolean isAtivo() {
		return ativo;
	}

	public float getSaldo() {
		return saldo;
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

	
	public float addSaldo(float m) throws MontanteInvalidoException {
            if (m < 0)
                    throw new MontanteInvalidoException("O montante a adicionar deve ser positivo");
            
                saldo += m;
		apartamento.setSaldoMorador(this);
		return saldo;
	}

	public float decSaldo(float m) throws MontanteInvalidoException {
		if (m < 0)
			throw new MontanteInvalidoException("O montante a decrementar deve ser positivo");
                
		saldo -= m;
		apartamento.setSaldoMorador(this);
                return saldo;
	}

	public float updateSaldo(float valor) {
		saldo += valor;
                
                apartamento.addMorador(this);
                
                return saldo;
	}

	public void update(Morador m) {
		nome = m.getNome();
		contacto = m.getContacto();
		saldo = m.getSaldo();
		ativo = m.isAtivo();
		apartamento = m.getApartamento();
	}
        
}

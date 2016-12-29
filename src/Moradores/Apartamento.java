package Moradores;

import data.ApartamentoDAO;
import java.util.*;
import data.MoradorDAO;

public class Apartamento {
	private int id;
	private float saldo;
	private MoradorDAO moradores;

	public Apartamento(int id, float s) {
		ApartamentoDAO dao = new ApartamentoDAO();
                this.id = id;
		saldo = s;
		moradores = new MoradorDAO();
                dao.put(this);
        }
        
        public Apartamento() {
		ApartamentoDAO dao = new ApartamentoDAO();
                if(dao.isEmpty()){
                    dao.put(new Apartamento(1,0));
                }
                Apartamento a = dao.get(1);
                this.id = a.getId();
		this.saldo = a.getSaldo();
		moradores = new MoradorDAO();
                dao.put(this);
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
                
                moradores.put(morador.getId(), morador);
	}

	public void removerMorador(Morador m) {
		m.desativar();
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

	public int genMoradorId() {
		return moradores.size();
	}
        
        public Morador getMoradorNome(String morador){
            MoradorDAO dao = new MoradorDAO();
            return dao.get(morador);
        }
        
}

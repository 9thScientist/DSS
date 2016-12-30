package Main;

import java.util.*;
import Moradores.*;
import Despesas.*;

public class SplitExpense {

	private Apartamento apartamento;
	private Historico historico;
        
        
        public SplitExpense(){
            apartamento = new Apartamento();
            historico = new Historico();
        }
        
        public Apartamento getApartamento(){
            return apartamento;
        }
        
         public void registarDespesa(String d, Categoria c, float valor,
		GregorianCalendar data, Map<Morador, Float> racios, Morador morador) {
		int id = historico.genMovimentoId();
		Despesa despesa = new Despesa(id, apartamento, morador, d, valor, data, c, racios);
		historico.addDespesa(despesa);

		apartamento.decSaldo(valor);
		apartamento.updateSaldos(-valor, racios);
	}
        
        public void registarDespesa(boolean tr, String d, Categoria c, float valor,
		java.sql.Date data, Map<Morador, Float> racios, Morador morador) {
		int id = historico.genMovimentoId();
		Despesa despesa = new Despesa(id, apartamento, morador, d, valor, data, tr, c, racios);
		historico.addDespesa(despesa);

		apartamento.decSaldo(valor);
		apartamento.updateSaldos(-valor, racios);
	}

	public void editarDespesa(int id, Despesa d) throws MovimentoNaoExisteException {
		Despesa despesa = (Despesa) historico.getMovimento(id);
		float ant_valor = despesa.getValor();
		Map<Morador, Float> ant_racios = despesa.getRacios();
		despesa.update(d);
                historico.addDespesa(d);
		apartamento.addSaldo(ant_valor);
		apartamento.decSaldo(d.getValor());
		apartamento.updateSaldos(ant_valor, ant_racios);
		apartamento.updateSaldos(-(d.getValor()), d.getRacios());
	}

	public void removerDespesa(Despesa d) {
		historico.removerMovimento(d);

		apartamento.addSaldo(d.getValor());
		apartamento.updateSaldos(d.getValor(), d.getRacios());
	}
        
        public void removerMovimento(Movimento m) {
		historico.removerMovimento(m);

		apartamento.decSaldo(m.getValor());
                apartamento.updateSaldo(m.getMorador(), m.getValor());
	}

	public void registarMorador(String nome, String contacto) {
		int id = apartamento.genMoradorId();
		Morador morador = new Morador(id, apartamento, nome, contacto, 0,true);

		apartamento.addMorador(morador);
	}

	public void editarMorador(String nome, Morador m) {
		Morador morador = apartamento.getMoradorNome(nome);
		morador.update(m);
	}
        
        public void editarMorador(String nome, String n_nome, String n_contacto) {
            Morador morador = apartamento.getMoradorNome(nome);
            apartamento.editMorador(morador, n_nome, n_contacto);
	}

	public void removerMorador(String morador) {
            Apartamento a = new Apartamento();
            a.removerMorador(a.getMoradorNome(morador));
	}

	public void levantar(Morador m, float valor) throws MontanteInvalidoException, SaldoInsuficienteException {
		if (m.getSaldo() < valor)
			throw new SaldoInsuficienteException("Saldo Insuficiente");

		m.decSaldo(valor);
		apartamento.decSaldo(valor);

		int id = historico.genMovimentoId();
                java.sql.Date hoje = new java.sql.Date((new Date()).getTime());
		Movimento levantamento = new Movimento(id, apartamento, m, -valor, hoje, true);
		historico.addMovimento(levantamento);
	}

	public void depositar(Morador m, float valor) throws MontanteInvalidoException {
                if (valor < 0)
                    throw new MontanteInvalidoException("Valor não pode ser inferior a 0.");
                m.addSaldo(valor);
		apartamento.addSaldo(valor);
                int id = historico.genMovimentoId();
                java.sql.Date hoje = new java.sql.Date((new Date()).getTime());
		Movimento deposito = new Movimento(id, apartamento, m, valor, hoje, true);
		historico.addMovimento(deposito);
                
        }

	public List<Movimento> getHistorico() {
		return historico.getMovimentosList();
	}
        
        public List<Movimento> getHistoricoList() {
            return historico.getMovimentosList();
        }
        
        public Historico getFullHistorico(){
            return historico;
        }

	public List<Movimento> getHistorico(Date from, Date to) {
		return historico.getMovimentos(from, to);
	}

	public List<Movimento> getHistorico(Date from, Date to, Categoria categoria) {
		return historico.getMovimentos(from, to, categoria);
	}

        public Set<String> getCategorias() {
            return historico.getCategorias();
        }
        
        public Categoria getCategoria(String nome) {
            return historico.getCategoriaNome(nome);
        }
        
	public Categoria criarCategoria(String descricao, boolean recorrente) {
		int id = historico.genCategoriaId();
		Categoria categoria = new Categoria(id, descricao, recorrente);
                historico.addCategoria(categoria);
                return categoria;
	}

	public void editarCategoria(int id, String d, boolean r) {
		historico.editCategoria(id, d, r);
	}

	public void removerCategoria(String nome) {
                historico.removerCategoria(historico.getCategoriaNome(nome));
	}
}

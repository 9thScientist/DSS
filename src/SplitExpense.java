import java.util.*;

public class SplitExpense {

	private Apartamento apartamento;
	private Historico historico;

	public void registarDespesa(boolean tr, String d, Categoria c, float valor,
			GregorianCalendar data, Map<Morador, Float> racios, Morador morador) {
		int id = historico.genMovimentoId();
		Despesa despesa = new Despesa(id, apartamento, morador, d, valor, data, tr, c, racios);
		historico.addMovimento(despesa);

		apartamento.decSaldo(valor);
		apartamento.updateSaldos(-valor, racios);
	}

	public void editarDespesa(int id, Despesa d) throws MovimentoNaoExisteException {
		Despesa despesa = (Despesa) historico.getMovimento(id);
		float ant_valor = despesa.getValor();
		Map<Morador, Float> ant_racios = despesa.getRacios();
		despesa.update(d);

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

	public void registarMorador(String nome, String contacto, String img) {
		int id = apartamento.genMoradorId();
		Morador morador = new Morador(id, apartamento, nome, contacto, 0, img);

		apartamento.addMorador(morador);
	}

	public void editarMorador(int id, Morador m) {
		Morador morador = apartamento.getMorador(id);
		morador.update(m);
	}

	public void removerMorador(Morador m) {
		apartamento.removerMorador(m);
	}

	public void levantar(Morador m, float valor) throws MontanteInvalidoException, SaldoInsuficienteException {
		if (m.getSaldo() < valor)
			throw new SaldoInsuficienteException("Saldo Insuficiente");

		m.decSaldo(valor);
		apartamento.decSaldo(valor);

		int id = historico.genMovimentoId();
		Movimento levantamento = new Movimento(id, apartamento, m, valor, new Date(), true);
		historico.addMovimento(levantamento);
	}

	public void depositar(Morador m, float valor) throws MontanteInvalidoException {
		m.addSaldo(valor);
		apartamento.addSaldo(valor);

		int id = historico.genMovimentoId();
		Movimento deposito = new Movimento(id, apartamento, m, valor, new Date(), true);
		historico.addMovimento(deposito);
	}

	public Set<Movimento> getHistorico() {
		return historico.getMovimentos();
	}

	public Set<Movimento> getHistorico(GregorianCalendar from, GregorianCalendar to) {
		return historico.getMovimentos(from, to);
	}

	public Set<Movimento> getHistorico(GregorianCalendar from, GregorianCalendar to, Categoria categoria) {
		return historico.getMovimentos(from, to, categoria);
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

	public void removerCategoria(Categoria c) {
		historico.removerCategoria(c);
	}
}

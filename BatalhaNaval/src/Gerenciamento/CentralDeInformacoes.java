package Gerenciamento;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import Alerta.RecursosEmail;
import Classes.Jogador;
import Classes.Partida;
import Persistencia.Persistencia;
import Relatorio.GeradorDeRelatorio;

public class CentralDeInformacoes {

	private HashMap<String, Jogador> todosOsJogadores = new HashMap<String, Jogador>();
	private ArrayList<Partida> todasAsPartidas = new ArrayList<Partida>();
	private Jogador desafiante;
	private Jogador desafiado;
	private Partida partidaAtual;

	public CentralDeInformacoes() {

	}

	public Partida getPartidaAtual() {
		return partidaAtual;
	}

	public void setPartidaAtual(Partida partidaAtual) {
		this.partidaAtual = partidaAtual;
	}

	public ArrayList<Partida> getTodasAsPartidas() {
		return todasAsPartidas;
	}

	public void setTodasAsPartidas(ArrayList<Partida> todasAsPartidas) {
		this.todasAsPartidas = todasAsPartidas;
	}

	public Jogador getDesafiante() {
		return desafiante;
	}

	public void setDesafiante(Jogador desafiante) {
		this.desafiante = desafiante;
	}

	public Jogador getDesafiado() {
		return desafiado;
	}

	public void setDesafiado(Jogador desafiado) {
		this.desafiado = desafiado;
	}

	public boolean jaTemUmVencedor() {

		if (this.desafiado.perdeu() || this.desafiante.perdeu()) {
			return true;
		}

		return false;
	}

	public boolean isDesafiante(Jogador jogador) {

		return jogador.equals(this.desafiante);
	}

	public boolean ehUmJogadorSimulado(Jogador jogador) {

		return jogador.equals(this.desafiado);
	}

	public boolean adicionarJogador(Jogador jogador) throws Exception {
	
		if (recuperarJogador(jogador.getLogin()) == null ) {
			this.todosOsJogadores.put(jogador.getLogin(), jogador);
			Persistencia.salvarOuAtualizar(this);
					
			return true;
		}
		throw new Exception("Usuário já cadastrado");
	}

	public HashMap<String, Jogador> getTodosOsJogadores() {
		return todosOsJogadores;
	}

	public void setTodosOsJogadores(HashMap<String, Jogador> novaLista) {
		this.todosOsJogadores = novaLista;
	}

	public Jogador recuperarJogador(String login) {

		return todosOsJogadores.get(login);
	}

	public Jogador login(String login, String senha) {
		Jogador jogador = recuperarJogador(login);

		if (jogador != null) {
			if (jogador.getSenha().equals(senha) && jogador.isExcluido() == false) {
				this.desafiante = jogador;
				Persistencia.salvarOuAtualizar(this);
				return jogador;
			}
		}

		return null;
	}
	
	public void inicializaPartida() {
		Partida partida = new Partida(this.desafiante, this.desafiado);
		this.partidaAtual = partida;
	}

	public Partida finalizaAPartida() {

		if (this.desafiado.perdeu()) {
			this.partidaAtual.setVencedor(this.desafiante);
			this.partidaAtual.setPerdedor(this.desafiado);
			desafiante.addVitoriaHumana(partidaAtual);
			desafiado.addDerrotaMaquina(partidaAtual);

		} else {
			this.partidaAtual.setVencedor(desafiado);
			this.partidaAtual.setPerdedor(this.desafiante);
			desafiado.addVitoriaMaquina(partidaAtual);
			desafiante.addDerrotaHumana(partidaAtual);
		}
		
		this.todasAsPartidas.add(partidaAtual);
		
		this.partidaAtual.getDesafiado().reativaMapa();
		this.partidaAtual.getDesafiante().reativaMapa();
		
		enviarNotificacao(this.partidaAtual);
		Persistencia.salvarOuAtualizar(this);

		return partidaAtual;
	}

	private void enviarNotificacao(Partida partida) {
	
	}

	public Jogador modificarSenhaDoJogador(String login,String senha){
		Jogador jogador = recuperarJogador(login);
		if(jogador!=null){
			jogador.setSenha(senha);
			Persistencia.salvarOuAtualizar(this);
		}
		
		return jogador;
		
	}
	
	public void removerUsuario(String login) {
		Jogador jogador = this.todosOsJogadores.get(login);
		jogador.setExcluido(true);		
		Persistencia.salvarOuAtualizar(this);
	}
	
	public void excluirContaUsuarioLogado() {
		
		Jogador jogador = this.desafiante;
		
		GeradorDeRelatorio geradorDeRelatorio = new GeradorDeRelatorio();
		RecursosEmail recursosEmail = new RecursosEmail();
		
		String nomeDoRelatorio = "";
		
		try {
			nomeDoRelatorio = geradorDeRelatorio.gerarRelatorioPartidasGanhas(jogador);
			recursosEmail.enviarRelatorio(nomeDoRelatorio, "Relatório de Partidas Ganhas", jogador.getEmail());
			
			nomeDoRelatorio = geradorDeRelatorio.gerarRelatorioPartidasPerdidas(jogador);
			recursosEmail.enviarRelatorio(nomeDoRelatorio, "Relatório de Partidas Perdidas",  jogador.getEmail());
		
			removerUsuario(jogador.getLogin());
		Persistencia.salvarOuAtualizar(this);
	} catch (Exception erro) {
		JOptionPane.showMessageDialog(null, "Houve um erro ao excluír a conta!");
	}
	
	}
}

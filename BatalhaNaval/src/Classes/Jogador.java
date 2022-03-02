package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.naming.PartialResultException;
import javax.swing.JButton;

public class Jogador {

	private String email;
	private String senha;
	private String login;
	private int quantidadeVitorias;
	private int quantidadeDerrotas;
	private int pontuacao;
	private HashMap<String, Integer> historicoDePontos = new HashMap<String, Integer>();
	private HashMap<String, Partida> historicoDePartidas = new HashMap<String, Partida>();
	private Mapa mapa;
	private boolean excluido = false;

	public Jogador(String login, String senha, String email) {
		this.email = email;
		this.senha = senha;
		this.login = login;
		this.quantidadeVitorias = 0;
		this.quantidadeDerrotas = 0;
		this.pontuacao = 0;
		this.mapa = new Mapa(new Dimensao(Mapa.PADRAO_QUANTIDADE_LINHAS, Mapa.PADRAO_QUANTIDADE_COLUNAS),
				Mapa.NAVIO_OCULTO);
	}
	
	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void adicionaVitoria() {
		quantidadeVitorias++;
		pontuacao += 10;
	}

	public void adicionaDerrota() {
		quantidadeDerrotas++;
		pontuacao -= 5;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getVitorias() {
		return quantidadeVitorias;
	}

	public void setVitorias(int vitorias) {
		this.quantidadeVitorias = vitorias;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public String toString() {
		return this.login;
	}

	public int getQuantidadeVitorias() {
		return quantidadeVitorias;
	}

	public void setQuantidadeVitorias(int quantidadeVitorias) {
		this.quantidadeVitorias = quantidadeVitorias;
	}

	public int getQuantidadeDerotas() {
		return quantidadeDerrotas;
	}

	public void setQuantidadeDerotas(int quantidadeDerotas) {
		this.quantidadeDerrotas = quantidadeDerotas;
	}

	

	public int getQuantidadeDerrotas() {
		return quantidadeDerrotas;
	}

	public void setQuantidadeDerrotas(int quantidadeDerrotas) {
		this.quantidadeDerrotas = quantidadeDerrotas;
	}

	public HashMap<String, Integer> getHistoricoDePontos() {
		return historicoDePontos;
	}

	public void setHistoricoDePontos(HashMap<String, Integer> historicoDePontos) {
		this.historicoDePontos = historicoDePontos;
	}

	public HashMap<String, Partida> getHistoricoDePartidas() {
		return historicoDePartidas;
	}

	public void setHistoricoDePartidas(HashMap<String, Partida> historicoDePartidas) {
		this.historicoDePartidas = historicoDePartidas;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public boolean perdeu() {
		return !this.mapa.aindaTemNavios();
	}

	@Override
	public boolean equals(Object o) {

		Jogador j = (Jogador) o;
		return this.email.equalsIgnoreCase(j.getEmail());
	}

	public void addVitoriaHumana(Partida partida) {
		this.pontuacao += 10;
		this.quantidadeVitorias++;
		atualizaHistorico(partida);
		// reativaMapa();
	}

	public void addDerrotaHumana(Partida partida) {
		this.pontuacao -= 5;
		this.quantidadeDerrotas++;
		atualizaHistorico(partida);
		// reativaMapa();
	}

	public void addVitoriaMaquina(Partida partida) {
		this.pontuacao += 2;
		this.quantidadeVitorias++;
		atualizaHistorico(partida);
		// reativaMapa();
	}

	public void addDerrotaMaquina(Partida partida) {
		this.quantidadeDerrotas++;
		atualizaHistorico(partida);
	}

	public void reativaMapa() {
		int[][] frota = mapa.getFrota();
		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {
				if (frota[i][j] < 0) {
					frota[i][j] = frota[i][j] * (-1);
				}

			}
		}

	}

	private void atualizaHistorico(Partida partida) {
		this.historicoDePontos.put(partida.getData().toString(), this.pontuacao);
		this.historicoDePartidas.put(partida.getData().toString(), partida);
	}

	public boolean mapaFoiInicializado() {
		return this.mapa.ehValido();
	}

	public Partida getUltimaPartidaContra(Jogador jogadorEscolhido) {
		
		LocalDateTime dataUltimaBatalha = LocalDateTime.MIN;
		System.out.println(dataUltimaBatalha);
		
		for (String data : historicoDePartidas.keySet()) {
			
		
			Partida partida = historicoDePartidas.get(data);
		
			boolean jogouContra = (partida.getDesafiante().equals(jogadorEscolhido)
					|| partida.getDesafiado().equals(jogadorEscolhido));
			
			if(jogouContra) {
				if(partida.getData().isAfter(dataUltimaBatalha)&& partida.getVencedor()!=null) {
					dataUltimaBatalha = partida.getData();	
				}
				
				
			}
		}
		
		return this.historicoDePartidas.get(dataUltimaBatalha.toString());	
	}	
}

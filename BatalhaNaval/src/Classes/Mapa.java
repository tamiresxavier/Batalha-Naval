package Classes;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Mapa implements Serializable {
	
	public static int PADRAO_QUANTIDADE_LINHAS = 5;
	public static int PADRAO_QUANTIDADE_COLUNAS = 5;
	public static int PADRAO_QUANTIDADE_NAVIOS = 10;
	public static int AGUA = 0;
	public static int NAVIO_OCULTO = 10;
	public static int NAVIO_DESCOBERTO = -10;
	public static int FIM = -100;
	private int[][] frota;
	private Dimensao dimensao;
	private int quantidadeDeNavios;
	private LocalDateTime dataDeAtualizacao;

	public Mapa(Dimensao dimensao, int quantidadeDeNavios) {
		this.dimensao = dimensao;
		this.quantidadeDeNavios = quantidadeDeNavios;
		this.frota = new int[dimensao.getLinhas()][dimensao.getColunas()];
		this.dataDeAtualizacao = LocalDateTime.now();
	}

	public int getQuantidadeDeNavios() {
		return quantidadeDeNavios;
	}

	public void setQuantidadeDeNavios(int quantidadeDeNavios) {
		this.quantidadeDeNavios = quantidadeDeNavios;
	}

	public Dimensao getDimensao() {
		return dimensao;
	}

	public void setDimensao(Dimensao dimensao) {
		this.dimensao = dimensao;
	}

	public int[][] getFrota() {
		return frota;
	}

	public void setFrota(int[][] frota) {
		this.frota = frota;
		this.dataDeAtualizacao = LocalDateTime.now();
	}

	public String toString() {

		String s = "[";

		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {
				s += frota[i][j] + ",";
			}
		}
		s += "]";
		return s;
	}

	public boolean ehAgua(int x, int y) {

		if (frota[x][y] == Mapa.AGUA) {
			return true;
		}
		return false;
	}

	public boolean temEmBarcacao(int x, int y) {
		if (frota[x][y] == Mapa.NAVIO_OCULTO) {
			return true;
		}
		return false;
	}

	public boolean aindaTemNavios() {

		int soma = 0;
		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {
				soma += frota[i][j];
			}
		}

		if (soma == Mapa.FIM) {
			return false;
		}

		return true;
	}

	public int linhas() {
		return this.dimensao.getLinhas();
	}

	public int colunas() {
		return this.dimensao.getColunas();
	}

	public boolean ehValido() {

		int soma = 0;
		int somaMapaValido = this.quantidadeDeNavios * Mapa.NAVIO_OCULTO;

		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {
				soma += frota[i][j];
			}
		}

		if (soma == somaMapaValido) {
			return true;
		}
		return false;
	}

	public LocalDateTime getDataDeAtualizacao() {
		return dataDeAtualizacao;
	}

	public void embarcacaoAtacada(int x, int y) {
		frota[x][y] = Mapa.NAVIO_DESCOBERTO;		
	}
}

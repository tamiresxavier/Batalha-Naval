package Classes;

import java.io.Serializable;

public class Dimensao implements Serializable {
	
	private int linhas;
	private int colunas;
	
	public Dimensao(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;		
	}
	
	public int getLinhas() {
		return linhas;
	}
	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}
	public int getColunas() {
		return colunas;
	}
	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
}

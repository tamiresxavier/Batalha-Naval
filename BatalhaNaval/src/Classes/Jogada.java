package Classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Jogada implements Serializable {

	private LocalDateTime data;
	private Jogador jogador;
	private Partida partida;
	private String descricao;
	private int x;
	private int y;

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
	
		return this.data+" - "+this.descricao;
	}
}

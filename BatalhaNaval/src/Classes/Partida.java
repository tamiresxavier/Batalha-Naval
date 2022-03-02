package Classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Partida implements Serializable{

	private Jogador desafiante;
	private Jogador desafiado;
	private LocalDateTime data;
	private List<Jogada> jogadas;
	private boolean finalizada;
	private Jogador vencedor;
	private Jogador perdedor;
	private Mapa mapaDesafiante;
	private Mapa mapaDesafiado;

	public Partida(Jogador desafiante, Jogador desafiado) {
		this.data = LocalDateTime.now();
		this.jogadas = new ArrayList<Jogada>();
		this.finalizada = false;
		this.desafiado = desafiado;
		this.desafiante = desafiante;
		this.mapaDesafiado = desafiado.getMapa();
		this.mapaDesafiante = desafiante.getMapa();
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public List<Jogada> getJogadas() {
		return jogadas;
	}

	public void setJogadas(List<Jogada> jogadas) {
		this.jogadas = jogadas;
	}

	public Mapa getMapaDesafiante() {
		return mapaDesafiante;
	}

	public void setMapaDesafiante(Mapa mapaDesafiante) {
		this.mapaDesafiante = mapaDesafiante;
	}

	public Mapa getMapaDesafiado() {
		return mapaDesafiado;
	}

	public void setMapaDesafiado(Mapa mapaDesafiado) {
		this.mapaDesafiado = mapaDesafiado;
	}	
	
	public void setPerdedor(Jogador jogador) {
		this.perdedor = jogador;	
	}
	

	public Jogador getPerdedor() {
		return perdedor;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Partida outra = (Partida) obj;
		
		return  this.getData().equals(outra.getData());
	}
}

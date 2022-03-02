package Tela.Ouvinte;

import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Classes.Jogador;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.TelaEditarFrota;
import Tela.TelaMapa;

public class CliqueRanking implements ListSelectionListener{

	
	private CentralDeInformacoes centralDeInformacoes;
	private JTable jTable;
	private JFrame tela;
	public CliqueRanking(CentralDeInformacoes centralDeInformacoes, JTable jTable, JFrame tela) {
		this.centralDeInformacoes = centralDeInformacoes;
		this.jTable = jTable;
		this.tela = tela;
	
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {

		validaPartida();
		
	}
	private void validaPartida() {
		
		Jogador desafiado = centralDeInformacoes.recuperarJogador(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
		
		if(desafiado.equals(centralDeInformacoes.getDesafiante())==false) {
			Partida ultimaPartidaContra = centralDeInformacoes.getDesafiante().getUltimaPartidaContra(desafiado);
			boolean validacaoMapa = validaMapa(ultimaPartidaContra, desafiado);
			
			
			if(validacaoMapa) {
				
				if(centralDeInformacoes.getDesafiante().getMapa().ehValido() && desafiado.getMapa().ehValido()) {
					centralDeInformacoes.setDesafiado(desafiado);
					Persistencia.salvarOuAtualizar(centralDeInformacoes);							
					new TelaMapa();
					tela.dispose();	
				}else if (centralDeInformacoes.getDesafiante().getMapa().ehValido() == false) {

					int resposta = JOptionPane.showConfirmDialog(null, "Atualize seu mapa para Batalhar!!!");
					if (resposta == JOptionPane.OK_OPTION) {
						TelaEditarFrota telaEditarFrota = new TelaEditarFrota();
						tela.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "O jogador " + desafiado.getLogin() + " precisa atualizar o mapa para batalhar!!!");
				}
				
			}
	
		} else {
			JOptionPane.showMessageDialog(null, "Você não pode desafiar você mesmo!!!");
		}			
	}
	
	private boolean validaMapa(Partida ultimaPartidaContra, Jogador desafiado) {
		
		boolean validacao = true;
		if(ultimaPartidaContra!=null) {
			LocalDateTime dataDaUltimaPartida = ultimaPartidaContra.getData();
			
			
			if(ultimaPartidaContra.getVencedor().equals(desafiado)) {
				
				LocalDateTime dataDeAtualizacaoDoMapa = desafiado.getMapa().getDataDeAtualizacao();
				
				
				
				if(!dataDeAtualizacaoDoMapa.isAfter(dataDaUltimaPartida)) {
					validacao = false;
					JOptionPane.showMessageDialog(null, "O oponente desafiado ainda não atualizou seu mapa desde a ultima partida entre vocês!!!");	
				}
				
			} else if (ultimaPartidaContra.getVencedor().equals(ultimaPartidaContra.getDesafiante())) {				
				
				LocalDateTime dataDeAtualizacaoDoMapa = ultimaPartidaContra.getDesafiante().getMapa().getDataDeAtualizacao();
				
				if(!dataDeAtualizacaoDoMapa.isAfter(dataDaUltimaPartida)) {
					validacao = false;
					JOptionPane.showMessageDialog(null, "Você ainda não atualizou seu mapa desde a última partida entre vocês!!!");	
				}
			}
		}
		return validacao;
	}
}

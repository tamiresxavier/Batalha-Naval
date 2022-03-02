package Tela.Ouvinte;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Classes.Jogador;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Tela.TelaReverPartida;

public class CliqueHistorico implements ListSelectionListener {

	private CentralDeInformacoes centralDeInformacoes;
	private JTable tabela;

	public CliqueHistorico(CentralDeInformacoes centralDeInformacoes, JTable tabela) {
		this.centralDeInformacoes = centralDeInformacoes;
		this.tabela = tabela;

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		Jogador jogador = centralDeInformacoes.getDesafiante();

		String chavePartida = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();

		Partida partida = jogador.getHistoricoDePartidas().get(chavePartida);

		new TelaReverPartida(partida);

	}

}

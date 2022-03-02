package Tela.Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import Classes.Jogador;
import Classes.Mapa;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.MapaGrafico;
import Tela.TelaEditarFrota;
import Tela.Icones.FabricaDeIcones;

public class ClicleBotaoSalvarFrota implements ActionListener {

	private MapaGrafico mapaGrafico;
	private Jogador jogador;
	private CentralDeInformacoes centralDeInformacoes;
	private Mapa mapatemp;
	private TelaEditarFrota tela;

	public ClicleBotaoSalvarFrota(MapaGrafico mapaGrafico, Jogador jogador, CentralDeInformacoes centralDeInformacoes, TelaEditarFrota tela) {
		this.mapaGrafico = mapaGrafico;
		this.jogador = jogador;
		this.centralDeInformacoes = centralDeInformacoes;
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (mapaValido()) {
			
			centralDeInformacoes.getDesafiante().setMapa(this.mapatemp);

			Persistencia.salvarOuAtualizar(centralDeInformacoes);

			JOptionPane.showMessageDialog(null, "Mapa Salvo com Sucesso!!!");
			
			tela.dispose();
			
		} else {
			JOptionPane.showMessageDialog(null, "Mapa Inválido");
		}
	}

	private boolean mapaValido() {

		this.mapatemp = new Mapa(jogador.getMapa().getDimensao(), jogador.getMapa().getQuantidadeDeNavios());

		int[][] frota = this.mapatemp.getFrota();

		JButton[][] frotaGrafica = mapaGrafico.getFrota();

		for (int i = 0; i < frotaGrafica.length; i++) {
			for (int j = 0; j < frotaGrafica.length; j++) {

				if (frotaGrafica[i][j].getIcon().toString().equals(tela.getFabricaDeIcones().getIconeAgua().toString())) {
					frota[i][j] = Mapa.AGUA;
				} else {
					frota[i][j] = Mapa.NAVIO_OCULTO;
				}
			}
		}

		return this.mapatemp.ehValido();
	}
}

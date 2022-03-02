package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Classes.Dimensao;
import Classes.Jogada;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Tela.Icones.FabricaDeIcones;
import Tela.Ouvinte.CliqueBotaoBatalha;
import Tela.Ouvinte.CliqueBotaoPC;
import Tela.Ouvinte.CliqueReplay;

public class TelaReverPartida extends TelaPadrao {

	private MapaGrafico mapaDoDesafiado;
	private MapaGrafico mapaDoDesafiante;

	public TelaReverPartida(Partida partida) {
		super(partida, "Rever Partida");
	}

	@Override
	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {
		this.mapaDoDesafiante = new MapaGrafico(this.reverPartida.getMapaDesafiante().getDimensao());
		this.mapaDoDesafiado = new MapaGrafico(this.reverPartida.getMapaDesafiado().getDimensao());
	}

	@Override
	protected void escolherLayout() {
		setLayout(new BorderLayout());
	}

	@Override
	protected void adicionarComponentes() {

		String desafiado = reverPartida.getDesafiado().getLogin();
		String desafiante = reverPartida.getDesafiante().getLogin();

		JPanel panelTituloJogo = criarPanelComTitulo(desafiante, desafiado);
		panelTituloJogo.setBackground(Color.BLACK);

		JPanel panelMapas = criarPanelComOsMapas();
		panelMapas.setBackground(Color.BLACK);

		JPanel panelButton = criarPanelButton();
				
		add(panelTituloJogo, BorderLayout.NORTH);
		add(panelMapas, BorderLayout.CENTER);
		add(panelButton,BorderLayout.SOUTH);
		
		replay();
	}

	private JPanel criarPanelButton() {
		TelaReverPartida tela = this;
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		JButton jButton = new JButton();
		jButton.setIcon(fabricaDeIcones.getIconeReplay());
		jButton.setBackground(Color.BLACK);
		panel.add(jButton);
		
		jButton.addActionListener(new ActionListener() {
			List<Jogada> jogadas = reverPartida.getJogadas();
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jogadas.size(); i++) {

					Jogada jogada = jogadas.get(i);

					int x = jogada.getX();
					int y = jogada.getY();
					
					if (reverPartida.getDesafiado().equals(jogada.getJogador())) {

						mapaDoDesafiante.getFrota()[x][y].setEnabled(true);
						mapaDoDesafiante.getFrota()[x][y].doClick();
						
					} else {
						mapaDoDesafiado.getFrota()[x][y].setEnabled(true);
						mapaDoDesafiado.getFrota()[x][y].doClick();
					}
					
					int resposta = JOptionPane.showConfirmDialog(null,"Ver próxima Jogada?");
					
					if(resposta == JOptionPane.NO_OPTION || resposta == JOptionPane.CANCEL_OPTION) {
						new TelaReverPartida(reverPartida);
						tela.dispose();
						
						break;
					}
					
				}		
				
			}
		});
		
		return panel;
	}

	private JPanel criarPanelComOsMapas() {
		JPanel panelMapas = new JPanel();
		panelMapas.setBackground(Color.BLACK);
		panelMapas.setLayout(new GridLayout(1, 1, 20, 20));

		panelMapas.add(this.mapaDoDesafiante.getJPanel(), BorderLayout.WEST);
		panelMapas.add(this.mapaDoDesafiado.getJPanel(), BorderLayout.EAST);

		return panelMapas;
	}

	private JPanel criarPanelComTitulo(String desafiante, String desafiado) {
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLACK);
		panelTitulo.setLayout(new GridLayout(1, 3));

		JLabel nomeDesafiante = new JLabel();
		nomeDesafiante.setText(desafiante);
		nomeDesafiante.setFont(new Font("Arial", Font.BOLD, 50));
		nomeDesafiante.setForeground(Color.ORANGE);
		nomeDesafiante.setHorizontalAlignment(JLabel.CENTER);

		JLabel titulo = new JLabel();
		titulo.setIcon(fabricaDeIcones.getIconeBatalhaNaval());
		titulo.setHorizontalAlignment(JLabel.CENTER);

		JLabel nomeDesafiado = new JLabel();
		nomeDesafiado.setText(desafiado);
		nomeDesafiado.setFont(new Font("Arial", Font.BOLD, 50));
		nomeDesafiado.setForeground(Color.ORANGE);
		nomeDesafiado.setHorizontalAlignment(JLabel.CENTER);

		panelTitulo.add(nomeDesafiante);
		panelTitulo.add(titulo);
		panelTitulo.add(nomeDesafiado);

		return panelTitulo;
	}

	private void replay() {

		desabilitarClick();
		List<Jogada> jogadas = reverPartida.getJogadas();

		for (int i = 0; i < jogadas.size(); i++) {

			Jogada jogada = jogadas.get(i);

			int x = jogada.getX();
			int y = jogada.getY();

			if (reverPartida.getDesafiado().equals(jogada.getJogador())) {

				if (jogada.getDescricao().contains("navio")) {
					this.mapaDoDesafiante.getFrota()[x][y].addActionListener(
							new CliqueReplay(this.mapaDoDesafiante.getFrota()[x][y], fabricaDeIcones.getIconeNavio()));
				
				} else {
					this.mapaDoDesafiante.getFrota()[x][y].addActionListener(
							new CliqueReplay(this.mapaDoDesafiante.getFrota()[x][y], fabricaDeIcones.getIconeAgua()));
				
				}

			} else {
				
				if (jogada.getDescricao().contains("navio")) {
					this.mapaDoDesafiado.getFrota()[x][y].addActionListener(
							new CliqueReplay(this.mapaDoDesafiado.getFrota()[x][y], fabricaDeIcones.getIconeNavio()));
				
				} else {
					this.mapaDoDesafiado.getFrota()[x][y].addActionListener(
							new CliqueReplay(this.mapaDoDesafiado.getFrota()[x][y], fabricaDeIcones.getIconeAgua()));
				}
			}	
		}
	}

	private void desabilitarClick() {
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				this.mapaDoDesafiado.getFrota()[i][j].setEnabled(false);
				this.mapaDoDesafiante.getFrota()[i][j].setEnabled(false);
			}
		}	
	}
}

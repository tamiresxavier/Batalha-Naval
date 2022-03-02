package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Classes.Jogador;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Tela.Icones.FabricaDeIcones;
import Tela.Ouvinte.CliqueBotaoBatalha;
import Tela.Ouvinte.CliqueBotaoPC;

/**
 * Classe TelaMapa,  responsável por manter exibir a parte gráfica do jogo.
 * 
 * @author Nataly Lucena
 *
 */
public class TelaMapa extends TelaPadrao {

	private MapaGrafico mapaDoDesafiado;
	private MapaGrafico mapaDoDesafiante;

	public TelaMapa() {
		super("Batalha Naval");
		inicializaAtributos(centralDeInformacoes);
		escolherLayout();
		adicionarComponentes();
	}

	/**
	 * Classe inicializaAtributos,  responsável por definir o tamanho dos mapas
	 * @param CentralDeInformacoes
	 * @author Nataly Lucena
	 *
	 */
	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {

		this.mapaDoDesafiante = new MapaGrafico(centralDeInformacoes.getDesafiante().getMapa().getDimensao());
		this.mapaDoDesafiado = new MapaGrafico(centralDeInformacoes.getDesafiado().getMapa().getDimensao());

	}

	/**
	 * Método escolherLayout,  responsável por definir o gerenciador de layout
	 * @param 
	 * @author Nataly Lucena
	 *
	 */
	public void escolherLayout() {
		setLayout(new BorderLayout());
	}

	protected void adicionarComponentes() {
		String desafiado = centralDeInformacoes.getDesafiado().getLogin();
		String desafiante = centralDeInformacoes.getDesafiante().getLogin();

		JPanel panelTituloJogo = criarPanelComTitulo(desafiante, desafiado);
		panelTituloJogo.setBackground(Color.BLACK);

		JPanel panelMapas = criarPanelComOsMapas();
		panelMapas.setBackground(Color.BLACK);

		add(panelTituloJogo, BorderLayout.NORTH);
		add(panelMapas, BorderLayout.CENTER);

		mapaDoDesafiante.desabilitarClick();
		centralDeInformacoes.inicializaPartida();
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

	private JPanel criarPanelComOsMapas() {
		JPanel panelMapas = new JPanel();
		panelMapas.setBackground(Color.BLACK);
		panelMapas.setLayout(new GridLayout(1, 1, 20, 20));

		panelMapas.add(this.mapaDoDesafiante.getJPanel(), BorderLayout.WEST);
		panelMapas.add(this.mapaDoDesafiado.getJPanel(), BorderLayout.EAST);

		inicializarOuvintesDoDesafiado();
		inicializarOuvintesDoDesafiante();

		return panelMapas;
	}

	private void inicializarOuvintesDoDesafiante() {

		JButton[][] frotaGrafica = this.mapaDoDesafiante.getFrota();
		int[][] frota = this.centralDeInformacoes.getDesafiante().getMapa().getFrota();

		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {

				if (frota[i][j] > 0) {
					frotaGrafica[i][j]
							.addActionListener(new CliqueBotaoPC(frotaGrafica[i][j], fabricaDeIcones.getIconeNavio()));
				} else {
					frotaGrafica[i][j]
							.addActionListener(new CliqueBotaoPC(frotaGrafica[i][j], fabricaDeIcones.getIconeAgua()));
				}
			}
		}
	}

	private void inicializarOuvintesDoDesafiado() {

		JButton[][] frotaGrafica = this.mapaDoDesafiado.getFrota();
		int[][] frota = this.centralDeInformacoes.getDesafiado().getMapa().getFrota();

		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {

				frotaGrafica[i][j].addActionListener(
						new CliqueBotaoBatalha(frotaGrafica[i][j], i, j, this, centralDeInformacoes));
			}
		}
	}

	public MapaGrafico getMapaGraficoDoDesafiado() {
		return mapaDoDesafiado;
	}

	public MapaGrafico getMapaGraficoDoDesafiante() {
		return mapaDoDesafiante;
	}

	public void exibeResultado() {

		TelaResultadoPartida telaResultadoPartida = new TelaResultadoPartida();
		telaResultadoPartida.setVisible(true);
	}

	public boolean jaTemUmVencedor() {
		return centralDeInformacoes.jaTemUmVencedor();
	}

	public Partida getPartida() {

		return centralDeInformacoes.getPartidaAtual();
	}

	public boolean ataqueDoPC(Jogador jogadorAtacado) {

		return centralDeInformacoes.ehUmJogadorSimulado(jogadorAtacado);
	}

	public CentralDeInformacoes getCentralDeInformacoes() {

		return this.centralDeInformacoes;
	}

}

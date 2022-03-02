package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Dimensao;
import Classes.Jogador;
import Classes.Mapa;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.Icones.FabricaDeIcones;
import Tela.Ouvinte.ClicleBotaoSalvarFrota;
import Tela.Ouvinte.CliqueBotaoEditaFrota;

public class TelaEditarFrota  extends TelaPadrao {

	private MapaGrafico mapaGrafico;
	private Jogador jogador;
	public TelaEditarFrota() {
		super("Editar Frota");
	}

	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {
		
		CentralDeInformacoes recuperarCentral = Persistencia.recuperarCentral();
		this.jogador = recuperarCentral.getDesafiante();
		this.mapaGrafico = new MapaGrafico(this.jogador.getMapa().getDimensao());	
	}

	protected void escolherLayout() {
		setLayout(new BorderLayout());	
	}

	protected void adicionarComponentes() {		
		add(criarPanelTitulo(), BorderLayout.NORTH);
		add(criarPanelComMapa(), BorderLayout.CENTER);
		add(criarPanelJButtonSalvar(), BorderLayout.SOUTH);				
	}

	private JPanel criarPanelTitulo() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBackground(Color.BLACK);
		
		JLabel icone = new JLabel(fabricaDeIcones.getIconeBatalhaNaval());		
		JLabel nome = criarLabelNomeJogador();
		
		panel.add(icone);
		panel.add(nome);
		
		return panel;
	}

	private JPanel criarPanelJButtonSalvar() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.BLACK);		
		JButton jBsalvar = new JButton();
		jBsalvar.setText("Salvar");
		jBsalvar.setFont(new Font("Arial", Font.BOLD, 30));
		jBsalvar.setForeground(Color.WHITE);
		jBsalvar.setBackground(new Color(205, 102, 29));		
		jBsalvar.addActionListener(new ClicleBotaoSalvarFrota(this.mapaGrafico,jogador, centralDeInformacoes,this));		
		panel.add(jBsalvar);
		return panel;
	}

	private JLabel criarLabelNomeJogador() {
		JLabel jLNomeJogador = new JLabel();
		jLNomeJogador.setText("ATUALIZE SEU MAPA");	
		jLNomeJogador.setFont(new Font("Arial", Font.BOLD, 40));
		jLNomeJogador.setForeground(Color.ORANGE);
		jLNomeJogador.setHorizontalAlignment(JLabel.CENTER);
		return jLNomeJogador;
	}
	
	private JPanel criarPanelComMapa() {
		JPanel panelMapa = this.mapaGrafico.getJPanel();
		panelMapa.setLayout(new GridLayout(5,5));
		inicializarOuvintesDoDesafiado();	
		return panelMapa;
	}

	private void inicializarOuvintesDoDesafiado() {
		
		JButton[][] frotaGrafica = this.mapaGrafico.getFrota();
		int[][] frota = this.jogador.getMapa().getFrota();		
		
		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {
				
				frotaGrafica[i][j].addActionListener(new CliqueBotaoEditaFrota(frotaGrafica[i][j]));
				
				if(frota[i][j] == Mapa.AGUA) {
					frotaGrafica[i][j].setIcon(fabricaDeIcones.getIconeAgua());
				} else {
					frotaGrafica[i][j].setIcon(fabricaDeIcones.getIconeNavio());
				}
			}
		}
	}
}

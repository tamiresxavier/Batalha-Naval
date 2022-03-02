package Tela;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Classes.Audio;
import Gerenciamento.CentralDeInformacoes;
import Relatorio.GeradorDeRelatorio;
import Tela.Icones.FabricaDeIcones;

public class TelaHome extends TelaPadrao {

	private Audio audio;

	public TelaHome() {
		super("Tela Inicial");
	}

	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {
		audio = new Audio("som.mp3");
		setTitle("Tela Inicial - Usário logado: " + centralDeInformacoes.getDesafiante().getLogin());
	}

	protected void escolherLayout() {
		setLayout(new BorderLayout());

	}

	protected void adicionarComponentes() {

		JPanel panelBackground = new JPanel();
		panelBackground.setLayout(new GridLayout(1,1));

		JLabel imagem = new JLabel();
		imagem.setIcon(fabricaDeIcones.getIconeBackground());
		imagem.setAlignmentX(CENTER_ALIGNMENT);

		panelBackground.add(imagem, BorderLayout.CENTER);

		JMenuBar jMenuBar = new JMenuBar();
		this.setJMenuBar(jMenuBar);

		addMenuArquivo(jMenuBar);
		addMenuHistorico(jMenuBar);
		addMenuRelatorios(jMenuBar);

		add(panelBackground, BorderLayout.CENTER);

		audio.play();
	}
	
	private void addMenuRelatorios(JMenuBar jMenuBar) {
		
		GeradorDeRelatorio geradorDeRelatorio = new GeradorDeRelatorio();
		
		JMenu jMenuRelatorios = new JMenu("Relatórios");
		jMenuBar.add(jMenuRelatorios);
		
		JMenuItem relatorioPartidasGanhas = new JMenuItem("Relatório De Partidas Ganhas");
		jMenuRelatorios.add(relatorioPartidasGanhas);
		relatorioPartidasGanhas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					geradorDeRelatorio.gerarRelatorioPartidasGanhas(centralDeInformacoes.getDesafiante());
					JOptionPane.showMessageDialog(null, "Relatório de partidas ganhas gerado com sucesso!");
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao gerar o relatório.");
				}
			}
		});

		JMenuItem relatorioPartidasPerdidas = new JMenuItem("Relatório De Partidas Perdidas");
		jMenuRelatorios.add(relatorioPartidasPerdidas);
		relatorioPartidasPerdidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					geradorDeRelatorio.gerarRelatorioPartidasPerdidas(centralDeInformacoes.getDesafiante());
					JOptionPane.showMessageDialog(null, "O relatório foi gerado com sucesso!.");
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao gerar o relatório.");
				}
			}
			
		});

	}

	private void addMenuHistorico(JMenuBar jMenuBar) {
		JMenu jMenuHistorico = new JMenu("Histórico");
		jMenuBar.add(jMenuHistorico);

		JMenuItem minhasPartidas = new JMenuItem("Minhas Partidas");
		minhasPartidas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaHistorico();

			}
		});
		jMenuHistorico.add(minhasPartidas);

	}

	private void addMenuArquivo(JMenuBar jMenuBar) {

		JFrame tela = this;
		JMenu jMenuArquivo = new JMenu("Arquivo");
		jMenuBar.add(jMenuArquivo);

		JMenuItem ranking = new JMenuItem("Lista de Jogadores (Ranking)");
		jMenuArquivo.add(ranking);

		ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new TelaRanking();

			}
		});

		JMenuItem frota = new JMenuItem("Editar Frota");
		jMenuArquivo.add(frota);
		frota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEditarFrota();
			}
		});

		JMenuItem excluirConta = new JMenuItem("Excluir Conta");
		jMenuArquivo.add(excluirConta);
		excluirConta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new TelaExcluirConta(centralDeInformacoes, tela);

			}
		});

		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new TelaLogin();
				tela.dispose();

			}
		});
		jMenuArquivo.add(sair);
	}
}
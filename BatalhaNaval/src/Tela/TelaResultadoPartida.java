package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import Classes.Jogada;
import Gerenciamento.CentralDeInformacoes;

public class TelaResultadoPartida extends TelaPadrao {

	public TelaResultadoPartida() {
		super("Resultado");
	}

	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {
	
	}

	protected void escolherLayout() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	protected void adicionarComponentes() {

		JPanel panelTitulo = criarPanelTitulo();
		JPanel panelTabela = criarPanelTabelaResultado();
		JPanel panelButton = criarPanelButtonReverPartida();

		add(panelTitulo, BorderLayout.NORTH);
		add(panelTabela, BorderLayout.CENTER);
		add(panelButton, BorderLayout.SOUTH);
	}

	private JPanel criarPanelButtonReverPartida() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.BLACK);

		JButton jButton = new JButton();
		jButton.setText("Rever Partida");
		jButton.setFont(new Font("Arial", Font.BOLD, 30));
		jButton.setForeground(Color.WHITE);
		jButton.setBackground(new Color(205, 102, 29));

		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new TelaReverPartida(centralDeInformacoes.getPartidaAtual());

			}
		});

		panel.add(jButton);

		return panel;
	}

	private JPanel criarPanelTabelaResultado() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		DefaultTableModel defaultTableModel = new DefaultTableModel();

		defaultTableModel.addColumn("DATA E HORA");
		defaultTableModel.addColumn("AÇÃO");
		List<Jogada> jogadas = centralDeInformacoes.getPartidaAtual().getJogadas();

		for (Jogada jogada : jogadas) {
			Object[] linha = new Object[2];
			linha[0] = jogada.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha[1] = jogada.getDescricao();
			defaultTableModel.addRow(linha);
		}

		JTable jTable = new JTable(defaultTableModel);
		jTable.setAlignmentX(CENTER_ALIGNMENT);

		jTable.getColumn("DATA E HORA").setPreferredWidth(50);
		jTable.getColumn("AÇÃO").setPreferredWidth(350);
		
		JScrollPane jScrollPane = new JScrollPane(jTable);

		panel.add(jScrollPane);

		return panel;
	}

	private JPanel criarPanelTitulo() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBackground(Color.BLACK);

		JLabel icone = new JLabel();
		icone.setIcon(fabricaDeIcones.getIconeBatalhaNaval());
		icone.setHorizontalAlignment(JLabel.CENTER);

		JLabel ranking = new JLabel();
		ranking.setText("VENCEDOR: " + centralDeInformacoes.getPartidaAtual().getVencedor().getLogin());
		
		ranking.setFont(new Font("Arial", Font.BOLD, 40));
		ranking.setForeground(Color.ORANGE);
		ranking.setHorizontalAlignment(JLabel.CENTER);

		panel.add(icone);
		panel.add(ranking);
		return panel;
	}
}

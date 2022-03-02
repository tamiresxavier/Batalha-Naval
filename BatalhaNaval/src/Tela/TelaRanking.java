package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Classes.Jogador;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.Icones.FabricaDeIcones;
import Tela.Ouvinte.CliqueRanking;

// https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event
// https://dicasdejava.com.br/java-8-como-formatar-localdate-e-localdatetime/
//https://stackoverflow.com/questions/7433602/how-to-center-in-jtable-cell-a-value
public class TelaRanking extends TelaPadrao {

	public TelaRanking() {
		super("Ranking");
	}

	@Override
	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {

	}

	@Override
	protected void escolherLayout() {

		setLayout(new BorderLayout());
	}

	@Override
	protected void adicionarComponentes() {

		JPanel panelTitulo = criarPanelTitulo();

		JPanel panelTabela = criarPanelTabelaRanking();

		add(panelTitulo, BorderLayout.NORTH);
		add(panelTabela, BorderLayout.CENTER);
	}

	private JPanel criarPanelTitulo() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBackground(Color.BLACK);

		JLabel icone = new JLabel();
		icone.setIcon(fabricaDeIcones.getIconeBatalhaNaval());
		icone.setHorizontalAlignment(JLabel.CENTER);

		JLabel ranking = new JLabel();
		ranking.setText("RANKING");
		ranking.setFont(new Font("Arial", Font.BOLD, 40));
		ranking.setForeground(Color.ORANGE);
		ranking.setHorizontalAlignment(JLabel.CENTER);

		panel.add(icone);
		panel.add(ranking);

		return panel;
	}

	private JPanel criarPanelTabelaRanking() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));

		DefaultTableModel defaultTableModel = new DefaultTableModel();

		defaultTableModel.addColumn("Jogador");
		defaultTableModel.addColumn("Total de Pontos");
		defaultTableModel.addColumn("Vitórias");
		defaultTableModel.addColumn("Derrotas");
		defaultTableModel.addColumn("Mapa atualizado em");

		HashMap<String, Jogador> todosOsJogadores = centralDeInformacoes.getTodosOsJogadores();

		Set<String> keys = todosOsJogadores.keySet();

		for (String login : keys) {
			Jogador jogador = todosOsJogadores.get(login);

			if(jogador.isExcluido() == false) {
				Object[] linha = new Object[6];	
				linha[0] = jogador.getLogin();
				linha[1] = jogador.getPontuacao();
				linha[2] = jogador.getQuantidadeVitorias();
				linha[3] = jogador.getQuantidadeDerotas();
				linha[4] = jogador.getMapa().getDataDeAtualizacao()
						.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
				defaultTableModel.addRow(linha);	
			}
		}

		JFrame tela = this;
		JTable jTable = new JTable(defaultTableModel);
		
		jTable.setAlignmentX(CENTER_ALIGNMENT);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		jTable.getSelectionModel().addListSelectionListener(new CliqueRanking(centralDeInformacoes, jTable, tela));

		JScrollPane jScrollPane = new JScrollPane(jTable);

		panel.add(jScrollPane);

		return panel;
	}
}

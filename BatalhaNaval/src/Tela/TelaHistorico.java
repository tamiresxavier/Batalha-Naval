package Tela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;

import Classes.Jogada;
import Classes.Jogador;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.Icones.FabricaDeIcones;
import Tela.Ouvinte.CliqueHistorico;

public class TelaHistorico extends TelaPadrao {

	private JLabel janela;
	private JTable tabela;
	private Jogador jogador;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public TelaHistorico() {

		super("Histórico de Partidas");
		
		getContentPane().setBackground(Color.BLACK);
	}

	@Override
	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {		
		jogador = centralDeInformacoes.getDesafiante();
	}

	@Override
	protected void escolherLayout() {
		setLayout(new GridBagLayout());
	}

	@Override
	protected void adicionarComponentes() {
		configurarPanel();

	}
	private void configurarPanel(){
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,0));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(600, 500));

		adicionarTabela(panel);
		adicionarBotao(panel);
		adicionarImagens(panel);
		add(panel);
	}

	private void adicionarImagens(JPanel panel) {
		JLabel icone = new JLabel();
		icone.setIcon(fabricaDeIcones.getIconeBatalhaNaval());
		icone.setBounds(0, 12, 600, 150);
		icone.setHorizontalAlignment(JLabel.CENTER);
		panel.add(icone);

	}
	private void adicionarTabela(JPanel panel) {
		
		TelaHistorico tela = this;
		
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("DATA DA PARTIDA ");
		modelo.addColumn("VENCEDOR");
		modelo.addColumn("PERDEDOR");
		modelo.addColumn("MINHA PONTUAÇÃO");

	
		HashMap<String, Partida> historicoDePartidas = jogador.getHistoricoDePartidas();
		HashMap<String, Integer> historicoDePontos = jogador.getHistoricoDePontos();
		
		Set<String> keySet = historicoDePartidas.keySet();
		
		for (String chave : keySet) {
			
			Partida partida = historicoDePartidas.get(chave);
			Object[] linha = new Object[4];
			linha [0] = partida.getData().toString();
			linha [1] = partida.getVencedor();
			linha [2] = partida.getPerdedor();
			linha [3] = historicoDePontos.get(chave);
			modelo.addRow(linha);
		}

		
		tabela = new JTable(modelo);
				
		JScrollPane container = new JScrollPane(tabela);
		container.setBounds(0,170,600,260);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);		
		tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tabela.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		
		tabela.getSelectionModel().addListSelectionListener(new CliqueHistorico(centralDeInformacoes, tabela));
		panel.add(container);

	}

	private void adicionarBotao(JPanel panel) {

		JButton back = new JButton("Voltar");
		back.setFont(new Font("Arial", Font.BOLD, 20));
		back.setForeground(Color.WHITE);
		back.setBackground(new Color(205, 102, 29));
		back.setBounds(180, 450, 150, 50);
		panel.add(back);
		back.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				dispose();
				//new TelaPrincipal();	
			}

		});
		JButton detail = new JButton("Detalhar"); 
		detail.setFont(new Font("Arial", Font.BOLD, 20));
		detail.setForeground(Color.WHITE);
		detail.setBackground(new Color(205, 102, 29));
		detail.setBounds(340, 450, 150, 50);
		panel.add(detail);
		
		
		detail.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();
				JTextArea saida = new JTextArea(20,20);
				saida.setWrapStyleWord(true);
				if(linha == -1){
					JOptionPane.showMessageDialog(null,"Selecone uma linha","Erro",JOptionPane.DEFAULT_OPTION);
				}else{
					String chaveDaPartida = (String) tabela.getValueAt(linha, 0);
					Partida historico = null;
					for(Map.Entry<String, Partida> historicoSalvo : centralDeInformacoes.getDesafiante().getHistoricoDePartidas().entrySet()){
						if(historicoSalvo.getKey().equals(chaveDaPartida)){
							historico = historicoSalvo.getValue();

						}
					}
					saida.append("Desafiado: " + historico.getDesafiado().getLogin());
					saida.append("\nVencedor: " + historico.getVencedor().getLogin());
					saida.append("\nPerdedor: " + historico.getPerdedor().getLogin());
					saida.append("\n\nMapa do desafiante: \n" + historico.getMapaDesafiante().toString());
					saida.append("\nMapa do desafiado: \n" + historico.getMapaDesafiado().toString());
					saida.append("\nJogadas da Partida: \n" + historico.getJogadas());
					saida.setEditable(false);
					JOptionPane.showMessageDialog(null, saida, "Partida Detalhada", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}); 
	}
}
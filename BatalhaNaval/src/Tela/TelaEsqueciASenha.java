package Tela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Alerta.Mensageiro;
import Alerta.RecursosEmail;
import Classes.Jogador;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;

public class TelaEsqueciASenha extends TelaPadrao {
	private JTextField login;

	public TelaEsqueciASenha() {
		super("Esqueci a senha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
	}

	protected void escolherLayout() {
		setLayout(new GridBagLayout());
	}

	protected void adicionarComponentes() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(350, 600));
		panel.setOpaque(false);

		adcionarLabels(panel);
		adcionarTextFields(panel);
		adicionarBotao(panel);

		add(panel);
	}

	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {
		
	}

	private void adcionarLabels(JPanel panel) {
		JLabel recuperarSenha = new JLabel("Recuperar senha");
		recuperarSenha.setFont(new Font("Arial", Font.BOLD, 28));
		recuperarSenha.setBounds(40, 60, 250, 30);
		recuperarSenha.setForeground(Color.WHITE);
		panel.add(recuperarSenha);

		JLabel login = new JLabel("Login");
		login.setFont(new Font("Arial", Font.BOLD, 13));
		login.setBounds(40, 118, 124, 15);
		login.setForeground(Color.WHITE);
		panel.add(login);
	}

	private void adcionarTextFields(JPanel panel) {
		login = new JTextField();
		login.setToolTipText("Ex: jogo@gmail.com");
		login.setForeground(Color.BLACK);
		login.setBackground(new Color(255, 255, 255));
		login.setBounds(40, 135, 282, 28);
		panel.add(login);
	}

	private void adicionarBotao(JPanel panel) {
		JButton enviar = new JButton("Enviar");
		enviar.setForeground(Color.WHITE);
		enviar.setBackground(new Color(205, 102, 29));
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (login.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo vazio.", "Atenção!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					RecursosEmail recurso = new RecursosEmail();
					
					Jogador jogador = centralDeInformacoes.recuperarJogador(login.getText());
					recurso.recuperarSenha(jogador.getEmail());
				}
			}
		});
		enviar.setBounds(182, 190, 140, 30);
		panel.add(enviar);

		JButton voltar = new JButton("Voltar");
		voltar.setForeground(Color.WHITE);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new TelaLogin();
			}
		});
		voltar.setBackground(new Color(205, 102, 29));
		voltar.setBounds(40, 190, 140, 30);
		panel.add(voltar);
	}

	public static void main(String[] agrs) {
		new TelaEsqueciASenha();
	}
}
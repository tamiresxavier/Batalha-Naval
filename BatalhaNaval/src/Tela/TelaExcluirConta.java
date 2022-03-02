package Tela;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPasswordField;

import javax.swing.JTextField;


import Classes.Jogador;

import Gerenciamento.CentralDeInformacoes;

import Persistencia.Persistencia;

public class TelaExcluirConta extends JFrame {

	private CentralDeInformacoes centralDeInformacoes;
	private JFrame telaHome;

	public TelaExcluirConta(CentralDeInformacoes centralDeInformacoes, JFrame telaHome) {

		this.centralDeInformacoes = centralDeInformacoes;
		this.telaHome = telaHome;
		setTitle("Excluir conta");

		setSize(850, 220);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		adicionarLabels();
		adicionarBotao();
		setVisible(true);
	}

	private void adicionarLabels() {

		JLabel excluirConta = new JLabel("Excluir conta");

		excluirConta.setFont(new Font("Arial", Font.BOLD, 28));

		excluirConta.setBounds(40, 20, 250, 30);

		excluirConta.setForeground(Color.WHITE);

		this.add(excluirConta);

		JLabel texto = new JLabel("Deseja excluir sua conta? Ficará impossibilitado de logar novamente na conta (todos os seus jogos continuarão salvos no sistema). ");

		texto.setFont(new Font("Arial", Font.BOLD, 12));

		texto.setBounds(40, 70, 800, 15);

		texto.setForeground(Color.WHITE);

		this.add(texto);

	}

	private void adicionarBotao() {

		JButton sim = new JButton("Sim");

		sim.setForeground(Color.WHITE);

		sim.setBackground(new Color(205, 102, 29));

		sim.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null, "A sua conta foi excluída!");
				dispose();
				telaHome.dispose();

				new TelaLogin();
			}

		});

		sim.setBounds(412, 120, 100, 25);

		this.add(sim);

		JButton nao = new JButton("Não");

		nao.setForeground(Color.WHITE);

		nao.setBackground(new Color(205, 102, 29));

		nao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();

				new TelaHome();
			}
		});

		nao.setBounds(310, 120, 100, 25);

		this.add(nao);
	}
}
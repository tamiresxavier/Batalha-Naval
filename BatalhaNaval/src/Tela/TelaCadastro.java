package Tela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Classes.Jogador;
import Classes.ValidacaoSistema;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.Icones.FabricaDeIcones;


public class TelaCadastro extends TelaPadrao {
	private JTextField login;
	private JTextField email;
	private JTextField confirmarEmail;
	private JPasswordField senha;
	private JPasswordField confirmarSenha;

	public TelaCadastro() {
		
		super("Cadastrar-se");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK); 
		
	}
	
	protected void escolherLayout() {
		setLayout(new GridBagLayout());
	}

	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {
		
	}
	
	protected void adicionarComponentes() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 600));
		
		adicionarLabels(panel);
		adicionarJTextFields(panel);
		adicionarBotoes(panel);
		adicionarImagens(panel);
	
		add(panel);
	
	}
	
	private void adicionarImagens(JPanel panel) {
		
		JLabel imagem = new JLabel(fabricaDeIcones.getIconeBatalhaNaval());
		imagem.setBounds(125, -5, 250, 195);
		imagem.setHorizontalAlignment(JLabel.CENTER);
		
		panel.add(imagem);
	}
	
	private void adicionarLabels(JPanel panel){
		Font fonte = new Font("Arial", Font.BOLD, 12);
		
		JLabel apelido = new JLabel("Nome de exibição (Apelido)");
		apelido.setFont(fonte);
		apelido.setBounds(80, 170, 220, 15);
		apelido.setForeground(Color.WHITE);
		panel.add(apelido);
		
		JLabel email = new JLabel("Endereço de e-mail");
		email.setFont(fonte);
		email.setBounds(80, 245, 150, 15);
		email.setForeground(Color.WHITE);
		panel.add(email);
		
		JLabel confemail = new JLabel("Confirme seu endereço de e-mail");
		confemail.setFont(fonte);
		confemail.setBounds(80, 320, 200, 15);
		confemail.setForeground(Color.WHITE);
		panel.add(confemail);
		
		JLabel senha = new JLabel("Senha");
		senha.setFont(fonte);
		senha.setBounds(80, 395, 120, 15);
		senha.setForeground(Color.WHITE);
		panel.add(senha);
		
		JLabel confsenha = new JLabel("Confirme sua senha");
		confsenha.setFont(fonte);
		confsenha.setBounds(80, 470, 220, 15);
		confsenha.setForeground(Color.WHITE);
		panel.add(confsenha);
	}
	
	private void adicionarJTextFields(JPanel panel){
		
		login = new JTextField();
		login.setToolTipText("Ex: João1234");
		login.setForeground(Color.BLACK); 
		login.setBounds(80, 190, 350, 28);
		panel.add(login);
		
		email = new JTextField();
		email.setToolTipText("Ex: jogo@gmail.com");
		email.setForeground(Color.BLACK); 
		email.setBounds(80, 265, 350, 28);
		panel.add(email);
		
		confirmarEmail = new JTextField();
		confirmarEmail.setToolTipText("Ex: jogo@gmail.com");
		confirmarEmail.setForeground(Color.BLACK); 
		confirmarEmail.setBounds(80, 340, 350, 28);
		panel.add(confirmarEmail);
		
		senha = new JPasswordField();
		senha.setForeground(Color.BLACK); 
		senha.setBounds(80, 415, 350, 28);
		panel.add(senha);
		
		confirmarSenha = new JPasswordField();
		confirmarSenha.setForeground(Color.BLACK); 
		confirmarSenha.setBounds(80, 490, 350, 28);
		panel.add(confirmarSenha);
	}
	private void adicionarBotoes(JPanel panel){
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setForeground(Color.WHITE);
		cadastrar.setBackground(new Color(205, 102, 29));
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(login.getText().isEmpty() || email.getText().isEmpty() || confirmarEmail.getText().isEmpty() || new String(senha.getPassword()).isEmpty() || new String(confirmarSenha.getPassword()).isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencha os campos vazios.", "Atenção", JOptionPane.ERROR_MESSAGE);
				} else if(!ValidacaoSistema.validarEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "E-mail inválido!", "Atenção", JOptionPane.ERROR_MESSAGE);
				} else if(!(email.getText().equals(confirmarEmail.getText()))){
					JOptionPane.showMessageDialog(null, "O e-mail informado está diferente!", "Atenção", JOptionPane.ERROR_MESSAGE);
				} else if(!(new String(senha.getPassword()).equals((new  String(confirmarSenha.getPassword()))))){
					JOptionPane.showMessageDialog(null, "A senha informada está diferente!", "Atenção", JOptionPane.ERROR_MESSAGE);
				} else if(new String(senha.getPassword()).length() < 6) {
					JOptionPane.showMessageDialog(null, "A senha é no mínimo de 6 caracteres.", "Atenção!", JOptionPane.ERROR_MESSAGE);
				} else{
					Jogador novoJogador = new Jogador( login.getText(), new String(senha.getPassword()),email.getText());
					try {
						centralDeInformacoes.adicionarJogador(novoJogador);
						Persistencia.salvarOuAtualizar(centralDeInformacoes);
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
						dispose();
						new TelaLogin();
					} catch(Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
				
		});
		cadastrar.setBounds(257, 560, 174, 30);
		panel.add(cadastrar);
		
		JButton voltar = new JButton("Voltar");
		voltar.setForeground(Color.WHITE);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new TelaLogin();
			}

		});
		voltar.setBackground(new Color(205, 102, 29));
		voltar.setBounds(80, 560, 175, 30);
		panel.add(voltar);
	}
}

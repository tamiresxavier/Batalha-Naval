package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Classes.Jogador;
import Classes.ValidacaoSistema;
import Gerenciamento.CentralDeInformacoes;
import Tela.Icones.FabricaDeIcones;

public class TelaLogin extends TelaPadrao {
	private JTextField login;
	private JPasswordField senha;

	public TelaLogin() {

		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK); 

	}
	
	@Override
	protected void escolherLayout() {
		setLayout(new GridBagLayout());
	}

	@Override
	protected void adicionarComponentes() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 400));
		
		adcionarLabels(panel);
		adicionarTextFields(panel);
		adicionarBotao(panel);
		adicionarImagens(panel);
	
		add(panel);
	}
	
	@Override
	protected void inicializaAtributos(CentralDeInformacoes centralDeInformacoes) {

	}

	private void adicionarImagens(JPanel panel) {
		
		JLabel imagem = new JLabel(fabricaDeIcones.getIconeBatalhaNaval());
		JLabel avatar = new JLabel(fabricaDeIcones.getIconeAvatar());
		JLabel cadeado = new JLabel(fabricaDeIcones.getIconeSenha());
		
		imagem.setBounds(105, 10, 250, 195);
		avatar.setBounds(95, 195, 30, 28);
		cadeado.setBounds(95, 255, 30, 28);
		
		panel.add(imagem);
		panel.add(avatar);
		panel.add(cadeado);
	}
	
	private void adcionarLabels(JPanel panel) {
		JLabel jlLogin = new JLabel("Login");
		jlLogin.setFont(new Font("Arial", Font.BOLD, 12));
		jlLogin.setBounds(95, 178, 120, 15);
		jlLogin.setForeground(Color.WHITE);
		panel.add(jlLogin);

		JLabel senha = new JLabel("Senha");
		senha.setFont(new Font("Arial", Font.BOLD, 12));
		senha.setBounds(95, 238, 120, 15);
		senha.setForeground(Color.WHITE);
		panel.add(senha);
	}

	private void adicionarTextFields(JPanel panel) {
		login = new JTextField();
		login.setToolTipText("Ex: jogo@gmail.com");
		login.setForeground(Color.BLACK);
		login.setBackground(new Color(255, 255, 255));
		login.setBounds(125, 195, 250, 28);
		panel.add(login);

		senha = new JPasswordField();
		senha.setForeground(Color.BLACK);
		senha.setBackground(new Color(255, 255, 255));
		senha.setBounds(125, 255, 250, 28);
		panel.add(senha);
	}

	private void adicionarBotao(JPanel panel) {
		JButton jbLogin = new JButton("Login");
		jbLogin.setForeground(Color.WHITE);
		jbLogin.setBackground(Color.BLACK);
		jbLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (login.getText().isEmpty() || new String(senha.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha os campos vazios.", "Atenção!", JOptionPane.ERROR_MESSAGE);
				}else {
					Jogador jogadorAutenticado = centralDeInformacoes.login(login.getText(), new String( senha.getPassword())); 
					if(jogadorAutenticado == null) {
						JOptionPane.showMessageDialog(null, "Infelizmente, o login informado não consta no sistema.", "Atenção!", JOptionPane.ERROR_MESSAGE);
					}else {
						dispose();
						new TelaHome();
					}
					
				}
			}

		});
		jbLogin.setBounds(95, 290, 280, 30);
		panel.add(jbLogin);

		JButton esqueciASenha = new JButton("Esqueci a senha");
		esqueciASenha.setForeground(Color.WHITE);
		esqueciASenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TelaEsqueciASenha recuperar = new TelaEsqueciASenha();
			}

		});
		esqueciASenha.setBackground(new Color(205, 102, 29));
		esqueciASenha.setBounds(95, 340, 138, 30);
		panel.add(esqueciASenha);

		JButton cadastrar = new JButton("Cadastrar-se");
		cadastrar.setForeground(Color.WHITE);
		cadastrar.setBackground(new Color(205, 102, 29));
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TelaCadastro cadastro = new TelaCadastro();
			}

		});
		cadastrar.setBounds(235, 340, 140, 30);
		panel.add(cadastrar);
	}
}
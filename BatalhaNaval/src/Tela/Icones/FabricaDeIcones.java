package Tela.Icones;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FabricaDeIcones {

	public  ImageIcon getIconeAgua() {
		return new ImageIcon(getClass().getClassLoader().getResource("icones/agua.jpg"));
	}

	public  ImageIcon getIconeNavio() {

		return new ImageIcon(getClass().getClassLoader().getResource("icones/navioDerrotado.jpg"));
	}
	
	public  ImageIcon getIconeBatalhaNaval() {

		return new ImageIcon(getClass().getClassLoader().getResource("icones/batalha_naval.png"));
	}

	public  ImageIcon getIconeAvatar() {

		return new ImageIcon(getClass().getClassLoader().getResource("icones/avatar.jpg"));
	}

	public  ImageIcon getIconeSenha() {

		return new ImageIcon(getClass().getClassLoader().getResource("icones/senha.jpg"));
	}
	
	public  ImageIcon getIconeReplay() {

		return new ImageIcon(getClass().getClassLoader().getResource("icones/replay-icon.png"));
	}

	public  ImageIcon getIconeBackground() {
		
		return new ImageIcon(getClass().getClassLoader().getResource("icones/background.jpg"));
	}

	public  JButton getButtonNavio() {
		JButton jButton = new JButton();
		jButton.setIcon(getIconeNavio());
		return jButton;
	}

	public  JButton getButtonAgua() {
		JButton jButton = new JButton();
		jButton.setIcon(getIconeAgua());
		return jButton;		
	}
}

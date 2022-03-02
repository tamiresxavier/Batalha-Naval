package Tela.Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CliqueReplay implements ActionListener {

	private JButton jButton;
	private ImageIcon imageIcon;
	public CliqueReplay(JButton jButton, ImageIcon imageIcon) {
		this.jButton = jButton;
		this.imageIcon = imageIcon;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		this.jButton.setIcon(imageIcon);
		this.jButton.setOpaque(false);
		
	}
}

package Tela.Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class CliqueBotaoPC implements ActionListener {

	private JButton button;
	private ImageIcon imageIcon;

	public CliqueBotaoPC(JButton button,ImageIcon imageIcon) {
		this.button = button;
		this.imageIcon = imageIcon;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.button.removeActionListener(this.button.getActionListeners()[0]);
		this.button.setIcon(imageIcon);
		this.button.setDisabledIcon(imageIcon);
		this.button.setDisabledSelectedIcon(imageIcon);
		this.button.setPressedIcon(imageIcon);
		this.button.setOpaque(false);
		this.button.setRolloverIcon(imageIcon);
		this.button.setRolloverSelectedIcon(imageIcon);
		this.button.setSelectedIcon(imageIcon);

		this.button.setEnabled(false);
	}	
}

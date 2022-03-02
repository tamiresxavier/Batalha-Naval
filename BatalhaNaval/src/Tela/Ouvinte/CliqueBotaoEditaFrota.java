package Tela.Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import Tela.Icones.FabricaDeIcones;

public class CliqueBotaoEditaFrota implements ActionListener {
		
	private JButton jButton;
	private FabricaDeIcones fabricaDeIcones;
	public CliqueBotaoEditaFrota(JButton jButton) {
		this.jButton = jButton;
		this.fabricaDeIcones = new FabricaDeIcones();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(jButton.getIcon().toString().equals(fabricaDeIcones.getIconeAgua().toString())) {
			jButton.setIcon(fabricaDeIcones.getIconeNavio());
		}else if(jButton.getIcon().toString().equals(fabricaDeIcones.getIconeNavio().toString())){
			jButton.setIcon(fabricaDeIcones.getIconeAgua());
		}
		
	}

}

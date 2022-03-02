package Tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import Classes.Dimensao;

/**
 * Classe MapaGrafico, responsável por gerar as grades de cada Jogador
 * 
 * @author Nataly Lucena
 *
 */
public class MapaGrafico {

	private JButton[][] frota;

	public MapaGrafico(Dimensao dimensao) {
		instanciarFrota(dimensao.getLinhas(), dimensao.getColunas());
	}

	/**
	* Método getJPanel, responsável por criar um JPanel contendo os JButtons da grade do Jogo
	* @param 
	* @author Nataly Lucena
	*/
	public JPanel getJPanel() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout(5, 5));

		panel.setLayout(new GridLayout(frota.length, frota.length));
		for (int i = 0; i < frota.length; i++) {
			for (int j = 0; j < frota.length; j++) {
				panel.add(frota[i][j]);
			}
		}

		return panel;
	}

	private void instanciarFrota(int linhas, int colunas) {

		JButton[][] frotaGrafica = new JButton[linhas][colunas];
		this.frota = frotaGrafica;

		for (int i = 0, y = 70; i < linhas; i++, y += 75) {
			for (int j = 0, x = 40; j < colunas; j++, x += 75) {
				JButton button = criarBotao(i, j, x, y);
				frotaGrafica[i][j] = button;
			}
		}
	}

	public JButton[][] getFrota() {
		return frota;
	}

	public void setFrota(JButton[][] frota) {
		this.frota = frota;
	}

	private JButton criarBotao(int i, int j, int x, int y) {

		JButton button = new JButton();

		return button;
	}
	
	/**
	* Método desabilitaClick, responsável por desativar os cliques dos JButtons 
	*  
	* @param 
	* @author Nataly Lucena
	*/
	public void desabilitarClick() {

		for (int i = 0; i < frota.length; i++) {

			for (int j = 0; j < frota.length; j++) {

				frota[i][j].setEnabled(false);

			}
		}
	}
}

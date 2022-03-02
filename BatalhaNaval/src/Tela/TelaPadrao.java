package Tela;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;
import Tela.Icones.FabricaDeIcones;

/**
 * Classe TelaPadrão, foi criar com o intuito de reunir as características comuns às telas, tanto gráficas como lógicas (ela recupera a central de informações na instanciação)
 * 
 * @author Nataly Lucena
 *
 */
public abstract class TelaPadrao extends JFrame {
	protected CentralDeInformacoes centralDeInformacoes;
	protected Partida reverPartida;
	protected FabricaDeIcones fabricaDeIcones;

	public TelaPadrao(String titulo) {
		this.centralDeInformacoes = Persistencia.recuperarCentral();
		this.fabricaDeIcones = new FabricaDeIcones();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(titulo);
		inicializaAtributos(centralDeInformacoes);
		escolherLayout();		
		adicionarComponentes();
		exibeTelaCheia();
		setVisible(true);
		
	}
	
	public TelaPadrao(Partida reverPartida, String titulo) {
		this.centralDeInformacoes = Persistencia.recuperarCentral();
		this.fabricaDeIcones = new FabricaDeIcones();
		this.reverPartida = reverPartida;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(titulo);
		inicializaAtributos(centralDeInformacoes);
		escolherLayout();		
		adicionarComponentes();
		exibeTelaCheia();
		setVisible(true);
		
	}

	/**
	 * Método inicializaAtributos,  metodo responsável por concetrar a inicialização dos atributos das telas 
	 * 
	 * @author Nataly Lucena
	 *
	 */
	protected abstract void inicializaAtributos(CentralDeInformacoes centralDeInformacoes);
	
	/**
	 * Método escolherLayout,  metodo responsável por definir o gerenciamento de layout da tela
	 * 
	 * @author Nataly Lucena
	 *
	 */
	protected abstract void escolherLayout();
	
	/**
	 * Método adicionarComponentes,  metodo responsável por iniciar a construção dos componentes da tela.
	 * 
	 * @author Nataly Lucena
	 *
	 */

	protected abstract void adicionarComponentes();

	/**
	 * Método exibeTelaCheia,  responsável por deixar todas as telas em formato tela cheia.
	 * 
	 * @author Nataly Lucena
	 *
	 */

	private void exibeTelaCheia() {

		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int width = d.width - (insets.left + insets.top);
		int height = d.height - (insets.top + insets.bottom);

		setSize(width, height);
		setLocation(insets.left, insets.top);

  //	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

	public FabricaDeIcones getFabricaDeIcones() {
		return fabricaDeIcones;
	}

	public void setFabricaDeIcones(FabricaDeIcones fabricaDeIcones) {
		this.fabricaDeIcones = fabricaDeIcones;
	}
}

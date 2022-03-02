package Tela.Ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JButton;
import Classes.Jogada;
import Classes.Jogador;
import Classes.Partida;
import Gerenciamento.CentralDeInformacoes;
import Tela.MapaGrafico;
import Tela.TelaMapa;
import Tela.Icones.FabricaDeIcones;

/**
 * Classe CliqueBotaoBatalha, responsável por gerenciar o Jogo
 * 
 * @author Nataly Lucena
 *
 */
public class CliqueBotaoBatalha implements ActionListener {

	private JButton button;
	private TelaMapa tela;
	private CentralDeInformacoes centralDeInformacoes;
	private int x;
	private int y;
	private FabricaDeIcones fabricaDeIcones;

	public CliqueBotaoBatalha(JButton button, int x, int y, TelaMapa tela, CentralDeInformacoes centralDeInformacoes) {
		this.button = button;
		this.tela = tela;
		this.centralDeInformacoes = centralDeInformacoes;
		this.x = x;
		this.y = y;
		this.fabricaDeIcones = new FabricaDeIcones();
	}

	/**
	 * Método actionPerformed, responsável por orquestrar as ações do Jogo, executar
	 * o clique do desafiante (que sempre será o jogador logado) e a ação do
	 * computador manipulando um outro jogador
	 * 
	 * @param
	 * @author Nataly Lucena
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (centralDeInformacoes.getPartidaAtual().getVencedor() == null) {

			executarClickDesafiante();
		}
	}

	/**
	 * Método executarClickDesafiante, responsável pela ação do desafiante
	 * 
	 * @param
	 * @author Nataly Lucena
	 */

	private void executarClickDesafiante() {

		Jogador desafiado = centralDeInformacoes.getDesafiado();

		boolean desafianteNaoAcertouEmbarcacao = false;

		if (desafiado.getMapa().temEmBarcacao(x, y)) {
			desafiado.getMapa().embarcacaoAtacada(x, y);
			this.button.setIcon(fabricaDeIcones.getIconeNavio());
			desafianteNaoAcertouEmbarcacao = true;
		} else {
			this.button.setIcon(fabricaDeIcones.getIconeAgua());
		}

		this.button.removeMouseListener(this.button.getMouseListeners()[0]);

		adicionarJogada(centralDeInformacoes.getDesafiante(), desafianteNaoAcertouEmbarcacao, this.x, this.y);

		boolean houveVencedorAposJogada = verificaHouveVencedorAposJogada();

		if (!houveVencedorAposJogada && !desafianteNaoAcertouEmbarcacao) {

			boolean acertou = executarClickDoComputador();

			while (acertou) {
				acertou = executarClickDoComputador();
			}

			verificaHouveVencedorAposJogada();
		}
	}

	/**
	 * Método executarClickDoComputador, responsável pela ação do computador
	 * 
	 * @param
	 * @author Nataly Lucena
	 */
	private boolean executarClickDoComputador() {

		boolean acertou = false;

		Jogador desafiante = centralDeInformacoes.getDesafiante();
		int tamanhoMatriz = desafiante.getMapa().getDimensao().getLinhas();

		int x = (int) (Math.random() * tamanhoMatriz);
		int y = (int) (Math.random() * tamanhoMatriz);

		boolean clicavel = botaoEhClicavel(x, y);

		while (clicavel == false) {

			x = (int) (Math.random() * tamanhoMatriz);
			y = (int) (Math.random() * tamanhoMatriz);

			clicavel = botaoEhClicavel(x, y);
		}

		JButton jButton = this.tela.getMapaGraficoDoDesafiante().getFrota()[x][y];
		if (desafiante.getMapa().temEmBarcacao(x, y)) {
			desafiante.getMapa().embarcacaoAtacada(x, y);
			jButton.setEnabled(true);
			jButton.doClick(50);
			jButton.setEnabled(false);
			acertou = true;
		} else {
			jButton.setEnabled(true);
			jButton.doClick(50);

			jButton.setEnabled(false);
		}

		adicionarJogada(centralDeInformacoes.getDesafiado(), acertou, x, y);

		return acertou;
	}

	//////////////////// Métodos Auxiliares
	/**
	 * Método verificaHouveVencedorAposJogada, a cada jogada é chamado para
	 * verificar se houve um vencedor
	 * 
	 * @param
	 * @author Nataly Lucena
	 */
	private boolean verificaHouveVencedorAposJogada() {

		if (centralDeInformacoes.jaTemUmVencedor()) {
			centralDeInformacoes.finalizaAPartida();
			// Persistencia.salvarOuAtualizar(centralDeInformacoes);
			removerActionListener(tela.getMapaGraficoDoDesafiado());
			removerActionListener(tela.getMapaGraficoDoDesafiante());

			tela.exibeResultado();
			return true;
		}
		return false;
	}

	/**
	 * Método botaoEhClicavel, necessário para auxiliar na geração da ação aleátoria
	 * do computador
	 * 
	 * @param x, y
	 * @author Nataly Lucena
	 */

	private boolean botaoEhClicavel(int x, int y) {

		if (tela.getMapaGraficoDoDesafiante().getFrota()[x][y].getIcon() != null) {
			return false;
		}

		return true;
	}

	/**
	 * Método adicionarJogada, responsável por fazer o registro de cada jogada
	 * 
	 * @param
	 * @author Nataly Lucena
	 */
	private void adicionarJogada(Jogador jogador, boolean acertouEmbarcacao, int x, int y) {

		String acertou = "O Jogador " + jogador.getLogin() + " clicou na posição (" + x + "," + y
				+ ") e acertou um navio!";

		String errou = "O Jogador " + jogador.getLogin() + " clicou na posição (" + x + "," + y + ") e acertou a água!";

		Partida partida = centralDeInformacoes.getPartidaAtual();

		Jogada jogada = new Jogada();

		jogada.setData(LocalDateTime.now());
		jogada.setX(x);
		jogada.setY(y);
		jogada.setJogador(jogador);
		jogada.setPartida(partida);

		if (acertouEmbarcacao) {
			jogada.setDescricao(acertou);
		} else {
			jogada.setDescricao(errou);
		}

		partida.getJogadas().add(jogada);
	}

	/**
	 * Método removerActionListener, responsável por remover os ActionListener dos
	 * botões já clicados
	 * 
	 * @param
	 * @author Nataly Lucena
	 */
	public void removerActionListener(MapaGrafico mapaGrafico) {

		JButton[][] frota = mapaGrafico.getFrota();

		for (int i = 0; i < frota.length; i++) {

			for (int j = 0; j < frota.length; j++) {
				if (frota[i][j].getActionListeners().length > 0) {

					frota[i][j].removeActionListener(frota[i][j].getActionListeners()[0]);
				}
			}
		}
	}
}

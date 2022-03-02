package Relatorio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import Classes.Jogador;
import Classes.Partida;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorio {

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private HashMap<String, Partida> historicoDePartidasGanhas;
	private HashMap<String, Partida> historicoDePartidasPerdidas;

	private void geradorDeRelatorio(HashMap<String, Partida> historicoDePartidas, String nomeDoArquivo)
			throws Exception {

		Rectangle ret = new Rectangle(PageSize.A4);
		Document documento = new Document(ret, 72, 72, 72, 72);
		FileOutputStream pdf = new FileOutputStream(nomeDoArquivo + ".pdf");
		PdfWriter.getInstance(documento, pdf);
		documento.open();

		Paragraph texto = new Paragraph(nomeDoArquivo + "\n\n");
		texto.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(texto);
		for (Map.Entry<String, Partida> historicoSalvo : historicoDePartidas.entrySet()) {

			texto = new Paragraph("Data da Partida: " + historicoSalvo.getValue().getData());
			documento.add(texto);
			texto = new Paragraph("Desafiante: " + historicoSalvo.getValue().getDesafiante().getLogin());
			documento.add(texto);
			texto = new Paragraph("Desafiado: " + historicoSalvo.getValue().getDesafiado().getLogin());
			documento.add(texto);
			texto = new Paragraph("Vencedor: " + historicoSalvo.getValue().getVencedor().getLogin());
			documento.add(texto);
			texto = new Paragraph("Perdedor: " + historicoSalvo.getValue().getPerdedor().getLogin());
			documento.add(texto);
			texto = new Paragraph(
					"\nMapa do Desafiante: \n" + historicoSalvo.getValue().getMapaDesafiante().toString());
			documento.add(texto);
			texto = new Paragraph("\nMapa do Desafiado: \n" + historicoSalvo.getValue().getMapaDesafiado().toString());
			documento.add(texto);
			texto = new Paragraph(
					"\nPasso a Passo da Partida: \n " + historicoSalvo.getValue().getJogadas().toString());
			documento.add(texto);
		}

		documento.close();
	}

	private void separarPartidas(Jogador jogador) {

		historicoDePartidasGanhas = new HashMap<String, Partida>();
		historicoDePartidasPerdidas = new HashMap<String, Partida>();

		HashMap<String, Partida> historicoDePartidas = jogador.getHistoricoDePartidas();

		Set<String> keySet = historicoDePartidas.keySet();

		for (String chave : keySet) {
			if (jogador.getLogin().equals(historicoDePartidas.get(chave).getVencedor())) {
				historicoDePartidasGanhas.put(historicoDePartidas.get(chave).getData().toString(),
						historicoDePartidas.get(chave));
			} else {
				historicoDePartidasPerdidas.put(historicoDePartidas.get(chave).getData().toString(),
						historicoDePartidas.get(chave));
			}
		}
	}

	public String gerarRelatorioPartidasGanhas(Jogador jogador)  {

		separarPartidas(jogador);
		String nomeDoArquivo = "Relatorio_de_Partidas_Ganhas" + System.currentTimeMillis();
		try {
			geradorDeRelatorio(historicoDePartidasGanhas, nomeDoArquivo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nomeDoArquivo;
	}

	public String gerarRelatorioPartidasPerdidas(Jogador jogador)  {

		separarPartidas(jogador);
		String nomeDoArquivo = "Relatorio_de_Partidas_Perdidas" + System.currentTimeMillis();

		try {
			geradorDeRelatorio(historicoDePartidasPerdidas, nomeDoArquivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nomeDoArquivo;
	}
}
package Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Gerenciamento.CentralDeInformacoes;

public class Persistencia {

	static public void salvarOuAtualizar(CentralDeInformacoes centralDeInformacoes) {
		File file = new File("central.xml");
		
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		String xml = xstream.toXML(centralDeInformacoes);

		try {
			file.createNewFile();
			
			PrintWriter pw = new PrintWriter(file);
			pw.write(xml);
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public CentralDeInformacoes recuperarCentral() {
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		File file = new File("central.xml");

		try {

			if (file.exists()) {

				FileInputStream fis = new FileInputStream(file);

				return (CentralDeInformacoes) xstream.fromXML(fis);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new CentralDeInformacoes();
	}
}

package Alerta;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Classes.Jogador;

public class Mensageiro {
	public static void enviarEmailParaUmJogador(Jogador j) throws MessagingException {
		Properties props = new Properties();
	    
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("projetobatalhanaval0@gmail.com",
		         "projetop2");
		   }
		});

		Message mensagem =  new MimeMessage(session);
		mensagem.setFrom(new InternetAddress("projetobatalhanaval0@gmail.com"));

		Address[] remetente = InternetAddress.parse(j.getEmail());


		mensagem.setRecipients(Message.RecipientType.TO, remetente);
		mensagem.setSubject("Relatório do Jogo");
		
		MimeBodyPart texto = new MimeBodyPart();
		texto.setText("Mensagem do email");
		
		MimeBodyPart anexo = new MimeBodyPart();
		FileDataSource arquivo = new FileDataSource("Relatorio.pdf");
		anexo.setDataHandler(new DataHandler(arquivo));
		anexo.setFileName(arquivo.getName());
		
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(texto);
		mp.addBodyPart(anexo);
		
		mensagem.setContent(mp);
	}
}


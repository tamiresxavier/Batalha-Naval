package Alerta;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import Classes.Jogador;
import Gerenciamento.CentralDeInformacoes;
import Persistencia.Persistencia;

public class RecursosEmail {
	
	private String emailDoProjeto = "projetobatalhanaval0@hotmail.com";
    private String senhaDoProjeto = "projetop2";

    private CentralDeInformacoes central = Persistencia.recuperarCentral();

    private MimeMessage configuracaoDoemail() throws Exception {

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication()
                             {
                                   return new PasswordAuthentication(emailDoProjeto, senhaDoProjeto);
                             }
                        });

            return new MimeMessage(session);

        }

    
    public void recuperarSenha(String destinatario){
    	
    	System.out.println(destinatario);

            String novaSenha="";

            String[] caracteres = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
                                    "U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};

            for(int i = 0 ;i<8;i++){
                int indiceSorteado = (int)(Math.random()*caracteres.length);
                    novaSenha += caracteres[indiceSorteado];
            }

            try {
                Message mensagem = configuracaoDoemail();
                mensagem.setFrom(new InternetAddress(emailDoProjeto));
                mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
                mensagem.setSubject("Recuperação de Senha - Batalha Naval");
                mensagem.setText("Sua nova Senha: " + novaSenha);
                Transport.send(mensagem);
                JOptionPane.showMessageDialog(null, "Sua senha foi alterada com Sucesso! Verifique seu e-mail");
                central.modificarSenhaDoJogador(destinatario, novaSenha);
            } catch(Exception erro) {
            	erro.printStackTrace();
                JOptionPane.showMessageDialog(null, "Houve um erro ao enviar o e-mail.");
            }
    }

    
    public void enviarRelatorio(String nomeDoRelatorio, String textoDoEmail,String destinatario) {

        try {

            Message mensagem = configuracaoDoemail();
            mensagem.setFrom(new InternetAddress(emailDoProjeto));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensagem.setSubject("Relatório - Batalha Naval");

            MimeBodyPart texto = new MimeBodyPart();
            texto.setText(textoDoEmail);

            MimeBodyPart anexo = new MimeBodyPart();
            FileDataSource arquivo = new FileDataSource(nomeDoRelatorio + ".pdf");
            anexo.setDataHandler(new DataHandler(arquivo));
            anexo.setFileName(arquivo.getName());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(texto);
            mp.addBodyPart(anexo);

            mensagem.setContent(mp);
            Transport.send(mensagem);

            JOptionPane.showMessageDialog(null, "Relatório Enviado com Sucesso! Verifique seu e-mail");
        } catch(Exception erro) {
        	erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um erro ao enviar o e-mail.");
        }

    }
}
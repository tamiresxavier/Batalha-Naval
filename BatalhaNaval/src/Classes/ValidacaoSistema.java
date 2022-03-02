package Classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidacaoSistema {
	
	public static boolean validarEmail(String login){
		String padraoEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(padraoEmail, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(login);
		if(matcher.matches() ) {
			return true;
		}
		return false;
	}
	
	public static boolean validaPartida(Jogador desafiante, Jogador desafiado) {
		
		return true;
	}
}


public class MainPlagio {

	public static void main(String[] args) {
		String txt1 = "1� Lei de Kepler, tamb�m conhecida como �Lei das �rbitas�, � enunciada da seguinte forma:\r\n" + 
				"\r\n" + 
				"�Todos os planetas movem-se ao redor do Sol em �rbitas el�pticas, estando o Sol em um dos focos.�\r\n" + 
				"\r\n" + 
				"Kepler percebeu que a velocidade orbital dos planetas em torno do Sol n�o era constante. Por causa do formato das �rbitas, havia pontos nos quais a dist�ncia ao Sol aumentava ou diminu�a e que essa mudan�a era respons�vel por varia��es na velocidade dos planetas que orbitam o Sol.";
		String txt2 = "A 1� Lei de Kepler, tamb�m conhecida como �Lei das �rbitas�, � enunciada da seguinte forma:\r\n" + 
				"\r\n" + 
				"�Todos os planetas movem-se ao redor do Sol em �rbitas el�pticas, estando o Sol em um dos focos.�\r\n" + 
				"\r\n" + 
				"Kepler percebeu que a velocidade orbital dos planetas em torno do Sol n�o era constante. Por causa do formato das �rbitas, havia pontos nos quais a dist�ncia ao Sol aumentava ou diminu�a e que essa mudan�a era respons�vel por varia��es na velocidade dos planetas que orbitam o Sol.";
				
		CalculadoraDeSemelhan�a calc = new CalculadoraDeSemelhan�a(txt1, txt2);
		
		calc.calcularSemelhan�a();
		calc.displayRelatorio();		
		
	}

}

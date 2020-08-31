
public class MainPlagio {

	public static void main(String[] args) {
		String txt1 = "1ª Lei de Kepler, também conhecida como “Lei das Órbitas”, é enunciada da seguinte forma:\r\n" + 
				"\r\n" + 
				"“Todos os planetas movem-se ao redor do Sol em órbitas elípticas, estando o Sol em um dos focos.”\r\n" + 
				"\r\n" + 
				"Kepler percebeu que a velocidade orbital dos planetas em torno do Sol não era constante. Por causa do formato das órbitas, havia pontos nos quais a distância ao Sol aumentava ou diminuía e que essa mudança era responsável por variações na velocidade dos planetas que orbitam o Sol.";
		String txt2 = "A 1ª Lei de Kepler, também conhecida como “Lei das Órbitas”, é enunciada da seguinte forma:\r\n" + 
				"\r\n" + 
				"“Todos os planetas movem-se ao redor do Sol em órbitas elípticas, estando o Sol em um dos focos.”\r\n" + 
				"\r\n" + 
				"Kepler percebeu que a velocidade orbital dos planetas em torno do Sol não era constante. Por causa do formato das órbitas, havia pontos nos quais a distância ao Sol aumentava ou diminuía e que essa mudança era responsável por variações na velocidade dos planetas que orbitam o Sol.";
				
		CalculadoraDeSemelhança calc = new CalculadoraDeSemelhança(txt1, txt2);
		
		calc.calcularSemelhança();
		calc.displayRelatorio();		
		
	}

}

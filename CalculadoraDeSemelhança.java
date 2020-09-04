import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

<<<<<<< HEAD
public class CalculadoraDeSemelhan�a {
=======
public class CalculadoraDeSemelhança {
>>>>>>> d2b2a90f2f9379eb16dac451ce1c354154c211be
	
	private static String texto1;
	private static String texto2;
	private static String fonte1;
	private static String fonte2;
	private static Set<String> conjTexto1;
	private static Set<String> conjTexto2;
	
	protected void setFonte1(String fonte1) {
		this.fonte1 = fonte1;
	}

	protected void setFonte2(String fonte2) {
		this.fonte2 = fonte2;
	}

	protected void setTexto1(String texto1) {
		this.texto1 = texto1;
	}

	protected void setTexto2(String texto2) {
		this.texto2 = texto2;
	}


	public static String semelhanca() {
		//passa os textos para letra minúscula
		String lowTexto1 = texto1.toLowerCase();
		String lowTexto2 = texto2.toLowerCase();
		
		// separa as palavras do texto em arrays
		String[] arrayTexto1 = lowTexto1.split("[ ,.;:]");
		String[] arrayTexto2 = lowTexto2.split("[ ,.;:]");
		
		// cria conjuntos para colocar as palavras
		conjTexto1 = new TreeSet<>();
		conjTexto2 = new TreeSet<>();
		
		// coloca as palavras de cada array em seus respectivos conjuntos
		// eliminando assim as duplicatas
		for (String word : arrayTexto1) {
			conjTexto1.add(word);
		}
		
		for (String word : arrayTexto2) {
			conjTexto2.add(word);
		}
		
		// cria um mapa para contar as palavras
		HashMap<String, Integer> AllWords = new HashMap<String, Integer>();
		
		// adociona as palavras do primeiro conjunto ao mapa
		for (String word : conjTexto1) {
			AllWords.put(word, 1);
		}
		
		// adiciona as palavras do segundo conjunto ao mapa incrementando as repetidas
		for (String word : conjTexto2) {
			if (!AllWords.containsKey(word)) {
				AllWords.put(word, 1);
			} else {
				AllWords.put(word, AllWords.get(word) + 1);
			}
		}
		
		int countPairs = 0;
		int totalWords = AllWords.size();
		
		for (Map.Entry<String, Integer> w : AllWords.entrySet()) {
			if (w.getValue() == 2) {
				countPairs++;
			}
		}
		
		DecimalFormat dfmt = new DecimalFormat();
		dfmt.setMaximumIntegerDigits(3);
		dfmt.setMinimumIntegerDigits(1);
		dfmt.setMaximumFractionDigits(2);
		dfmt.setMinimumFractionDigits(2);
		
		double semelhanca = (double) countPairs / totalWords * 100;
		
		return dfmt.format(semelhanca);
	}
	
	public static String relatorio() {
<<<<<<< HEAD
		String rel = "Relat�rio de semelhan�a entre textos: \n" + 
=======
		String rel = "Relatório de semelhança de textos: \n" + 
>>>>>>> d2b2a90f2f9379eb16dac451ce1c354154c211be
					 fonte1 + " x " + fonte2 + "\n\n" +		
					 "Nível de semelhança = " + semelhanca() + "%\n\n" +
					 "Texto 1: " + texto1 + " - Fonte: " + fonte1 + "\n\n" +
					 "Texto 2: " + texto2 + " - Fonte: " + fonte2 + "\n\n" +
					 "Palavras do Texto 1 = " + conjTexto1.toString() + "\n\n" +
					 "Palavras do Texto 2 = " + conjTexto2.toString() + "\n\n" +
					 tamanho();
<<<<<<< HEAD
=======
		
		/*fmt.format("Relatório de semelhança de textos: \n" + 
				"%s" + " x " + "%s" + "\n\n" +		
				"Nível de semelhança = " + "%.2f" + "%\n\n" +
				"Texto 1: " + "%s" + " - Fonte: " + "%s" + "\n\n" +
				"Texto 2: " + "%s" + " - Fonte: " + "%s" + "\n\n" +
				"Palavras do Texto 1 = " + "%s" + "\n\n" +
				"Palavras do Texto 2 = " + "%s" + "\n\n" +
				"%s", fonte1, fonte2, semelhanca(), texto1, fonte1, fonte2, texto2, conjTexto1.toString()
				, conjTexto2.toString(), tamanho());*/
>>>>>>> d2b2a90f2f9379eb16dac451ce1c354154c211be
		return rel;
	}
	
	private static String tamanho() {
		if (conjTexto1.size() > conjTexto2.size()) {
			return "Texto 1 > Texto 2\n";
		} else if (conjTexto1.size() < conjTexto2.size()) {
			return "Texto 1 < Texto 2\n";
		} else {
			return "Texto 1 = Texto 2\n";
		}
	}
	
	public void displayRelatorio(File file) {	
		PrintStream p = null;
		try {			
			p = new PrintStream(file);
			p.print(relatorio());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

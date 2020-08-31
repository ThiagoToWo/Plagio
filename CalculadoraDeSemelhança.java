import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CalculadoraDeSemelhança {
	
	private String texto1;
	private String texto2;
	private Set<String> conjTexto1;
	private Set<String> conjTexto2;
	private double semelhança;
	
	public CalculadoraDeSemelhança(String texto1, String texto2) {		
		this.texto1 = texto1;
		this.texto2 = texto2;
	}	
	
	
	protected void setTexto1(String texto1) {
		this.texto1 = texto1;
	}



	protected void setTexto2(String texto2) {
		this.texto2 = texto2;
	}


	public void calcularSemelhança() {
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
		
		semelhança = (double) countPairs / totalWords;
		
		System.out.printf("Nível de semelhança = %.4f%n", semelhança);
	}
	
	public void displayRelatorio() {		
		System.out.printf("Texto 1: %s%nTexto 2: %s%n", texto1, texto2);
		
		System.out.print("Palavras do Texto 1 = {");		
		for (String word : conjTexto1) {
			System.out.print(word + " ");
		}		
		System.out.println("}");
		
		System.out.print("Palavras do Texto 2 = {");		
		for (String word : conjTexto2) {
			System.out.print(word + " ");
		}		
		System.out.println("}");
		
		if (conjTexto1.size() > conjTexto2.size()) {
			System.out.println("Texto 1 > Texto 2\n");
		} else if (conjTexto1.size() < conjTexto2.size()) {
			System.out.println("Texto 1 < Texto 2\n");
		} else {
			System.out.println("Texto 1 = Texto 2\n");
		}
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CalculadoraDeSemelhança {
	
	private String texto1;
	private int texto1Len;
	private String texto2;
	private int texto2Len;
	private String fonte1;
	private String fonte2;
	private String[] arrayTexto1; 
	private String[] arrayTexto2;
	private String[] arrayMaxSubseq;
	private Set<String> conjTexto1;
	private Set<String> conjTexto2;
	private int countPairs;
	private int totalWords;
	private String maxSubseq;
	
	protected void setFonte1(String fonte1) {
		this.fonte1 = fonte1;
	}

	protected void setFonte2(String fonte2) {
		this.fonte2 = fonte2;
	}

	protected void setTexto1(String texto1) {
		this.texto1 = texto1;
		texto1Len = texto1.length();
	}

	protected void setTexto2(String texto2) {
		this.texto2 = texto2;
		texto2Len = texto2.length();
	}


	private String semelhanca() {
		//passa os textos para letra minúscula
		String lowTexto1 = texto1.toLowerCase();
		String lowTexto2 = texto2.toLowerCase();
		
		// separa as palavras do texto em arrays
		arrayTexto1 = lowTexto1.split("[ ,.;:]");
		arrayTexto2 = lowTexto2.split("[ ,.;:]");
		
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
		
		totalWords = AllWords.size(); // total de palavras repetidas entre os dois textos
		
		// conta as palavras de um texto que formam par com outra igual no outro texto
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
		
		return dfmt.format(semelhanca) + "%";
	}
	
	private String maiorSubsequencia() {
		String menor = "";
		String maior;
		maxSubseq = "";
		
		if (texto1Len < texto2Len) {
			menor = texto1;
			maior = texto2;
		} else {
			menor = texto2;
			maior = texto1;
		}		
		
		for (int start = 0; start < menor.length(); start++) {
			for (int end = start + 1; end < menor.length() + 1; end++) {
				String sub = menor.substring(start, end);
				for (int i = 0; i < maior.length(); i++) {
					if (maior.regionMatches(i, sub, 0, sub.length())) {
						if (sub.length() > maxSubseq.length()) {
							maxSubseq = sub;
						}
					}
				}					
			}
		}
		arrayMaxSubseq = maxSubseq.split("[ ,.;:]");
		return maxSubseq;
	}
	
	private String probDeAcaso() {
		DecimalFormat dfmt = new DecimalFormat();
		dfmt.setMaximumIntegerDigits(3);
		dfmt.setMinimumIntegerDigits(1);
		dfmt.setMaximumFractionDigits(2);
		dfmt.setMinimumFractionDigits(2);
		
		double n = totalWords * 2;
		double a = arrayTexto1.length;
		double b = arrayTexto2.length;
		double p = arrayMaxSubseq.length;
		
		double prob = (Math.pow(n - p, a - p) * Math.pow(n - a, b - p)) / Math.pow(n, a + b - p) * 100;
		
		return dfmt.format(prob) + "%";
	}
	
	public String relatorio() {
		String rel = "Relatório de semelhança entre textos: \n" + 
					 fonte1 + " x " + fonte2 + "\n\n" +	
					 "Nível de semelhança = " + semelhanca() + " (palavras comuns aos dois textos)" + "\n\n" +					
					 "Texto 1: " + texto1 + " - Fonte: " + fonte1 + "\n\n" +
					 "Texto 2: " + texto2 + " - Fonte: " + fonte2 + "\n\n" +
					 tamanho() + "\n\n" +
					 "Maior trecho comum: " + maiorSubsequencia() + "\n\n" +
					 "Palavras do Texto 1 = " + conjTexto1.toString() + "\n\n" +
					 "Palavras do Texto 2 = " + conjTexto2.toString() + "\n\n" +					 
					 "Estimativa da probabilidade de acaso na coincidência do maior trecho comum " + "\n\n" +
					 "Palavras diferentes nos dois textos = " + totalWords + "\n\n" + 
					 "Universo estimado (N) = " + totalWords * 2 + "\n\n" +
					 "Palavras no texto 1 (a) = " + arrayTexto1.length + "\n\n" +
					 "Palavras no texto 2 (b) = " + arrayTexto2.length + "\n\n" +
					 "Palavras no maior trecho comum (p) = " + arrayMaxSubseq.length + "\n\n" +
					 "Probabilidade da coicidência = 100x[(N - p)^(a - p)]x[(N - a)^(b - p)]/[N^(a + b - p)] = " +
					 probDeAcaso();
		return rel;
	}
	
	private String tamanho() {
		if (arrayTexto1.length > arrayTexto2.length) {
			return "O Texto 1 tem mais palavras que o Texto 2";
		} else if (arrayTexto1.length < arrayTexto2.length) {
			return "O Texto 1 tem menos palavras que o Texto 2";
		} else {
			return "O Texto 1 e o Texto 2 tem a mesma quantidade de palavras";
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

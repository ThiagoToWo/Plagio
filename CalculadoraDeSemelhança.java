import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CalculadoraDeSemelhança {
	
	private String texto1;
	private String texto2;
	private String fonte1;
	private String fonte2;
	private String lowTexto1;
	private String lowTexto2;
	private StringTokenizer stk1; 
	private StringTokenizer stk2;
	private StringTokenizer lowStk1; 
	private StringTokenizer lowStk2;
	private StringTokenizer stkMaxSubseq;
	private Set<String> conjTexto1;
	private Set<String> conjTexto2;
	private int countPairs;
	private int totalWords;
	private String maxSubseq;
	
	public CalculadoraDeSemelhança(String fonte1, String texto1, String fonte2, String texto2) {
		// configura as fontes e os textos
		this.fonte1 = fonte1;
		this.fonte2 = fonte2;
		this.texto1 = texto1;
		this.texto2 = texto2;
		
		// armazena os textos em letras minúsculas
		lowTexto1 = texto1.toLowerCase();
		lowTexto2 = texto2.toLowerCase();
		
		// separa as palavras dos textos para futura contagem
		stk1 = new StringTokenizer(texto1, " ,.;:");
		stk2 = new StringTokenizer(texto2, " ,.;:");
		
		// separa as palavras dos textos com minúscula para agrupamento
		lowStk1 = new StringTokenizer(lowTexto1, " ,.;:");
		lowStk2 = new StringTokenizer(lowTexto2, " ,.;:");
		
		// cria conjuntos para colocar as palavras
		conjTexto1 = new TreeSet<>();
		conjTexto2 = new TreeSet<>();
		
		// coloca as palavras de cada array em seus respectivos conjuntos
		// eliminando assim as duplicatas
		while (lowStk1.hasMoreTokens()) {
			conjTexto1.add(lowStk1.nextToken());
		}

		while (lowStk2.hasMoreTokens()) {
			conjTexto2.add(lowStk2.nextToken());
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
			
		//configura a maior subsequência comum
		String menor = "";
		String maior;
		maxSubseq = "";
		
		if (texto1.length() < texto2.length()) {
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
		
		stkMaxSubseq = new StringTokenizer(maxSubseq, " ,.;:");
	}
	
	private String semelhanca() {		
		
		DecimalFormat dfmt = new DecimalFormat();
		dfmt.setMaximumIntegerDigits(3);
		dfmt.setMinimumIntegerDigits(1);
		dfmt.setMaximumFractionDigits(2);
		dfmt.setMinimumFractionDigits(2);
		
		double semelhanca = (double) countPairs / totalWords * 100;
		
		return dfmt.format(semelhanca) + "%";
	}
	
	public String relatorio() {
		String rel = "Relatório de semelhança entre textos: \n" + 
					 fonte1.toUpperCase() + " x " + fonte2.toUpperCase() + "\n\n" +	
					 "Nível de semelhança = " + semelhanca() + "\n\n" +					
					 "I. Análise dos textos:\n\n" +
					 "- Texto 1: " + texto1.replaceAll(maxSubseq, maxSubseq.toUpperCase()) + " - Fonte: " + fonte1.toUpperCase() + "\n\n" +
					 "- Texto 2: " + texto2.replaceAll(maxSubseq, maxSubseq.toUpperCase()) + " - Fonte: " + fonte2.toUpperCase() + "\n\n" +					
					 "- Palavras diferentes do Texto 1 = " + conjTexto1.toString() + "\n\n" +
					 "- Palavras diferentes do Texto 2 = " + conjTexto2.toString() + "\n\n" +	
					 "- Maior trecho comum: \"" + maxSubseq + "\"\n\n" +
					 "II. Contadores:\n\n" +
					 "- Palavras diferentes nos dois textos = " + totalWords + "\n\n" + 
					 "- Palavras diferentes comuns aos dois textos = " + countPairs + "\n\n" +
					 "- Palavras no texto 1 = " + stk1.countTokens() + "\n\n" +
					 "- Palavras no texto 2 = " + stk2.countTokens() + "\n\n" +
					 "- Palavras no maior trecho comum = " + stkMaxSubseq.countTokens() + "\n\n";
					// "Probabilidade da coicidência = 100x[(N - p)^(a - p)]x[(N - a)^(b - p)]/[N^(a + b - p)] = " +
					//probDeAcaso();
		return rel;
	}
	
	private String probDeAcaso() {
		DecimalFormat dfmt = new DecimalFormat();
		dfmt.setMaximumIntegerDigits(3);
		dfmt.setMinimumIntegerDigits(1);
		dfmt.setMaximumFractionDigits(2);
		dfmt.setMinimumFractionDigits(2);
		
		double n = totalWords * 2;
		double a = stk1.countTokens();
		double b = stk2.countTokens();
		double p = stkMaxSubseq.countTokens();
		
		double prob = (Math.pow(n - p, a - p) * Math.pow(n - a, b - p)) / Math.pow(n, a + b - p) * 100;
		
		return dfmt.format(prob) + "%";
	}	
	
	private String tamanho() {
		if (stk1.countTokens() > stk2.countTokens()) {
			return "O Texto 1 tem mais palavras que o Texto 2";
		} else if (stk1.countTokens() < stk2.countTokens()) {
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

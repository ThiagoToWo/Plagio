import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CalculadoraDeSemelhança {
	
	private String texto1; // texto 1
	private String texto2; // texto 2
	private String fonte1; // fonte do texto 1
	private String fonte2; // fonte do texto 2
	private String lowTexto1; // texto 1 com todas as letras minúsculas
	private String lowTexto2; // texto 2 com todas as letras minúsculas
	private String maxSubseq;
	private String formatTexto1; // texto 1 com a maior subsequência comum marcada
	private String formatTexto2; // texto 2 com a maior subsequência comum marcada
	private StringTokenizer stk1; // tokenizer do texto1
	private StringTokenizer stk2; // tokenizer do texto2
	private StringTokenizer lowStk1; // tokenizer do lowTexto1
	private StringTokenizer lowStk2; // tokenizer do lowWexto2
	private StringTokenizer stkMaxSubseq; // tokenizer da maior subsequência comum
	private Set<String> conjTexto1; // conjunto de palavras diferentes do texto 1
	private Set<String> conjTexto2; // conjunto de palavras diferentes do texto 2
	private Set<String> conjTextoComum; // conjunto de palavras diferentes comuns aos dois textos
	private int totalWords;	// total de palavras diferentes usadas nos dois textos
	
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
		stk1 = new StringTokenizer(texto1, " (){}[]\"\',.;:") ;
		stk2 = new StringTokenizer(texto2, " (){}[]\",.;:");
		
		// separa as palavras dos textos com minúscula para agrupamento
		lowStk1 = new StringTokenizer(lowTexto1, " (){}[]\"\',.;:");
		lowStk2 = new StringTokenizer(lowTexto2, " (){}[]\"\',.;:");
		
		// cria conjuntos para colocar as palavras com letras minúsculas
		conjTexto1 = new TreeSet<>();
		conjTexto2 = new TreeSet<>();
		
		// coloca as palavras de cada tokenizer em seus respectivos conjuntos
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

		totalWords = AllWords.size(); // total de palavras diferentes entre os dois textos

		// conta as palavras de um texto que formam par com outra igual no outro texto
		// e armazena no conjunto de palavras comuns
		conjTextoComum = new TreeSet<>();
		for (Map.Entry<String, Integer> w : AllWords.entrySet()) {
			if (w.getValue() == 2) {
				conjTextoComum.add(w.getKey());
			}
		}
			
		//encontra a maior subsequência comum		
		int[][] mtrxComp = new int[texto1.length()][texto2.length()];
		char[] textoChar1 = texto1.toCharArray();
		char[] textoChar2 = texto2.toCharArray();
		int maxLen = 0;
		int x = 0;
		// int y = 0; 
		
		for (int i = 0; i < mtrxComp.length; i++) {
			for (int j = 0; j < mtrxComp[i].length; j++) {				
				if (textoChar1[i] == textoChar2[j]) {
					mtrxComp[i][j] = 1 + (i > 0 && j > 0 ? mtrxComp[i - 1][j - 1] : 0);
					if (mtrxComp[i][j] > maxLen) {
						maxLen = mtrxComp[i][j];
						x = i;
						// y = j;
					}
				} else {
					mtrxComp[i][j] = 0;
				}
			}
		}
		
		maxSubseq = (String) texto1.substring(x + 1 - maxLen, x + 1); // ou maxSubseq = (String) texto2.substring(j + 1 - maxLen, j + 1);
		
		// marca a maior subsequência comum nos dois textos
		formatTexto1 = texto1.replace(maxSubseq, maxSubseq.toUpperCase());
		formatTexto2 = texto2.replace(maxSubseq, maxSubseq.toUpperCase());
		
		// separ as palavras do maior trecho comum
		stkMaxSubseq = new StringTokenizer(maxSubseq, " (){}[]\"\',.;:");
	}
	
	// calcula a porcentagem de palavras comuns aos dois textos em relação ao total de palavras diferentes
	private String semelhanca() {		
		
		DecimalFormat dfmt = new DecimalFormat();
		dfmt.setMaximumIntegerDigits(3);
		dfmt.setMinimumIntegerDigits(1);
		dfmt.setMaximumFractionDigits(2);
		dfmt.setMinimumFractionDigits(2);
		
		double semelhanca = (double) conjTextoComum.size() / totalWords * 100;
		
		return dfmt.format(semelhanca) + "%";
	}
	
	// emite um relatório com a análise do texto
	public String relatorio() {
		
		String rel = "Relatório de semelhança entre textos: \n" + 
					 fonte1.toUpperCase() + " x " + fonte2.toUpperCase() + "\n\n" +	
					 "Nível de semelhança = " + semelhanca() + "\n\n" +					 
					 "- Texto 1 (" + stk1.countTokens() + " palavras) : " + formatTexto1 + " - Fonte: " + fonte1.toUpperCase() + "\n\n" +
					 "- Texto 2 (" + stk2.countTokens() + " palavras) : " + formatTexto2 + " - Fonte: " + fonte2.toUpperCase() + "\n\n" +					
					 "- Palavras diferentes do Texto 1 (" + conjTexto1.size() + " palavras) = " + conjTexto1.toString() + "\n\n" +
					 "- Palavras diferentes do Texto 2 (" + conjTexto2.size() + " palavras) = " + conjTexto2.toString() + "\n\n" +	
					 "- Palavras diferentes comuns aos dois textos (" + conjTextoComum.size() + " palavras) = " + conjTextoComum.toString() + "\n\n" +
					 "- Maior trecho comum (" + stkMaxSubseq.countTokens() + " palavras) : \"" + maxSubseq + "\"\n\n";
					// "Probabilidade da coicidência = 100x[(N - p)^(a - p)]x[(N - a)^(b - p)]/[N^(a + b - p)] = " +
					//probDeAcaso();
		return rel;
	}
	
	/*private String probDeAcaso() {
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
	}*/
	
}

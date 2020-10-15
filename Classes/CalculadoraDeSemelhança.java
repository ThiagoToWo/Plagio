package Classes;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CalculadoraDeSemelhança {
	
	private Texto texto1; // texto 1
	private Texto texto2; // texto 2
	private StringTokenizer stkMaxSubseq; // tokenizer da maior subsequência comum
	private Set<String> conjTextoComum; // conjunto de palavras diferentes comuns aos dois textos
	private int totalWords;	// total de palavras diferentes usadas nos dois textos
	private String maxSubseq; // maior subsequência comum aos dois textos
	
	public CalculadoraDeSemelhança(String fonte1, String texto1, String fonte2, String texto2) {
		// configura as fontes e os textos
		this.texto1 = new Texto(fonte1, texto1);
		this.texto2 = new Texto(fonte2, texto2);
		
		// cria um mapa para contar as palavras
		HashMap<String, Integer> AllWords = new HashMap<String, Integer>();

		// adociona as palavras do primeiro conjunto ao mapa
		for (int i = 0; i < this.texto1.getConjTextoSize(); i++) {
			String word = this.texto1.getConjTexto(i);
			AllWords.put(word, 1);
		}

		// adiciona as palavras do segundo conjunto ao mapa incrementando as repetidas
		for (int i = 0; i < this.texto2.getConjTextoSize(); i++) {
			String word = this.texto2.getConjTexto(i);
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
		
		if (x > 0) {
			maxSubseq = (String) texto1.substring(x + 1 - maxLen, x + 1); // ou maxSubseq = (String) texto2.substring(j + 1 - maxLen, j + 1);
		} else {
			maxSubseq = "";
		}		
		
		// marca a maior subsequência comum nos dois textos
		this.texto1.setFormatTexto(texto1.replace(maxSubseq, maxSubseq.toUpperCase()));
		this.texto2.setFormatTexto(texto2.replace(maxSubseq, maxSubseq.toUpperCase()));
		
		// separ as palavras do maior trecho comum
		stkMaxSubseq = new StringTokenizer(maxSubseq, " (){}[]\"\',.;:");
	}
	
	// calcula a porcentagem de palavras comuns aos dois textos em relação ao total de palavras diferentes
	// em String
	public String semelhanca() {		
		
		DecimalFormat dfmt = new DecimalFormat();
		dfmt.setMaximumIntegerDigits(3);
		dfmt.setMinimumIntegerDigits(1);
		dfmt.setMaximumFractionDigits(2);
		dfmt.setMinimumFractionDigits(2);
		
		double semelhanca = (double) conjTextoComum.size() / totalWords * 100;
		
		return dfmt.format(semelhanca) + "%";
	}

	// calcula a porcentagem de palavras comuns aos dois textos em relação ao total
	// de palavras diferentes
	// em double
	public double semelhancaDouble() {

		double semelhanca = (double) conjTextoComum.size() / totalWords * 100;

		return semelhanca;
	}

	// emite um relatório com a análise do texto
	public String relatorio() {
		
		String rel = "Relatório de semelhança entre textos: \n" + 
					 texto1.getFonte().toUpperCase() + " x " + texto2.getFonte().toUpperCase() + "\n\n" +	
					 "Nível de semelhança = " + semelhanca() + "\n\n" +					 
					 "- Texto 1 (" + texto1.getTokenizerSize() + " palavras) : " + texto1.getFormatTexto() + " - Fonte: " + texto1.getFonte().toUpperCase() + "\n\n" +
					 "- Texto 2 (" + texto2.getTokenizerSize() + " palavras) : " + texto2.getFormatTexto() + " - Fonte: " + texto2.getFonte().toUpperCase() + "\n\n" +					
					 "- Palavras diferentes do Texto 1 (" + texto1.getConjTextoSize() + " palavras) = " + texto1.getConjTextoString() + "\n\n" +
					 "- Palavras diferentes do Texto 2 (" + texto2.getConjTextoSize() + " palavras) = " + texto2.getConjTextoString() + "\n\n" +	
					 "- Palavras diferentes comuns aos dois textos (" + conjTextoComum.size() + " palavras) = " + conjTextoComum.toString() + "\n\n" +
					 "- Maior trecho comum (" + stkMaxSubseq.countTokens() + " palavras) : \"" + maxSubseq + "\"\n\n";
		return rel;
	}
}

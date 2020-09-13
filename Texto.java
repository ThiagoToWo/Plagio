import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Texto {
	
	private String fonte; // fonte do texto
	private String texto; // texto 	
	private String lowTexto; // texto com todas as letras min�sculas
	private String formatTexto; // texto com a maior subsequ�ncia comum marcada
	private StringTokenizer stk; // tokenizer do texto
	private StringTokenizer lowStk; // tokenizer do lowTexto
	private Set<String> conjTexto; // conjunto de palavras diferentes do texto 
	
	public Texto(String fonte, String texto) {
		// configura as fontes e os textos
		this.fonte = fonte;
		this.texto = texto;

		// armazena os textos em letras min�sculas		
		lowTexto = texto.toLowerCase();

		// separa as palavras dos textos para futura contagem
		stk = new StringTokenizer(texto, " (){}[]\",.;:");

		// separa as palavras dos textos com min�scula para agrupamento
		lowStk = new StringTokenizer(lowTexto, " (){}[]\"\',.;:");

		// cria conjuntos para colocar as palavras com letras min�sculas
		conjTexto = new TreeSet<>();

		// coloca as palavras de cada tokenizer em seus respectivos conjuntos
		// eliminando assim as duplicatas
		while (lowStk.hasMoreTokens()) {
			conjTexto.add(lowStk.nextToken());
		}
	}

	// retorna a fonte do texto
	protected String getFonte() {
		return fonte;
	}

	// retorna o corpo do texto
	protected String getTexto() {
		return texto;
	}

	// retorna o corpo do texto com a maior subseq�ncia comum formatada
	protected String getFormatTexto() {
		return formatTexto;
	}

	// formata a maior subequ�ncia comum do texto
	protected void setFormatTexto(String fmtText) {
		this.formatTexto = fmtText;
	}

	// retorna a quantidade de palavras do texto separadas pelo StringTokenizer
	protected int getTokenizerSize() {
		return stk.countTokens();
	}

	// retorna a quantidade de palavras diferentes do texto separadas pelo StringTokenizer
	public int getConjTextoSize() {
		return conjTexto.size();
	}
	
	// retorna o conjunto de palavras diferentes do texto separadas pelo StringTokenizer
	public String getConjTextoString() {
		return conjTexto.toString();
	}

	public String getConjTexto(int i) {
		String[] conjTxt = conjTexto.toArray(new String[conjTexto.size()]);
		return conjTxt[i];
	}
}

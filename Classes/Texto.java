package Classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Texto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fonte; // fonte do texto
	private String texto; // texto 	
	private String lowTexto; // texto com todas as letras min�sculas
	private String formatTexto; // texto com a maior subsequ�ncia comum marcada
	private transient StringTokenizer stk; // tokenizer do texto
	private transient StringTokenizer lowStk; // tokenizer do lowTexto
	private Set<String> conjTexto; // conjunto de palavras diferentes do texto 
	private ArrayList<String> listaSemelhanca = new ArrayList<String>(); // lista de semelhan�as entre outros textos
	private ArrayList<Double> listaSemelhancaDb = new ArrayList<Double>(); // lista de semelhan�as entre outros textos
	private ArrayList<String> listaRelatorio = new ArrayList<String>(); // lista de relat�rios entre outros textos
	
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
	
	// retorna a semelhan�a com o texto de posi��o i
	protected String getListaSemelhanca(int i) {
		return listaSemelhanca.get(i);
	}
	// alimenta a lista de semelhan�a com outros textos
	protected void setListaSemelhanca(String texto) {
		listaSemelhanca.add(texto);
	}

	// retorna a semelhan�a com o texto de posi��o i
	protected Double getListaSemelhancaDb(int i) {
		return listaSemelhancaDb.get(i);
	}

	// alimenta a lista de semelhan�a com outros textos
	protected void setListaSemelhancaDb(Double texto) {
		listaSemelhancaDb.add(texto);
	}

	// retorna o relat�rio com o texto de posi��o i
	protected String getListaRelatorio(int i) {
		return listaRelatorio.get(i);
	}

	// alimenta a lista de relat�rios com outros textos
	protected void setListaRelatorio(String texto) {
		listaRelatorio.add(texto);
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

	// retorna uma palavra espec�fica do conjunto de palavras diferentes do texto separadas pelo StringTokenizer
	public String getConjTexto(int i) {
		String[] conjTxt = conjTexto.toArray(new String[conjTexto.size()]);
		return conjTxt[i];
	}
}

package Classes;
import java.util.ArrayList;

public class Biblioteca {

	private static CalculadoraDeSemelhança calc;
	private static ArrayList<Texto> listaTexto = new ArrayList<Texto>();
	private static String[] fontes;
	//private static String[][] mtxSem;	
	
	// adicionar textos na biblioteca
	public static void addTexto(Texto txt) {
		listaTexto.add(txt);
	}

	// compara todos os textos da lista de Textos uns com os outros e alimenta suas
	// listas de semelhanças e de relatórios;
	public static void comparar() {
		for (Texto txt1 : listaTexto) {
			for (Texto txt2 : listaTexto) {
				calc = new CalculadoraDeSemelhança(txt1.getFonte(), txt1.getTexto(), txt2.getFonte(), txt2.getTexto());
				txt1.setListaSemelhanca(calc.semelhanca());
				txt1.setListaSemelhancaDb(calc.semelhancaDouble());
				txt1.setListaRelatorio(calc.relatorio());				
			}
		}
	}

	/* alimentar a matriz com as semelhanças entre cada texto da bilblioteca
	public static void buildMatrizSemelhanca() {
		mtxSem = new String[listaTexto.size()][listaTexto.size()];

		for (int i = 0; i < mtxSem.length; i++) {
			for (int j = 0; j < mtxSem[i].length; j++) {
				mtxSem[i][j] = listaTexto.get(i).getListaSemelhanca(j);
			}
		}
	}
	
	 retorna um valor de semelhança entre o texto i e j na lista de textos.
	public static String getMatrizSemelhanca(int i, int j) {		
		return mtxSem[i][j];		
	}*/
	
	// limpa a lista de textos
	public static void limpar() {
		listaTexto.clear();
	}
	
	// retorna a quantidade de textos na biblioteca
	public static int getSize() {
		return listaTexto.size();
	}
	
	// constrói array com as fontes de cada texto
	public static void buildFontes() {
		fontes = new String[listaTexto.size() + 1];
		fontes[0] = "";
		for (int i = 1; i <= listaTexto.size(); i++) {
			fontes[i] = listaTexto.get(i - 1).getFonte();
		}
	}
	
	// retorna a fonte i do array de fontes
	public static String[] getFontes() {
		return fontes;
	}
	
	// retorna a semelhança entre o texto i e j da lista
	public static String getSemelhanca(int i, int j) {
		return listaTexto.get(i).getListaSemelhanca(j);
	}
	
	// retorna a semelhança entre do texto i da lista
	public static Double getSemelhancaDb(int i, int j) {
		return listaTexto.get(i).getListaSemelhancaDb(j);
	}

	// retorna o relatório do texto i e j da lista
	public static String getRelatorio(int i, int j) {
		return listaTexto.get(i).getListaRelatorio(j);
	}
}

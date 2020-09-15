import java.util.ArrayList;

public class Biblioteca {

	private CalculadoraDeSemelhança calc;
	private ArrayList<Texto> listaTexto = new ArrayList<Texto>();
	private String[][] mtxSem;	
	
	// adicionar textos na biblioteca
	public void addTexto(Texto txt) {
		listaTexto.add(txt);
	}

	// compara todos os textos da lista de Textos uns com os outros e alimenta suas
	// listas de semelhanças e de relatórios;
	public void comparar() {
		for (Texto txt1 : listaTexto) {
			for (Texto txt2 : listaTexto) {
				calc = new CalculadoraDeSemelhança(txt1.getFonte(), txt1.getTexto(), txt2.getFonte(), txt2.getTexto());
				txt1.setListaSemelhanca(calc.semelhanca());
				txt1.setListaRelatorio(calc.relatorio());
				txt2.setListaSemelhanca(calc.semelhanca());
				txt2.setListaRelatorio(calc.relatorio());
			}
		}
	}

	// alimentar a matriz com as semelhanças entre cada texto da bilblioteca
	public void buildMatrizSemelhanca() {
		mtxSem = new String[listaTexto.size()][listaTexto.size()];

		for (int i = 0; i < mtxSem.length; i++) {
			for (int j = 0; j < mtxSem[i].length; j++) {
				mtxSem[i][j] = listaTexto.get(i).getListaSemelhanca(j);
			}
		}
	}
	
	// retorna um valor de semelhança entre o texto i e j na lista de textos.
	public String getMatrizSemelhanca(int i, int j) {		
		return mtxSem[i][j];		
	}	
}

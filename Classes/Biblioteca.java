package Classes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Biblioteca {

	private static CalculadoraDeSemelhança calc;
	private static ArrayList<Texto> listaTexto = new ArrayList<Texto>();
	private static String[] fontes;
	
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

	// salva a lista de Textos da biblioteca
	public static void salvar(File file) {		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(listaTexto);
			os.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	// carrega uma lista de Textos e substitui a da Biblioteca
	public static void carregar(File file) {
		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<Texto> lt = (ArrayList<Texto>) os.readObject();
			listaTexto.clear();
			listaTexto.addAll(lt);
			os.close();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
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

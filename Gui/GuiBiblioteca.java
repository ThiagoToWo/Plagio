package Gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Classes.Biblioteca;
import Classes.Texto;

public class GuiBiblioteca extends JFrame {		

	private static final long serialVersionUID = 1L;
	private JLabel jlfonte = new JLabel("Fonte do texto: ");
	private JLabel jl = new JLabel("Texto: ");
	private JTextField jtf = new JTextField();
	private JTextArea jta = new JTextArea(40, 60);
	JLabel labAdd;
	private Font bigFont = new Font(getName(), Font.BOLD, 16);

	public GuiBiblioteca() {
		setTitle("Biblioteca de textos");
		// barra de menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem comparar = new JMenuItem("Comparar por fontes");
		comparar.addActionListener(new CompararListener());
		JMenuItem mapa = new JMenuItem("Mapa");
		mapa.addActionListener(new MapaListener());
		JMenuItem mapaRestrito = new JMenuItem("Mapa com tolerância");
		mapaRestrito.addActionListener(new MapaRestListener());
		JMenu arquivo = new JMenu("Arquivo");
		JMenuItem salvar  = new JMenuItem("Salvar biblioteca"); 
		salvar.addActionListener(new SalvarListener());
		JMenuItem carregar  = new JMenuItem("Carregar biblioteca"); 
		carregar.addActionListener(new CarregarListener());
		arquivo.add(salvar);
		arquivo.add(carregar);
		menu.add(comparar);
		menu.add(mapa);
		menu.add(mapaRestrito);
		menuBar.add(arquivo);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		// cria e configura o painel central.
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// cria e adiciona os componentes ao painel central.
		jlfonte.setFont(bigFont);
		jl.setFont(bigFont);
		jtf.setFont(bigFont);
		center.add(jlfonte);
		center.add(jtf);
		center.add(jl);
		jta.setFont(bigFont);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		center.add(new JScrollPane(jta)); 
		getContentPane().add(BorderLayout.CENTER, center);
		// cria o painel sul.
		JPanel south = new JPanel();
		// cria, configura e adiciona o componente do painel sul.
		JButton botAdd = new JButton("Adicionar à biblioteca");
		botAdd.setFont(bigFont);
		botAdd.addActionListener(new AdicionarListener());
		JButton botLimp = new JButton("Limpar biblioteca");
		botLimp.setFont(bigFont);
		botLimp.addActionListener(new LimparListener());
		labAdd = new JLabel(" Textos = " + Biblioteca.getSize());
		labAdd.setFont(bigFont);
		south.add(botAdd);
		south.add(botLimp);
		south.add(labAdd);
		getContentPane().add(BorderLayout.SOUTH, south);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(500, 200);
		setSize(1000, 500);
		setVisible(true);
	}	

	public class AdicionarListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Texto txt = new Texto(jtf.getText(), jta.getText());
			Biblioteca.addTexto(txt);
			labAdd.setText(" Textos = " + Biblioteca.getSize());
			jtf.setText("");
			jta.setText("");
			jtf.requestFocus();
		}
		
	}
	
	public class LimparListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Biblioteca.limpar();
			labAdd.setText(" Textos = " + Biblioteca.getSize());
		}

	}

	public class CompararListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new DialogComparador();

		}

	}
	
	public class MapaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Biblioteca.comparar();
			Biblioteca.buildFontes();
			
			JFileChooser mapa = new JFileChooser();
			JOptionPane.showMessageDialog(getParent(), "Nomeie o arquivo e acrescente um extensão de arquivo de texto.\n"
					+ "Exemplos: nome.txt, nome.docx, nome.rtf", "Instruções de salvamento",
					JOptionPane.INFORMATION_MESSAGE);
			mapa.showSaveDialog(getParent());
			try {
				PrintStream ps = new PrintStream(mapa.getSelectedFile());
				
				ps.println("Mapa de Semelhança " + mapa.getSelectedFile().getName() + "\n"); // título
				
				for (int i = 0; i < Biblioteca.getSize(); i++) {
					for (int j = 0; j < Biblioteca.getSize(); j++) {
						ps.printf("%3d|%3d %3.2f%% ", i + 1, j + 1, Biblioteca.getSemelhancaDb(i, j));
					}
					ps.println("\n"); // pula duas linhas
				}
				
				for (int i = 1; i <= Biblioteca.getSize(); i++) { // imprime a legenda de fontes
					ps.printf("%d. %s.\n", i, Biblioteca.getFontes()[i]);
				}
				ps.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public class MapaRestListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Biblioteca.comparar();
			Biblioteca.buildFontes();
			
			JFileChooser mapaRest = new JFileChooser();
			String stsSem = JOptionPane.showInputDialog(getParent(), "Insira a semelhança mínima (separe decimais com ponto \".\") ",
					"Semelhança mínima", JOptionPane.QUESTION_MESSAGE);
			double dbSem = new Double(stsSem);
			JOptionPane.showMessageDialog(getParent(), "Nomeie o arquivo e acrescente um extensão de arquivo de texto.\n"
					+ "Exemplos: nome.txt, nome.docx, nome.rtf", "Instruções de salvamento",
					JOptionPane.INFORMATION_MESSAGE);
			mapaRest.showSaveDialog(getParent());
			try {
				PrintStream ps = new PrintStream(mapaRest.getSelectedFile());
				
				ps.println("Mapa de Semelhança " + mapaRest.getSelectedFile().getName() + "\n"); // título
				ps.printf("Semelhança mínima = %.2f%%%n%n", dbSem);
				for (int i = 0; i < Biblioteca.getSize(); i++) {
					for (int j = i + 1; j < Biblioteca.getSize(); j++) {
						if (Biblioteca.getSemelhancaDb(i, j) >= dbSem) { // retorna apenas as semelhanças maiores ou iguais a informada
							ps.printf("%s x %s = %.2f%%%n", Biblioteca.getFontes()[i + 1],
									Biblioteca.getFontes()[j + 1], Biblioteca.getSemelhancaDb(i, j));
						}
					}
				}
				ps.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public class CarregarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser load = new JFileChooser();
			load.showOpenDialog(getParent());
			Biblioteca.carregar(load.getSelectedFile());
			labAdd.setText(" Textos = " + Biblioteca.getSize());
		}

	}

	public class SalvarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser save = new JFileChooser();
			save.showSaveDialog(getParent());
			Biblioteca.salvar(save.getSelectedFile());
		}

	}
}

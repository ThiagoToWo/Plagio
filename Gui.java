import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class Gui extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private String autor = "Autor: Thiago de Oliveira Alves\ntowo497@gmail.com";
	private String versao = "Versão: 1.0 \n 02-09-2020\n\n";
	private JLabel[] jlfonte = {new JLabel("Fonte do texto 1:"), new JLabel("Fonte do texto 2:")};
	private JLabel[] jl = {new JLabel("Texto 1:"), new JLabel("Texto 2:")};
	private JTextField[] jtf = {new JTextField(), new JTextField()};
	private JTextArea[] jta = {new JTextArea(40, 60), new JTextArea(40, 60)};
	private Font bigFont = new Font(getName(), Font.BOLD, 16);
	
	public Gui() {
		setTitle("Analisador de Plágio");
		// barra de menu
		JMenuBar barraDeMenu = new JMenuBar();
		JMenu menuSobre = new JMenu("Informações");
		JMenuItem autoria = new JMenuItem("Autor");
		autoria.addActionListener(new AutorListener());
		JMenuItem versao = new JMenuItem("Sobre o aplicativo");
		versao.addActionListener(new VersaoListener());
		menuSobre.add(autoria);
		menuSobre.add(versao);
		barraDeMenu.add(menuSobre);
		setJMenuBar(barraDeMenu);
		// cria e configura o painel central.
		JPanel center  = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));
		// cria e adiciona os componentes ao painel central.		
		for (int i = 0; i < 2; i++) {
			jlfonte[i].setFont(bigFont);
			jl[i].setFont(bigFont);
			jtf[i].setFont(bigFont);
			center.add(jlfonte[i]);
			center.add(jtf[i]);
			center.add(jl[i]);
			jta[i].setFont(bigFont);
			jta[i].setLineWrap(true);
			jta[i].setWrapStyleWord(true);
			jta[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));
			center.add(new JScrollPane(jta[i]));
		}
		getContentPane().add(BorderLayout.CENTER, center);
		// cria o painel sul.
		JPanel south = new JPanel();
		//cria, configura e adiciona o componente do painel sul.
		JButton botAval = new JButton("Analisar");
		botAval.setFont(bigFont);
		botAval.addActionListener(this);
		south.add(botAval);
		getContentPane().add(BorderLayout.SOUTH, south);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CalculadoraDeSemelhança cs = new CalculadoraDeSemelhança(jtf[0].getText(), jta[0].getText(),
				jtf[1].getText(), jta[1].getText());
		GuiDialog gd = new GuiDialog(cs.relatorio());
	}
	
	private class AutorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {			
			JOptionPane.showMessageDialog(null, autor, "Sobre mim", JOptionPane.INFORMATION_MESSAGE);

		}

	}
	
	private class VersaoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, versao, "Sobre este", JOptionPane.INFORMATION_MESSAGE);

		}

	}	
}

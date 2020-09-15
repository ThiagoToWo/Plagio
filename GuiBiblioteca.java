import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiBiblioteca extends JFrame {	

	private JLabel jlfonte = new JLabel("Fonte do texto: ");
	private JLabel jl = new JLabel("Texto: ");
	private JTextField jtf = new JTextField();
	private JTextArea jta = new JTextArea(40, 60);
	private Font bigFont = new Font(getName(), Font.BOLD, 16);

	public GuiBiblioteca() {
		setTitle("Biblioteca de textos");
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
		JButton botAna = new JButton("Analisar");
		botAna.setFont(bigFont);
		botAna.addActionListener(new AnalisarListener());
		south.add(botAdd);
		south.add(botAna);
		getContentPane().add(BorderLayout.SOUTH, south);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(500, 200);
		setSize(1000, 500);
		setVisible(true);
	}
	
	public class AnalisarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class AdicionarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}
}

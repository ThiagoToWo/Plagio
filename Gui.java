import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame implements ActionListener {
	JLabel[] jl = {new JLabel("Texto 1:"), new JLabel("Texto 2:")};
	JTextField[] jtf = {new JTextField("fonte do texto 1"), new JTextField("fonte do texto 2")};
	JTextArea[] jta = {new JTextArea(40, 60), new JTextArea(40, 60)};
	public Gui() {
		setTitle("Analisador de Plágio");
		// ceira e configura o painel central.
		JPanel center  = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		// cria e adiciona os componentes ao painel central.
		
		for (int i = 0; i < 2; i++) {
			center.add(jl[i]);
			center.add(jtf[i]);
			jta[i].setLineWrap(true);
			center.add(new JScrollPane(jta[i]));
		}
		getContentPane().add(BorderLayout.CENTER, center);
		// cria o painel sul.
		JPanel south = new JPanel();
		//cria, configura e adiciona o componente do painel sul.
		JButton botAval = new JButton("Analisar");
		botAval.addActionListener(this);
		south.add(botAval);
		getContentPane().add(BorderLayout.SOUTH, south);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CalculadoraDeSemelhança cs = new CalculadoraDeSemelhança();
		cs.setTexto1(jta[0].getText());
		cs.setTexto2(jta[1].getText());
		GuiDialog gd = new GuiDialog(this, "Relatório de semelhança entre textos", CalculadoraDeSemelhança.relatorio());
		cs.displayRelatorio(gd);
	}
}

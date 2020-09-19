package Gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Biblioteca;

public class DialogComparador extends JFrame {		

	private JComboBox<String> fonteChooser1;
	private JComboBox<String> fonteChooser2;
	int fonte1;
	int fonte2;
	JLabel labSemelhanca;
	private Font bigFont = new Font(getName(), Font.BOLD, 16);
	private Font bigFont2 = new Font(getName(), Font.BOLD, 128);
	
	public DialogComparador() {
		setTitle("Comparação cruzada");
		
		Biblioteca.comparar();	
		Biblioteca.buildFontes();
		
		JPanel north = new JPanel();
		fonteChooser1 = new JComboBox<String>(Biblioteca.getFontes());
		fonteChooser1.setFont(bigFont);
		fonteChooser1.addItemListener(new aListener());
		fonteChooser2 = new JComboBox<String>(Biblioteca.getFontes());
		fonteChooser2.setFont(bigFont);
		fonteChooser2.addItemListener(new bListener());
		north.add(fonteChooser1);
		north.add(fonteChooser2);
		getContentPane().add(BorderLayout.NORTH, north);
		
		JPanel center = new JPanel();
		labSemelhanca = new JLabel();
		labSemelhanca.setFont(bigFont2);
		center.add(labSemelhanca);
		getContentPane().add(BorderLayout.CENTER, center);
		
		JPanel south = new JPanel();
		JButton botRelatorio = new JButton("Gerar relatório");
		botRelatorio.addActionListener(new RelatorioListener());
		botRelatorio.setFont(bigFont);
		south.add(botRelatorio);
		getContentPane().add(BorderLayout.SOUTH, south);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(500, 200);
		setSize(1000, 500);
		setVisible(true);
	}
	
	public class aListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			fonte1 = fonteChooser1.getSelectedIndex() - 1;
			fonte2 = fonteChooser2.getSelectedIndex() - 1;
			labSemelhanca.setText(Biblioteca.getSemelhanca(fonte1, fonte2));
		}

	}
	
	public class bListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			fonte1 = fonteChooser1.getSelectedIndex() - 1;
			fonte2 = fonteChooser2.getSelectedIndex() - 1;
			labSemelhanca.setText(Biblioteca.getSemelhanca(fonte1, fonte2));
		}

	}
	
	public class RelatorioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new DialogRelatorio(Biblioteca.getRelatorio(fonte1, fonte2));
		}

	}
}

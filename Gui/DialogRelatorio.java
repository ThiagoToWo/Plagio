package Gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DialogRelatorio extends JFrame implements ActionListener {	
	
	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea(80, 60);
	private Font bigFont = new Font(getName(), Font.BOLD, 16);
	
	public DialogRelatorio(String rel) {
		setTitle("Relatório de semelhança entre textos");
		
		JPanel center  = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));
		jta.setFont(bigFont);
		jta.setText(rel);
		center.add(new JScrollPane(jta));
		getContentPane().add(BorderLayout.CENTER, center);
		
		JPanel south = new JPanel();
		JButton botExpTxt = new JButton("Exportar arquivo de texto");
		botExpTxt.setFont(bigFont);
		botExpTxt.addActionListener(this);
		south.add(botExpTxt);		
		getContentPane().add(BorderLayout.SOUTH, south);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(500, 200);
		setSize(1000, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser save = new JFileChooser();
		JOptionPane.showMessageDialog(getParent(), "Nomeie o arquivo e acrescente um extensão de arquivo de texto.\n"
				+ "Exemplos: nome.txt, nome.docx, nome.rtf", "Instruções de salvamento",
				JOptionPane.INFORMATION_MESSAGE);
		save.showSaveDialog(getParent());
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(save.getSelectedFile()));
			br.write(jta.getText());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

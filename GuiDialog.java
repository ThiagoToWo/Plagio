import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class GuiDialog extends JDialog implements ActionListener {	
	
	public GuiDialog(JFrame frame, String title, String rel) {
		super(frame, title);
		JTextPane jp = new JTextPane();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS ));
		JButton expTxt = new JButton("Exportar arquivo de texto");
		expTxt.addActionListener(this);
		jp.setText(rel);
		jp.add(expTxt);
		setLocation(500, 200);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser save = new JFileChooser();
		save.showSaveDialog(getParent());
		CalculadoraDeSemelhança cs = new CalculadoraDeSemelhança();
		cs.displayRelatorio(save.getSelectedFile());
	}

}

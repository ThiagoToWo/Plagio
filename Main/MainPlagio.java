package Main;
import javax.swing.JFrame;

import Gui.Gui;

public class MainPlagio {

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(1000, 500);
		gui.setLocation(500, 200);;
		gui.setVisible(true);
	}

}

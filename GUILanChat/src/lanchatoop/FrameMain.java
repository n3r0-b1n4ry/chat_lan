package lanchatoop;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameMain {

	private JFrame frameMain;
	private JLabel label;
	private JButton button;
	private ImageIcon iconLogo;
	public static void main(String[] args) {
		FrameMain Gui = new FrameMain();
		Gui.bodyMain();
	}
	//--------------Frame main----------------
	private void bodyMain() {
		frameMain = new JFrame();
		frameMain.setTitle("Chat Lan");
		frameMain.setSize(460, 700);
		//frameMain.setResizable(false);
		frameMain.getContentPane().setBackground(Color.DARK_GRAY);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setVisible(true);
		
		iconFrame();
	}
	//--------------add icon frameMain-----------
	private void iconFrame() {
		iconLogo = new ImageIcon("image/logo1.png");
		frameMain.setIconImage(iconLogo.getImage());
	}
	
}
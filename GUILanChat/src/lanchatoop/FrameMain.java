package lanchatoop;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameMain {

	public static void main(String[] args) {
		FrameMain.frame();

	}

	// Frame chinh
	private static void frame() {
		final Frame fr = new Frame("Chat Lan");
		fr.setSize(460, 900);
		fr.setBackground(Color.DARK_GRAY);
		fr.setVisible(true);
		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				fr.dispose();
			}
		});
	}

}
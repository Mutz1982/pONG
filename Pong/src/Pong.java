
import javax.swing.JFrame;

public class Pong extends JFrame {

	private static final String WINDOW_TITLE = "Pong";
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 800;
	
	public Pong() {
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new PongPanel());
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pong();

	}

}

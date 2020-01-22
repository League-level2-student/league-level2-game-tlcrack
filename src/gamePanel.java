import javax.swing.JFrame;
import javax.swing.JPanel;

public class gamePanel {
	final int WIDTH = 1000;
	final int HEIGHT= 1000;
	
	int mode = 0;
	
	JFrame fra = new JFrame();
	JPanel pan = new JPanel();
	
	
	gamePanel(){
		
	}
	
	void setup() {
		fra.add(pan);
		fra.setVisible(true);
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fra.setSize(WIDTH, HEIGHT);
	}
	
	void setMode() {
		
	}
	
}

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager {
	final int WIDTH = 1000;
	final int HEIGHT= 1000;
	
	JFrame fra = new JFrame();
	GamePanel pan = new GamePanel();
	
	
	GameManager(){
		
	}
	
	void setup() {
		fra.add(pan);
		fra.setVisible(true);
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fra.setSize(WIDTH, HEIGHT);
		fra.addKeyListener(pan);
	}
	
}

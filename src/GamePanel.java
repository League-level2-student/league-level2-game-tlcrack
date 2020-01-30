import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	final int MENU = 0;
    final int GAME = 1;
    final int BOSS = 2;
    final int END = 3;
    int mode = MENU;
	
	String menuBackground = "menu_background.jpg";
	String gameBackground = "grass_14.png";
	Font welcomeFont = new Font("Arial", Font.PLAIN, 50);
	Font continueFont = new Font("Arial", Font.PLAIN, 40);
	
	GamePanel() {
		if (needImage) {
		    loadImage (menuBackground);
		}
	}
	
	protected void paintComponent(Graphics g) {
		
		if (gotImage) {
			g.drawImage(image, 0, 0, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 1000, 1000);
		}
		
		if(mode==MENU) {
			drawMenuText(g);
		}
	}
	
	void drawMenuText(Graphics g) {
		g.setFont(welcomeFont);
		g.setColor(new Color(136, 5, 245));
		g.drawString("Welcome To Wizardia!", 240, 200);
		g.setFont(continueFont);
		g.drawString("Press Space to Play", 300, 500);
	}
	
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(mode==MENU && e.getKeyCode()==KeyEvent.VK_SPACE) {
			mode=GAME;
		    needImage=true;
			loadImage (gameBackground);
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

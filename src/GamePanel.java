import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
	
	public static BufferedImage menuImage;
	public static BufferedImage gameImage;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	public BufferedImage wizardImage1;
	public BufferedImage wandImage;
	public BufferedImage wizardImage2;
	
	final int MENU = 0;
    final int GAME = 1;
    final int BOSS = 2;
    final int END = 3;
    int mode = MENU;
    int level = 1;
    
    Timer updateTimer = new Timer(1000/60, this);
    
	String menuBackground = "menu_background.jpg";
	String gameBackground = "grass_14.png";
	String wizard1 = "wandless_wizard.png";
	String wand = "wand.png";
	String wizard2 = "wizard.png";
	
	Font welcomeFont = new Font("Arial", Font.PLAIN, 50);
	Font continueFont = new Font("Arial", Font.PLAIN, 40);
	Font gameFont = new Font("Arial", Font.PLAIN, 40);
	Font gameSubtitle = new Font("Arial", Font.BOLD, 25);
	Wizard w;
	ObjectManager om;
	GameObject staff = new GameObject(880, 500, 65, 65);
	WizardBeam wb;
	
	GamePanel() {
		if (needImage) {
		    loadImage (menuBackground);
		    try {
			    wizardImage1 = ImageIO.read(this.getClass().getResourceAsStream(wizard1));
			    wandImage = ImageIO.read(this.getClass().getResourceAsStream(wand));
			    wizardImage2 = ImageIO.read(this.getClass().getResourceAsStream(wizard2));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		w = new Wizard(50, 450, 100, 100);
		wb = new WizardBeam(w.x+w.width, w.y-(w.height/2), 300, 214);
		om=new ObjectManager(this, w);
		updateTimer.start();
	}
	
	protected void paintComponent(Graphics g) {
		BufferedImage bg;
			bg=menuImage;
		if (mode==GAME) {
			bg=gameImage;
		}
		if (gotImage) {
			g.drawImage(bg, 0, 0, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 1000, 1000);
		}
		
		if(mode==MENU) {
			
			drawMenuText(g);
		}
		if(mode==GAME) {
			
			om.draw(g);
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
	        	menuImage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	        	gameImage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	        	
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
		void drawGameState() {
			if(w.x>1000) {
				level+=1;
				w.setX(0);
				w.setY(450);
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
		if(mode==GAME||mode==BOSS) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			w.moveUp();
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			w.moveDown();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			w.moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			w.moveRight();
		}
		
		if(om.hasWand) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				wb.isActive=true;
			}
		}
		repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		w.XSpeed = 0;
		w.YSpeed = 0;
		if(om.hasWand&&wb.isActive) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				wb.isActive=false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(mode==GAME) {
			om.update();
			drawGameState();
		}
		repaint();
		
	}
}

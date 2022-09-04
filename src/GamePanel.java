import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	public static BufferedImage menuImage;
	public static BufferedImage gameImage;
	public static BufferedImage gameNightImage;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	public BufferedImage wizardImage1;
	public BufferedImage wandImage;
	public BufferedImage wizardImage2;

	final int MENU = 0;
	final int GAME = 1;
	final int END = 3;
	final int FAIL = 4;
	int mode = MENU;
	int level = 1;
	int wizardProgress = 0;

	Timer updateTimer = new Timer(1000 / 60, this);
	Timer batlinSpawner = new Timer(1000 / 1, this);
	Timer wizardKnockback = new Timer(50 / 1, this);
	Timer bossInvincibility = new Timer(2000 / 1, this);
	Timer fire = new Timer(3000 / 1, this);
	Timer fireBall = new Timer(1200 / 1, this);

	Random batlinRandomizer = new Random();

	String menuBackground = "menu_background.jpg";
	String gameBackground = "grass.png";
	String gameNightBackground = "darkened grass.jpg";
	String wizard1 = "wandless_wizard.png";
	String wand = "wand.png";
	String wizard2 = "wizard.png";

	Font welcomeFont = new Font("Arial", Font.PLAIN, 50);
	Font continueFont = new Font("Arial", Font.PLAIN, 40);
	Font gameFont = new Font("Arial", Font.PLAIN, 40);
	Font gameSubtitle = new Font("Arial", Font.BOLD, 25);
	boolean invincibility = false;
	Wizard w;
	ObjectManager om;
	GameObject staff = new GameObject(880, 500, 65, 65);
	WizardBeam wb;
	FireBreath fb;

	GamePanel() {
		if (needImage) {
			loadImage();
			try {
				wizardImage1 = ImageIO.read(this.getClass().getResourceAsStream(wizard1));
				wandImage = ImageIO.read(this.getClass().getResourceAsStream(wand));
				wizardImage2 = ImageIO.read(this.getClass().getResourceAsStream(wizard2));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		w = new Wizard(50, 450, 100, 100);
		wb = new WizardBeam(w.x + w.width, w.y, 275, 25);
		om = new ObjectManager(this, w);
		fb = new FireBreath(false);
		updateTimer.start();
	}

	protected void paintComponent(Graphics g) {
		BufferedImage bg;
		bg = menuImage;
		if (mode == GAME) {
			if (om.isDay) {
				bg = gameImage;
			} else {
				bg = gameNightImage;
			}
		}
		if (gotImage) {
			g.drawImage(bg, 0, 0, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 1000, 1000);
		}
		

		if (mode == MENU) {

			drawMenuText(g);
		} else if (mode == GAME) {

			if (level == 6) {
				// insert image here
				om.draw(g);
			} else {
				om.draw(g);
			}
		} else if (mode == END) {
			drawEndText(g);
		}
	}

	void drawMenuText(Graphics g) {
		g.setFont(welcomeFont);
		g.setColor(new Color(136, 5, 245));
		g.drawString("Welcome To Wizardia!", 240, 200);
		g.setFont(continueFont);
		g.drawString("Press Space to Play", 300, 500);
	}

	void drawEndText(Graphics g) {
		g.setFont(welcomeFont);
		g.setColor(new Color(136, 5, 245));
		g.drawString("You Won!", 300, 200);
		g.setFont(continueFont);
		g.drawString("Thanks for playing.", 260, 500);
	}

	void loadImage() {

		if (needImage) {
			try {
				menuImage = ImageIO.read(this.getClass().getResourceAsStream(menuBackground));
				gameImage = ImageIO.read(this.getClass().getResourceAsStream(gameBackground));
				gameNightImage = ImageIO.read(this.getClass().getResourceAsStream(gameNightBackground));

				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void drawGameState() {
		if ((w.x > 1000 && level < 6) || (om.b.bossHealth == 0 && om.b.isActive)) {
			level += 1;
			if (level == 3) {
				om.addEnemy(700, 400);
			}
			if (level == 4) {
				om.addEnemy(400, 400);
				om.addEnemy(525, 275);
				om.addEnemy(525, 525);
				om.addEnemy(650, 100);
				om.addEnemy(700, 850);
			}
			if (level == 5) {
				om.addEnemy(500, 400);
				om.addEnemy(500, 100);
				om.addEnemy(500, 700);
				
				//This took up 20 lines for TWO YEARS because I was printing them out individually. - Tate Criqui, 9/3/2022
				
				for(int i = 100; i < 1000; i+=100) {
					om.addEnemy(1500, i);
				}
				
				for(int i = -1000; i < 2000; i+=200) {
					om.addEnemy(1900, i);
				}
			}
			if (level == 6) {
				om.b.isActive = true;
				if (!batlinSpawner.isRunning() && om.b.bossHealth > 1) {
					batlinSpawner.start();
				}
				if (invincibility) {
					bossInvincibility.start();
				}
				if (!fire.isRunning() && om.b.bossHealth > 1) {
					fire.start();
				}

			}
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
		if (mode == MENU && e.getKeyCode() == KeyEvent.VK_SPACE) {
			mode = GAME;
			needImage = true;
			loadImage();
		}
		if (mode == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				w.moveUp();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				w.moveDown();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				w.moveLeft();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				w.moveRight();
			}

			if (om.hasWand) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					wb.isActive = true;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			w.XSpeed = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			w.YSpeed = 0;
		}
		if (om.hasWand && wb.isActive) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				wb.isActive = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (mode == GAME) {
			om.update();
			drawGameState();
			if (level == 6) {
				if (e.getSource() == batlinSpawner) {
					om.addEnemy(700, batlinRandomizer.nextInt(550) + 50);

				} else if (e.getSource() == wizardKnockback) {
					w.x -= 10;
					wizardProgress += 1;
					if (wizardProgress == 1) {
						om.hasWand = false;
					} else if (wizardProgress == 42 || w.x < 20) {
						wizardKnockback.stop();
						wizardProgress = 0;
						om.hasWand = true;
					}
				} else if (e.getSource() == bossInvincibility) {
					invincibility = false;
				} else if (e.getSource() == fire) {
					fb.FireShot();
				}
			}
		}
		repaint();

	}
}

import java.awt.Color;
import java.awt.Graphics;

public class ObjectManager {
	GamePanel gp;
	Wizard w;
	
	public ObjectManager(GamePanel gp, Wizard w) {
		this.gp=gp;
		this.w=w;
	}
	
	void draw(Graphics g) {

		
		setGameText(g);
		g.drawImage(gp.wizardImage1, w.x, w.y, w.width, w.height, null);
		
		if(gp.level==1) {
			drawLevel1Text(g);
		}
		if(gp.level==2) {
			drawLevel2Text(g);
			g.drawImage(gp.wandImage, gp.staff.x, gp.staff.y, gp.staff.width, gp.staff.height, null);
		}
		
		
	
	}
	
	
	
	void setGameText(Graphics g) {
		g.setFont(gp.gameFont);
		g.setColor(new Color(0, 0, 50));
	}

	void drawLevel1Text(Graphics g) {
		g.drawString("Hello, stranger. Welcome to this bizarre land.", 125, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Use arrow keys to move.", 350, 150);
	}
	void drawLevel2Text(Graphics g) {
		g.drawString("A Wand! Grab it.", 350, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Press space to fire an energy ball.", 300, 150);
	}
	void update() {
		w.update();
		if(gp.level==2) {
			
		}
	}
}
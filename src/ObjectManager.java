import java.awt.Color;
import java.awt.Graphics;

public class ObjectManager {
	GamePanel gp;
	Wizard w;
	boolean hasWand = false;
	
	public ObjectManager(GamePanel gp, Wizard w) {
		this.gp=gp;
		this.w=w;
	}
	
	void draw(Graphics g) {

		
		setGameText(g);
		if(hasWand==false) {
			g.drawImage(gp.wizardImage1, w.x, w.y, w.width, w.height, null);
		}
		else {
			g.drawImage(gp.wizardImage2, w.x, w.y, w.width, w.height, null);
		}
		if(gp.level==1) {
			drawLevel1Text(g);
		}
		if(gp.level==2) {
			drawLevel2Text(g);
			if(hasWand==false) {
				g.drawImage(gp.wandImage, gp.staff.x, gp.staff.y, gp.staff.width, gp.staff.height, null);
			}
		}
		gp.wb.draw(g);
		
		
	
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
		g.drawString("Press space to fire an energy beam.", 300, 150);
	}
	void drawLevel3Text(Graphics g) {
		g.drawString("It's getting dark. Look out!", 350, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Kill the bats with your energy beam.", 300, 150);
	}
	void update() {
		w.update();
		gp.wb.update(w.x, w.y, w.height);
		if(gp.level==2) {
			if (collisionCheck(w, gp.staff)==true){
				hasWand=true;
				
			}
		}
	}
	
	boolean collisionCheck(GameObject a, GameObject b) {
		if(a.collisionBox.intersects(b.collisionBox)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
}

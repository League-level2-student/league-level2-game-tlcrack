import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	GamePanel gp;
	Wizard w;
	boolean hasWand = false;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public boolean isDay = true;
	
	public ObjectManager(GamePanel gp, Wizard w) {
		this.gp=gp;
		this.w=w;
		double angle = getTanAngle(700, 400, w.x, w.y);
		enemies.add(new Enemy(700, 400, 100, 100, angle));
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
		if(gp.level==3) {
			isDay=false;
			drawLevel3Text(g);
			for(int i = enemies.size()-1; i>=0; i--) {
				enemies.get(i).draw(g);
				
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
	
	double getTanAngle(int startX, int startY, int endX, int endY) {
		return Math.atan2(endY-startY, endX - startX);
	}
	
	void update() {
		w.update();
		for(int x = enemies.size()-1; x >= 0; x--) {
			enemies.get(x).update();
		}
		gp.wb.update(w.x, w.y, w.height);
		if(gp.level==2) {
			if (collisionCheck(w, gp.staff)==true){
				hasWand=true;
				
			}
		
		}
		else if(gp.level==3) {
			
			for(int x=enemies.size()-1; x>=0; x--) {
				Enemy enemy = enemies.get(x);
				
				if(enemy.isActive) {
					double angle = getTanAngle(enemy.x, enemy.y, w.x, w.y);
					enemy.setAngle(angle);
					enemy.setSpeed(3, 3);
					if(collisionCheck(w, enemy)==true) {
						gp.mode = 4;
					}
					if(collisionCheck(gp.wb, enemy)==true && gp.wb.isActive==true) {
						enemy.isActive=false;
					}
					System.out.println(enemy.isActive);
				}
			}
		}
		purgeEnemies();
	}
	
	void purgeEnemies() {
		for(int x=enemies.size()-1; x>=0; x--) {
			Enemy enemy = enemies.get(x);
			if(!enemy.isActive) {
				enemies.remove(enemy);
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

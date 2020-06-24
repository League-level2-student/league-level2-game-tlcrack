import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
	GamePanel gp;
	Wizard w;
	boolean hasWand = false;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public boolean isDay = true;
	Boss b = new Boss(700, 100, 500, 500);
	FireBreath f;
	public ObjectManager(GamePanel gp, Wizard w) {
		this.gp=gp;
		this.w=w;
		f=new FireBreath(false);
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
		if(gp.level==4) {
			drawLevel4Text(g);
			for(int i = enemies.size()-1; i>=0; i--) {
				enemies.get(i).draw(g);
				
			}
		}
		if(gp.level==5) {
			drawLevel5Text(g);
			for(int i = enemies.size()-1; i>=0; i--) {
				enemies.get(i).draw(g);
			
			}
		}
		if(gp.level==6) {
			b.isActive=true;
			drawLevel6Text(g);
			b.draw(g);
			for(int i = enemies.size()-1; i>=0; i--) {
				enemies.get(i).draw(g);
			}
			if(gp.fb.isActive) {
				gp.fb.draw(g);
			}
		}
		gp.wb.draw(g);
		
	}
	
	
	
	void setGameText(Graphics g) {
		g.setFont(gp.gameFont);
		g.setColor(new Color(0, 0, 50));
	}

	void drawLevel1Text(Graphics g) {
		g.drawString("Hello, stranger. Welcome to this bizarre land.", 50, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Use arrow keys to move.", 350, 150);
	}
	void drawLevel2Text(Graphics g) {
		g.drawString("A Wand! Grab it.", 350, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Press space to fire an energy beam.", 250, 150);
	}
	void drawLevel3Text(Graphics g) {
		g.setColor(new Color(75, 150, 175));
		g.drawString("It's getting dark. Look out!", 250, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Kill the batlin with your energy beam.", 250, 150);
	}
	void drawLevel4Text(Graphics g) {
		g.setColor(new Color(75, 150, 175));
		g.drawString("More? You must be close to their cave.", 150, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Be careful-the batlin bite is deadly.", 220, 150);
		
	}
	void drawLevel5Text(Graphics g) {
		g.setColor(new Color(75, 150, 175));
		g.drawString("Look out - the batlin use battle tactics.", 150, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("Don't sprint for the exit.", 350, 150);
	}
	void drawLevel6Text (Graphics g) {
		g.setColor(new Color(75, 150, 175));
		g.drawString("Take down the Golbatlin!", 300, 100);
		g.setFont(gp.gameSubtitle);
		g.drawString("You have gained enough power.", 250, 150);
	}
	
	double getTanAngle(int startX, int startY, int endX, int endY) {
		return Math.atan2(endY-startY, endX - startX);
	}
	
	void update() {
		w.update();
		for(int x = enemies.size()-1; x >= 0; x--) {
			enemies.get(x).update();
		}
		gp.wb.update(w.x, w.y+75, w.height);
		if(gp.level==2) {
			if (collisionCheck(w, gp.staff)==true){
				hasWand=true;
				
			}
		
		}
		else if(gp.level>2) {
			
			for(int x=enemies.size()-1; x>=0; x--) {
				Enemy enemy = enemies.get(x);
				
				if(enemy.isActive) {
					double angle = getTanAngle(enemy.x, enemy.y, w.x, w.y);
					enemy.setAngle(angle);
					enemy.setSpeed(3, 3);
					if(collisionCheck(w, enemy)==true) {
						gp.mode = 4;
					}
					if(collisionCheck(gp.wb, enemy)==true && enemy.isActive==true && gp.wb.isActive==true) {
						enemy.isActive=false;
						
					}
					
				}
				
			}
			if(collisionCheck(gp.wb, b)==true && b.isActive && gp.invincibility==false) {
				System.out.println("boss collision");
				//b.bossHealth-=1;
				//gp.invincibility = true;
				//if(b.bossHealth==1) {
				//	gp.batlinSpawner.stop();
				//	if(!gp.fire.isRunning()) {
				//		gp.fire.start();
					//}
				//}
				//gp.wizardKnockback.start();
				gp.mode=3;
			}
			if(b.isActive&&gp.fb.isActive) {
				if(collisionCheck(gp.w, gp.fb)==true) {
					gp.mode=4;
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
	void addEnemy(int x, int y){
		double angle = getTanAngle(x, y, w.x, w.y);
		enemies.add(new Enemy(x, y, 130, 90, angle));
	}
    public BufferedImage rotateImage( BufferedImage image, int rotationDeg ) {
        double rotationRad = Math.toRadians(rotationDeg);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRad, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(image, null);
    }
	
}

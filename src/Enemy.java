import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Enemy extends GameObject{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public int xSpeed = 0;
	public int ySpeed = 0;
	public double tanAngle = 0;
	
	Enemy(int x, int y, int width, int height,double tanAngle) {
		super(x, y, width, height);
		this.tanAngle = tanAngle;
		// TODO Auto-generated constructor stub
		if(needImage) {
			loadImage("enemy.png");
		}
	}
		
	void update() {
		x += Math.cos(tanAngle)*xSpeed;
		y += Math.sin(tanAngle)*ySpeed;
		super.update();
	}
	
	void setAngle(double angle) {
		this.tanAngle=angle;
	}
	void setSpeed(int xSpeed, int ySpeed) {
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
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
	void draw(Graphics g) {
		g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
	}

}

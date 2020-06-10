import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Boss extends GameObject{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public int bossHealth = 2;
	
	Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	isActive=false;
		if(needImage) {
			loadImage("Golbat.png");
		}
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
		g.drawImage(image, x, y, width, height, null);
	}
}

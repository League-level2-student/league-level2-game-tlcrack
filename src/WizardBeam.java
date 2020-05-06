import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class WizardBeam extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	
	WizardBeam(int x, int y, int width, int height) {
		super(x, y, width, height);
		if (needImage) {
		    loadImage ("light beam.png");
		}
		isActive = false;
	}
	
	void draw(Graphics g) {
		if(isActive) {
		if (gotImage) {
			g.drawImage(image, x, y-75, image.getWidth(), image.getHeight(), null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		}
		super.draw(g);
	}
	void update(int newX,int newY,int height) {
		this.x=newX;
		this.y=newY-(height/2);
		super.update();
		
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
}

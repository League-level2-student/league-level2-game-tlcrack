import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class WizardBeam extends GameObject{

	boolean isActive = false;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	
	WizardBeam(int x, int y, int width, int height) {
		super(x, y, width, height);
		if (needImage) {
		    loadImage ("light beam.png");
		}
	}
	
	void draw(Graphics g) {
		if(isActive) {
			System.out.println("check");
		if (gotImage) {
			g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
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

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class FireBreath extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean isActive;
	FireBreath(boolean isActive) {
		super(700, 300, 20, 20);
		this.isActive=isActive;
		if(needImage) {
			loadImage("Fire Breath.png");
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
	g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
}
	
	void update() {
		super.update();
	}
}

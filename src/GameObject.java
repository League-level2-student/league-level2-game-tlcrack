import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int XSpeed = 0;
	int YSpeed = 0;
	boolean isActive = true;
	Rectangle collisionBox;
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
	}
	void update(){
		collisionBox.setBounds(x, y, width, height);
		x+=XSpeed;
		y+=YSpeed;
	}
	void draw(Graphics g) {
		g.setColor(Color.red);
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	void moveLeft() {
		XSpeed=-5;
	}
	void moveRight() {
		XSpeed=5;
	}
	void moveUp() {
		YSpeed=-5;
	}
	void moveDown() {
		YSpeed=5;
	}
	
	
	
	
	
}

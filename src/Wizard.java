
public class Wizard extends GameObject{
	
	public Wizard(int x, int y, int width, int height){
		
		super( x, y, width, height);
		
		
	}
	void update() {
		if(x+XSpeed>=0 && y+YSpeed>=0 && y+YSpeed + height<=1000) {
			super.update();
		}
	
	
	}
	
}

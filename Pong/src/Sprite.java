
public class Sprite {

	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	//private int initialXPosition, initialYPosition;
	
	public int getxPosition() {return xPosition;}
	public int getyPosition() {return yPosition;}
	public int getxVelocity() {return xVelocity;}
	public int getyVelocity() {return yVelocity;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public void setxPosition(int newX) {
		xPosition = newX;
	}

	public void setyPosition(int newY) {
		yPosition = newY;
	}

	public void setxVelocity(int newXVelocity) {
		xVelocity = newXVelocity;
	}

	public void setyVelocity(int newYVelocity) {
		yVelocity = newYVelocity;
	}

	public void setWidth(int newWidth) {
		width = newWidth;
	}

	public void setHeight(int newHeight) {
		height = newHeight;
	}
	
}

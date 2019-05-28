import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {

	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private int initialXPosition, initialYPosition;
	Color BALL_COLOUR = Color.WHITE;
	
	public int getXPosition() {return xPosition;}
	public int getYPosition() {return yPosition;}
	public int getXVelocity() {return xVelocity;}
	public int getYVelocity() {return yVelocity;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getInitialXPosition() {return initialXPosition;}
	public int getInitialYPosition() {return initialYPosition;}
	
	public void setInitialPosition(int initialX, int initialY) {
		initialXPosition = initialX;
		initialYPosition = initialY;
	}
	
	public void resetToInitialPosition() {
		setXPosition(initialXPosition);
		setYPosition(initialYPosition);
	}
	
	public void setXPosition(int newX) {
		xPosition = newX;
	}
		
	public void setYPosition(int newY) {
		yPosition = newY;
	}
	
	public void setXPosition(int newX, int panelWidth) {
		xPosition = newX;
		if(xPosition < 0) {
			xPosition = 0;
		} else if(xPosition + width > panelWidth) {
			xPosition = panelWidth - width;
		}
		
	}

	public void setYPosition(int newY, int panelHeight) {
		yPosition = newY;
		if(yPosition < 0) {
			yPosition = 0;
		} else if(yPosition + height > panelHeight) {
			yPosition = panelHeight - height;
		}
	}

	public void setXVelocity(int newXVelocity) {
		xVelocity = newXVelocity;
	}

	public void setYVelocity(int newYVelocity) {
		yVelocity = newYVelocity;
	}

	public void setWidth(int newWidth) {
		width = newWidth;
	}

	public void setHeight(int newHeight) {
		height = newHeight;
	}

	public void setInitialXPosition(int initialXPosition) {
		this.initialXPosition = initialXPosition;
	}

	public void setInitialYPosition(int initialYPosition) {
		this.initialYPosition = initialYPosition;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
	}
	public Color getColour() {
		// TODO Auto-generated method stub
		return BALL_COLOUR;
	}
	
}

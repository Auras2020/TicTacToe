import java.awt.Color;
import java.awt.Graphics;

public class Borders {

    private int xCoor, yCoor, width, height;
    
    public Borders(int xCoor, int yCoor, int width, int height) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.width=width;
        this.height=height;
    }

    public void draw(Graphics g) {
    	g.setColor(Color.GRAY);
    	g.fillRect(xCoor, yCoor, width, height);
    }

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
    
    
}

package gameworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class JdImage {
	BufferedImage img = null;
	
	int width;
	int height;

	int centerX;
	int centerY;
	
	float topLeftX;
	float topLeftY;
	float topRightX;
	float topRightY;
	float botLeftX;
	float botLeftY;
	float botRightX;
	float botRightY;
	
	boolean boundingBoxVisible = false;
	
	public JdImage(int centerX, int centerY, String imageName) {
		try {
			this.img = ImageIO.read(new File(imageName));
			} 
		catch (IOException e) {
			System.out.println("Image file failed to load");
			}
		
		this.centerX = centerX;
		this.centerY = centerY;
		
		this.width = this.img.getWidth();
		this.height = this.img.getHeight();
		
		this.topLeftX = (float) (this.centerX - this.width/2);
		this.topLeftY = (float) (this.centerY - this.height/2);
		
		this.topRightX = (float) (this.centerX + this.width/2);
		this.topRightY = (float) (this.centerY - this.height/2);
		
		this.botLeftX = (float) (this.centerX - this.width/2);
		this.botLeftY = (float) (this.centerY + this.height/2);
		
		this.botRightX = (float) (this.centerX + this.width/2);
		this.botRightY = (float) (this.centerY + this.height/2);
	}
	
	public void move(int dx, int dy) {
		this.centerX += dx;
		this.centerY += dy;
		
		this.topLeftX += (float) dx;
		this.topLeftY += (float) dy;
		
		this.topRightX += (float) dx;
		this.botRightY += (float) dy;
		
		this.botLeftX += (float) dx;
		this.botRightY += (float) dy;
		
		this.botLeftX += (float) dx;
		this.botLeftY += (float) dy;
		
		this.botRightX += (float) dx;
		this.botRightY += (float) dy;
		
	}
	
	public void setBoundingBoxVisible(boolean visibility) {
		this.boundingBoxVisible = visibility;
	}
	
	public void setImage(String imageName) {
		
		try {
			this.img = ImageIO.read(new File(imageName));
			} 
		catch (IOException e) {
			System.out.println("Image file failed to load");
			}
	}
	
	public void drawUpdates(Graphics g) {
		if (this.boundingBoxVisible) {
			//show visible bounds of image
			g.setColor(new Color(255,255,255));
			g.fillOval(Math.round(this.topLeftX-2),Math.round(this.topLeftY-2),4,4);
			g.setColor(new Color(255,255,255));
			g.fillOval(Math.round(this.topRightX-2),Math.round(this.topRightY-2),4,4);
			g.setColor(new Color(255,255,255));
			g.fillOval(Math.round(this.botLeftX-2),Math.round(this.botLeftY-2),4,4);
			g.setColor(new Color(255,255,255));
			g.fillOval(Math.round(this.botRightX-2),Math.round(this.botRightY-2),4,4);
		}
		g.drawImage(this.img, this.centerX-this.width/2,this.centerY-this.height/2,null);
	}
		
}
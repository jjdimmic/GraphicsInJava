package gameworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;

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
	
	int rotation = 0;
	AffineTransform at;
	
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
		
		this.at = AffineTransform.getTranslateInstance(this.centerX-this.width/2,this.centerY-this.height/2);
		
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
		this.at = AffineTransform.getTranslateInstance(this.centerX-this.width/2,this.centerY-this.height/2);
		
		this.topLeftX += (float) dx;
		this.topLeftY += (float) dy;
		
		this.topRightX += (float) dx;
		this.botRightY += (float) dy;
		
		this.botLeftX += (float) dx;
		this.botLeftY += (float) dy;
		
		this.botRightX += (float) dx;
		this.botRightY += (float) dy;
		
	}
	
	public void rotate(int degrees) {
		double tempX,tempY;
		
		this.rotation += degrees;
		if (this.rotation > 360 || this.rotation < -360) {
			this.rotation = degrees;
		}
		
		//----TopLeftPoint----
		//move point to origin
		this.topLeftX -= (float) this.centerX;
        this.topLeftY -= (float) this.centerY;		
		
		//apply rotation to point
		tempX = ((double) this.topLeftX) * Math.cos(Math.toRadians((float) degrees)) - ((double) this.topLeftY) * Math.sin(Math.toRadians((float) degrees)); 
		tempY = ((double) this.topLeftX) * Math.sin(Math.toRadians((float) degrees)) + ((double) this.topLeftY) * Math.cos(Math.toRadians((float) degrees));
		
		//move point back
		this.topLeftX = (float) (tempX + this.centerX);
		this.topLeftY = (float) (tempY + this.centerY);

		
		//----TopRightPoint----
		//move point to origin
		this.topRightX -= (float) this.centerX;
        this.topRightY -= (float) this.centerY;		
		
		//apply rotation to point
		tempX = ((double) this.topRightX) * Math.cos(Math.toRadians((float) degrees)) - ((double) this.topRightY) * Math.sin(Math.toRadians((float) degrees)); 
		tempY = ((double) this.topRightX) * Math.sin(Math.toRadians((float) degrees)) + ((double) this.topRightY) * Math.cos(Math.toRadians((float) degrees));
		
		//move point back
		this.topRightX = (float) (tempX + this.centerX);
		this.topRightY = (float) (tempY + this.centerY);
		
		
		//----BotLeftPoint----
		//move point to origin
		this.botLeftX -= (float) this.centerX;
        this.botLeftY -= (float) this.centerY;		
		
		//apply rotation to point
		tempX = ((double) this.botLeftX) * Math.cos(Math.toRadians((float) degrees)) - ((double) this.botLeftY) * Math.sin(Math.toRadians((float) degrees)); 
		tempY = ((double) this.botLeftX) * Math.sin(Math.toRadians((float) degrees)) + ((double) this.botLeftY) * Math.cos(Math.toRadians((float) degrees));
		
		//move point back
		this.botLeftX = (float) (tempX + this.centerX);
		this.botLeftY = (float) (tempY + this.centerY);
		
		
		//----BotRightPoint----
		//move point to origin
		this.botRightX -= (float) this.centerX;
        this.botRightY -= (float) this.centerY;		
		
		//apply rotation to point
		tempX = ((double) this.botRightX) * Math.cos(Math.toRadians((float) degrees)) - ((double) this.botRightY) * Math.sin(Math.toRadians((float) degrees)); 
		tempY = ((double) this.botRightX) * Math.sin(Math.toRadians((float) degrees)) + ((double) this.botRightY) * Math.cos(Math.toRadians((float) degrees));
		
		//move point back
		this.botRightX = (float) (tempX + this.centerX);
		this.botRightY = (float) (tempY + this.centerY);
		
		
		this.at = AffineTransform.getTranslateInstance(this.centerX-this.width/2,this.centerY-this.height/2);
		this.at.rotate(Math.toRadians(this.rotation),this.width/2,this.height/2);
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
		Graphics2D g2D = (Graphics2D)g;
		
		if (this.boundingBoxVisible) {
			//show visible bounds of image
			g.setColor(new Color(0,0,0));
			g.fillOval(Math.round(this.topLeftX-2),Math.round(this.topLeftY-2),4,4);
			g.setColor(new Color(0,0,0));
			g.fillOval(Math.round(this.topRightX-2),Math.round(this.topRightY-2),4,4);
			g.setColor(new Color(0,0,0));
			g.fillOval(Math.round(this.botLeftX-2),Math.round(this.botLeftY-2),4,4);
			g.setColor(new Color(0,0,0));
			g.fillOval(Math.round(this.botRightX-2),Math.round(this.botRightY-2),4,4);
		}
		g2D.drawImage(this.img,this.at,null);
	}
		
}
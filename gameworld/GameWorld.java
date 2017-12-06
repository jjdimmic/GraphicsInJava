package gameworld;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.imageio.*;

public class GameWorld extends JComponent implements ActionListener {
	static JFrame window = new JFrame("Game World");
	private static int worldWidth = 640;
	private static int worldHeight = 480;
	private static Timer clock;
	private static Timer gameloop;
	private static int seconds_passed = 0;
	static JdImage test_image1;
	static int object_mass = 1;
	static int gforce = 1;
	static int object_forceX = 25;
	static int object_forceY = 0;
	static int object_rotation_force = 0;
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Game World");
		GameWorld world = new GameWorld();
		window.setSize(worldWidth,worldHeight);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(world);
		
		test_image1 = new JdImage(300,100,"resources/red_rect.png");
		test_image1.setBoundingBoxVisible(true);
		clock = new Timer(1000, world);
		gameloop = new Timer(33, world);
		gameloop.start();
		clock.start();
		
		window.setVisible(true);
		
	}
	
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(130,232,227));
		g.fillRect(0,0,640,480);
		window.setSize(400,400);
		/* g.setColor(new Color(255,0,0));
		g.fillOval(worldWidth/2-50/2,worldHeight/2-50/2,50,50); */
		test_image1.drawUpdates(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gameloop) {
			object_forceY += gforce*object_mass;
			
			
			if (test_image1.getTopLeftX() <= 0) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getTopLeftY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getTopLeftY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force += object_forceY;
						}
					} else {
						object_rotation_force += object_forceY;
					}
				test_image1.move((test_image1.getTopLeftX()-1)*-1,0);
				object_forceX = object_forceX*-1;
				
				
			}
			
			
			if (test_image1.getTopLeftX() >= 480) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getTopLeftY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getTopLeftY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force -= object_forceY;
						}
					} else {
						object_rotation_force -= object_forceY;
					}
				test_image1.move((test_image1.getTopLeftX() - 480+1)*-1,0); 
				object_forceX = object_forceX*-1;
				
			}
			
			
			//TopRightX check rotation
			
			if (test_image1.getTopRightX() <= 0) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getTopRightY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getTopRightY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force += object_forceY;
						}
					} else {
						object_rotation_force += object_forceY;
					}
				test_image1.move((test_image1.getTopRightX()-1)*-1,0);
				object_forceX = object_forceX*-1;
				
				
			}
			
			
			if (test_image1.getTopRightX() >= 480) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getTopRightY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getTopRightY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force -= object_forceY;
						}
					} else {
						object_rotation_force -= object_forceY;
					}
				test_image1.move((test_image1.getTopRightX() - 480+1)*-1,0); 
				object_forceX = object_forceX*-1;
				
			}
			
			
			if (test_image1.getBotLeftX() <= 0) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getBotLeftY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getBotLeftY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force += object_forceY;
						}
					} else {
						object_rotation_force += object_forceY;
					}
				test_image1.move((test_image1.getBotLeftX()-1)*-1,0);
				object_forceX = object_forceX*-1;
				
				
			}
			
			
			if (test_image1.getBotLeftX() >= 480) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getBotLeftY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getBotLeftY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force -= object_forceY;
						}
					} else {
						object_rotation_force -= object_forceY;
					}
				test_image1.move((test_image1.getBotLeftX() - 480+1)*-1,0); 
				object_forceX = object_forceX*-1;
				
			}
			
			
			if (test_image1.getBotRightX() <= 0) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getBotRightY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getBotRightY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force += object_forceY;
						}
					} else {
						object_rotation_force += object_forceY;
					}
				test_image1.move((test_image1.getBotRightX()-1)*-1,0);
				object_forceX = object_forceX*-1;
				
				
			}
			
			
			if (test_image1.getBotRightX() >= 480) {
					if ( Math.abs(object_forceX) >= Math.abs(object_forceY) ) {
						if (test_image1.getBotRightY() < test_image1.getCenterY()) {
							object_rotation_force -= object_forceX;
						} else if (test_image1.getBotRightY() > test_image1.getCenterY()) {
							object_rotation_force += object_forceX;
						} else {
							object_rotation_force -= object_forceY;
						}
					} else {
						object_rotation_force -= object_forceY;
					}
				test_image1.move((test_image1.getBotRightX() - 480+1)*-1,0); 
				object_forceX = object_forceX*-1;
			}
			
			
			
			
			test_image1.move(object_forceX,object_forceY);
			test_image1.rotate(object_rotation_force);
			if (object_forceX < 0) {
				object_forceX += 1;
			} else if (object_forceX < 0) {
				object_forceX -= 1;
			}
			if (object_rotation_force < 0) {
				object_rotation_force += 1;
			} else if (object_rotation_force < 0) {
				object_rotation_force -= 1;
			}
			
			repaint();
		} else if (e.getSource() == clock) {
			seconds_passed+=1;
			System.out.printf("%s seconds have passed\n",seconds_passed);
			if (seconds_passed >= 5) {
				
				clock.stop();
				System.out.println("game world clock stopped");
				
				gameloop.stop();
				System.out.println("gameloop stopped");
			}
		}
	}
}
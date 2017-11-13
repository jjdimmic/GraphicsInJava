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
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Game World");
		GameWorld world = new GameWorld();
		window.setSize(worldWidth,worldHeight);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(world);
		
		test_image1 = new JdImage(300,300,"resources/red_rect.png");
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
			test_image1.move(1,0);
			test_image1.rotate(1);
			repaint();
		}
		else if (e.getSource() == clock) {
			seconds_passed+=1;
			System.out.printf("%s seconds have passed\n",seconds_passed);
			if (seconds_passed >= 10) {
				
				clock.stop();
				System.out.println("game world clock stopped");
				
				gameloop.stop();
				System.out.println("gameloop stopped");
			}
		}
	}
}
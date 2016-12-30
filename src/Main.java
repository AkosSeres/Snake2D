import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Main extends JFrame {

	public World world;

	public Main() {
		setTitle("Snake 2D");
		setBackground(Color.white);
		setVisible(true);
		getContentPane().setPreferredSize(new Dimension(500, 500));
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		addKeyListener(new Key());

		world = new World(30, 30);
		world.pauseResume();
	}

	public void paint(Graphics g) {
		BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		world.draw(img.createGraphics(), 500, 500);
		this.getContentPane().getGraphics().drawImage(img, 0, 0, null);

		Toolkit.getDefaultToolkit().sync();
		repaint();
	}

	public static void main(String args[]) {
		Main snakeGame = new Main();
		Thread t = new Thread(snakeGame.world);
		t.start();
	}

	private class Key implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_W)
				world.getSnake().setState(Snake.GO_UP);
			if (keyCode == KeyEvent.VK_S)
				world.getSnake().setState(Snake.GO_DOWN);
			if (keyCode == KeyEvent.VK_A)
				world.getSnake().setState(Snake.GO_LEFT);
			if (keyCode == KeyEvent.VK_D)
				world.getSnake().setState(Snake.GO_RIGHT);
			if (keyCode == KeyEvent.VK_SPACE)
				world.pauseResume();
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}
}

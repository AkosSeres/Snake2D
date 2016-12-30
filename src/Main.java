import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame{
	GamePanel game;
	
	public Main(){
		game = new GamePanel();
		setTitle("Snake 2D");
		setBackground(Color.white);
		setVisible(true);
		setSize(406,510);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(game);
		addKeyListener(new Key());
		addMouseListener(new Click());
		addMouseMotionListener(new Mouse());
	}
	
	public void paint(Graphics g){
		g.drawLine(0, 431, 406, 431);
		g.setFont(new Font("ariel", Font.PLAIN, 12));
	}
	
	public static void main(String args[]){
		Main snakeGame = new Main();
	}
	
	private class Key implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e){
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_W)game.world.getSnake().setState(Snake.GO_UP);
			if(keyCode == KeyEvent.VK_S)game.world.getSnake().setState(Snake.GO_DOWN);
			if(keyCode == KeyEvent.VK_A)game.world.getSnake().setState(Snake.GO_LEFT);
			if(keyCode == KeyEvent.VK_D)game.world.getSnake().setState(Snake.GO_RIGHT);
			if(keyCode == KeyEvent.VK_SPACE)game.world.getSnake().increase();
			if(keyCode == KeyEvent.VK_CONTROL)game.world.pauseResume();
		}
		@Override
		public void keyReleased(KeyEvent e){
			
		}
		@Override
		public void keyTyped(KeyEvent e){
			
		}
	}
	
	private class Click implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e){
		}
		@Override
		public void mouseEntered(MouseEvent e){
			
		}
		@Override
		public void mouseExited(MouseEvent e){
			
		}
		@Override
		public void	mousePressed(MouseEvent e){
			
		}
		@Override
		public void	mouseReleased(MouseEvent e){
			
		}
	}
	
	private class Mouse implements MouseMotionListener{
		@Override
		public void mouseMoved(MouseEvent e){		
		}
		
		@Override
		public void mouseDragged(MouseEvent e){
			
		}
	}
	
}

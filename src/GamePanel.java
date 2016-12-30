import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	private Image dbImage;
	private Graphics dbg;
	
	private volatile boolean running;
	private Thread gameThread;
	
	static final int xWidth = 400;
	static final int yHeight = 400;
	static final Dimension gameDimension = new Dimension(xWidth, yHeight);
	
	World world;
	
	public void run(){
		while(running){
			
			updateGame();
			renderGame();
			printToScreen();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void updateGame(){
		if(running && gameThread != null){
			world.update();
		}
	}
	
	public void draw(Graphics g){
		world.draw(g, xWidth, yHeight);
	}
	
	public void renderGame(){
		if(dbImage == null){
			dbImage = createImage(xWidth, yHeight);
			if(dbImage == null){System.err.println("dbImage is still null");return;}
			else{
				dbg = dbImage.getGraphics();
			}
		}
		//clear screen
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, xWidth, yHeight);
		//draw game elements
		draw(dbg);
	}
	
	private void printToScreen(){
		Graphics g;
		try{
			g = getGraphics();
			if(dbImage != null && dbg != null)g.drawImage(dbImage,0,0,null);
			Toolkit.getDefaultToolkit().sync();//for linux
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public GamePanel(){
		running = false;
		setPreferredSize(gameDimension);
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		requestFocus();
		world = new World(30,30);
	}
	
	public void addNotify(){
		super.addNotify();
		startGame();
	}
	
	private void startGame(){
		if(!running || gameThread == null){
			gameThread = new Thread(this);
			gameThread.start();
			running = true;
		}
	}
	
	public void stopGame(){
		if(running){
			running = false;
		}
	}

}

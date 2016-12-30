import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class World{
	public int sizeX, sizeY;
	public boolean[][] availability;
	private Snake snake;
	private Point snack = new Point();
	private volatile boolean gamePaused = true;
	private boolean gameOver = false;
	private int score = 0;
	
	public World(final int x, final int y){
		sizeX = x;
		sizeY = y;
		availability = new boolean[x][y];
		snake = new Snake(sizeX, sizeY);
		calculateAvailability();
		newSnack();
	}
	
	private void newSnack(){
		snackSetter:
			{int randX = (int) (Math.random() * sizeX), randY =  (int) (Math.random() * sizeY);
			if(availability[randX][randY]){availability[randX][randY] = false;snack.setLocation(randX, randY);}
			else break snackSetter;}
	}
	
	public void draw(Graphics g, final int canvasX, final int canvasY){
		Point blockSize = new Point(canvasX/sizeX, canvasY/sizeY);
		g.setColor(Color.black);
		for(int i = 1;i<snake.pieces.size();i++){
			g.drawRoundRect(snake.pieces.get(i).x*canvasX/sizeX, snake.pieces.get(i).y*canvasY/sizeY, blockSize.x, blockSize.y, blockSize.x/4, blockSize.x/4);
		}
		g.setColor(Color.BLUE);
		g.drawRoundRect(snake.pieces.firstElement().x*canvasX/sizeX, snake.pieces.firstElement().y*canvasY/sizeY, blockSize.x, blockSize.y, blockSize.x/4, blockSize.x/4);
		g.setColor(Color.RED);
		g.drawRoundRect(snack.x*canvasX/sizeX, snack.y*canvasY/sizeY, blockSize.x, blockSize.y, blockSize.x/4, blockSize.x/4);
	}
	
	public void update(){
		if(!gamePaused){
			gamePaused = !snake.move(sizeX, sizeY);
			gameOver = gamePaused;
			if(snake.pieces.firstElement().x == snack.x && snake.pieces.firstElement().y == snack.y){
				snake.increase();
				newSnack();
				score++;
			}
			calculateAvailability();
		}
	}
	
	private void calculateAvailability(){
		for(int i = 0;i<availability.length;i++){
			for(int j = 0;j<availability[i].length;j++){
				availability[i][j] = true;
			}
		}
		for(int i = 0;i<snake.pieces.size();i++){
			availability[snake.pieces.get(i).x][snake.pieces.get(i).y] = false;
		}
	}

	public Snake getSnake() {
		return snake;
	}
	
	public void pauseResume(){
		gamePaused = !gamePaused;
	}

}

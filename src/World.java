import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class World implements Runnable {
	public int sizeX, sizeY;
	public boolean[][] availability;
	private Snake snake;
	private Point snack = new Point();
	private volatile boolean gamePaused = true;
	private boolean gameOver = false;
	private int score = 0;

	public void run() {
		System.out.println("Starting update thread...");
		while (true) {
			this.update();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public World(final int x, final int y) {
		sizeX = x;
		sizeY = y;
		availability = new boolean[x][y];
		snake = new Snake(sizeX, sizeY);
		calculateAvailability();
		newSnack();
	}

	private void newSnack() {
		snackSetter: {
			int randX = (int) (Math.random() * sizeX), randY = (int) (Math.random() * sizeY);
			if (availability[randX][randY]) {
				availability[randX][randY] = false;
				snack.setLocation(randX, randY);
			} else
				break snackSetter;
		}
	}

	public void draw(Graphics g, int w, int h) {
		Point blockSize = new Point(w / sizeX, h / sizeY);
		g.setColor(Color.YELLOW);
		for (int i = 1; i < snake.pieces.size(); i++) {
			g.drawRoundRect((snake.pieces.get(i).x * w / sizeX), (snake.pieces.get(i).y * h / sizeY), blockSize.x,
					blockSize.y, blockSize.x / 4, blockSize.x / 4);
		}
		g.setColor(Color.BLUE);
		g.drawRoundRect((snake.pieces.firstElement().x * w / sizeX), (snake.pieces.firstElement().y * h / sizeY),
				blockSize.x, blockSize.y, blockSize.x / 4, blockSize.x / 4);
		g.setColor(Color.RED);
		g.drawRoundRect((snack.x * w / sizeX), (snack.y * h / sizeY), blockSize.x, blockSize.y, blockSize.x / 4,
				blockSize.x / 4);
	}

	public void update() {
		if (!gamePaused) {
			if (!snake.move(sizeX, sizeY))
				snake = new Snake(sizeX, sizeY);
			gameOver = gamePaused;
			if (snake.pieces.firstElement().x == snack.x && snake.pieces.firstElement().y == snack.y) {
				snake.increase();
				newSnack();
				score++;
			}
			calculateAvailability();
		}
	}

	private void calculateAvailability() {
		for (int i = 0; i < availability.length; i++) {
			for (int j = 0; j < availability[i].length; j++) {
				availability[i][j] = true;
			}
		}
		for (int i = 0; i < snake.pieces.size(); i++) {
			availability[snake.pieces.get(i).x][snake.pieces.get(i).y] = false;
		}
	}

	public Snake getSnake() {
		return snake;
	}

	public void pauseResume() {
		gamePaused = !gamePaused;
	}

}

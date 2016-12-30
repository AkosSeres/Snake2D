import java.awt.Point;
import java.util.Vector;

public class Snake {
	final static int GO_UP = 0, GO_DOWN = 1, GO_LEFT = 2, GO_RIGHT = 3;
	public Vector<Point> pieces;
	private int state;
	private boolean increase = false;
	private int stateIntent = 0;
	private boolean stateChanged = false;

	public int getState() {
		return state;
	}

	public void setState(final int a) {
		stateIntent = a;
		stateChanged = true;
	}

	public boolean move(final int sizeX, final int sizeY) {
		if (stateChanged) {
			if ((state == GO_DOWN || state == GO_UP) && (stateIntent == GO_LEFT || stateIntent == GO_RIGHT)) {
				state = stateIntent;
			}
			if ((stateIntent == GO_DOWN || stateIntent == GO_UP) && (state == GO_LEFT || state == GO_RIGHT)) {
				state = stateIntent;
			}
			stateChanged = false;
		}
		Point lastPcStand = new Point(0, 0);
		lastPcStand.setLocation(pieces.get(0));
		switch (state) {
		case GO_UP:
			if ((pieces.get(0).y - 1) == -1)
				return false;
			pieces.get(0).y--;
			break;
		case GO_DOWN:
			if ((pieces.get(0).y + 1) == sizeY)
				return false;
			pieces.get(0).y++;
			break;
		case GO_LEFT:
			if ((pieces.get(0).x - 1) == -1)
				return false;
			pieces.get(0).x--;
			break;
		case GO_RIGHT:
			if ((pieces.get(0).x + 1) == sizeX)
				return false;
			pieces.get(0).x++;
			break;
		}
		for (int i = 1; i < pieces.size(); i++) {
			Point current = new Point(0, 0);
			current.setLocation(pieces.get(i));
			pieces.get(i).setLocation(lastPcStand);
			lastPcStand.setLocation(current);
		}
		if (increase) {
			pieces.add(new Point(lastPcStand));
			increase = false;
		}

		for (int i = 1; i < pieces.size(); i++) {
			if ((pieces.get(0).x == pieces.get(i).x) && (pieces.get(0).y == pieces.get(i).y))
				return false;
		}

		return true;
	}

	public void increase() {
		increase = true;
	}

	private void init(final int worldX, final int worldY) {
		pieces = new Vector<Point>();
		pieces.add(new Point(worldX / 2, worldY / 2));
		state = (int) (Math.random() * 4);
		switch ((int) (Math.random() * 4)) {
		case 0:
			pieces.add(new Point(worldX / 2 - 1, worldY / 2));
			break;
		case 1:
			pieces.add(new Point(worldX / 2 + 1, worldY / 2));
			break;
		case 2:
			pieces.add(new Point(worldX / 2, worldY / 2 - 1));
			break;
		case 3:
			pieces.add(new Point(worldX / 2, worldY / 2 + 1));
			break;
		}
	}

	public Snake(final int worldX, final int worldY) {
		init(worldX, worldY);
	}

	public Snake() {
	}
}

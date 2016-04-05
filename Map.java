package brainstorm;

import java.util.*;

public class Map
{
    private final static int BORDER = 2;
    private SquareType[][] mapSquares;
    private int height;
    private int width;

    private Player player;
    private int playerX;
    private int playerY;

    private List<MapListener> mapListeners = new ArrayList<MapListener>();
    private List<Enemy> enemies = new ArrayList<Enemy>(5);

    public Map(final int height, final int width, final Player player) {
	this.player = player;
	this.height = height;
	this.width = width;
	playerX = width/2-BORDER;
	playerY = height/2-BORDER;

	mapSquares = new SquareType[height + BORDER*2][width + BORDER*2];

	for (int r = 0; r < height + BORDER*2; r++) {
	    for (int c = 0; c < width + BORDER*2; c++) {
		if( r < BORDER || c < BORDER || r >= height + BORDER || c >= width + BORDER) {
		    mapSquares[r][c] = SquareType.OUTSIDE;
		}
		else {
		    mapSquares[r][c] = SquareType.GRASS;
		}
	    }
	}
	for (int i = 0; i < 5; i++) {
	    Random rnd = new Random();
	    Enemy enemy = new Human(rnd.nextInt(10));
	    enemy.setCoords(i,i );
	    enemies.add(enemy);
	}
    }

    private void notifyListeners() {
	for (MapListener ml : mapListeners) {
	    ml.mapChanged();
	}
    }

    public void addListener(MapListener ml) {
	mapListeners.add(ml);
    }

    public void moveDown() {
	playerY++;
	if (hasCollision()) {
	    playerY--;
	}
	notifyListeners();
    }

    public void moveUp() {
	playerY--;
	if (hasCollision()) {
	    playerY++;
	}
	notifyListeners();
    }

    public void moveRight() {
	playerX++;
	if (hasCollision()) {
	    playerX--;
	}
	notifyListeners();
    }

    public void moveLeft() {
	playerX--;
	if (hasCollision()) {
	    playerX++;
	}
	notifyListeners();
    }

    private boolean hasCollision() {
	boolean boo = true;
	if ( (getSquareType(playerY, playerX) == SquareType.GRASS)) {
	    boo = false;
	}
	for (Enemy enemy : enemies) {
	    if (enemy.getEnemyY() == playerY && enemy.getEnemyX() == playerX) {
		player.setState("arena");
	    }
	}
	return boo;
    }

    public SquareType getSquareType(int y, int x) {
	return mapSquares[BORDER+y][BORDER+x];
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public int getPlayerX() {
	return playerX;
    }

    public int getPlayerY() {
    	return playerY;
    }

    public List<Enemy> getEnemies() {
	return enemies;
    }
}

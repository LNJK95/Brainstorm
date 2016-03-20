package mapgame;

import java.util.*;

public class Map {
	
	private MapType[][] mapSquares;
	private int height;
	private int width;
	
	private List<MapListener> mapListeners = new ArrayList<MapListener>();
	
	private Player player = new Player("TESTER");
	private int playerX;
	private int playerY;
	
	private final static int BORDER = 2; //The border around the map, there to prevent the player from going outside the map. 2 because idk it's a nice number.
	
	public Map(final int height, final int width) {
		this.height = height;
		this.width = width;
		playerX = 0;
		playerY = 0;
		
		mapSquares = new MapType[height + BORDER*2][width + BORDER*2]; 
		
		for (int r = 0; r < height + BORDER*2; r++) {
			for (int c = 0; c < width + BORDER*2; c++) {
				if( (r < BORDER) || (c < BORDER) || (r >= height + BORDER) || (c >= width + BORDER) ) {
					mapSquares[r][c] = MapType.OUTSIDE;
				}
				else {
					mapSquares[r][c] = MapType.GRASS;
				}
			}
		}
	}

	//probably shouldnt be heere lmao but idk
	public int getNumberOfTypes() {
		return MapType.values().length -1;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getBorder() {
		return BORDER;
	}

	public int getPlayerX() {
		return playerX;
	}
	
	public int getPlayerY() {
		return playerY;
	}
	
	public MapType getMapType(int x, int y) {
		return mapSquares[y+BORDER][x+BORDER];
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
		if ( (getMapType(playerX, playerY) == MapType.GRASS) || (getMapType(playerX, playerY) == MapType.ROAD) ) {
			boo = false;
		}
		return boo;
	}
	
	private void notifyListeners() {
		for (MapListener ml : mapListeners) {
			ml.mapChanged();
		}
	}
	
	public void addListener(MapListener ml) {
		mapListeners.add(ml);
	}
	
	public Map randomMap(Map map) {
		Random rnd = new Random();
		for (int h = BORDER; h < height+BORDER; h++) {
			for (int w = BORDER; w < width+BORDER; w++) {
				mapSquares[h][w] = MapType.values()[1+rnd.nextInt(getNumberOfTypes())];
			}
		}
		return map;
	}
}

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

    private int gearX;
    private int gearY;
    private Gear gear;

    private Backpack backpack;

    private Enemy collidedEnemy;
    //private Enemy enemyType;

    private List<GameListener> gameListeners = new ArrayList<GameListener>();
    private List<Enemy> enemies = new ArrayList<Enemy>(5);

    Random rnd = new Random();

    public Map(final int height, final int width, final Player player, final Backpack backpack) {
	this.backpack = backpack;
	this.player = player;
	this.height = height;
	this.width = width;
	playerX = width/2-BORDER;
	playerY = height/2-BORDER;
	gear = Gear.values()[rnd.nextInt(Gear.values().length)];

	mapSquares = new SquareType[height + BORDER*2][width + BORDER*2];

	for (int r = 0; r < height + BORDER*2; r++) {
	    for (int c = 0; c < width + BORDER*2; c++) {
		if( r < BORDER || c < BORDER || r >= height + BORDER || c >= width + BORDER) {
		    mapSquares[r][c] = SquareType.OUTSIDE;
		}
		else {
		    mapSquares[r][c] = SquareType.GROUND;
		}
	    }
	}
	houseCreator();
	randomGearCoords();
	randomEnemy(5);
    }

    public void houseCreator() {
	for (int r = 0; r < height; r++) {
	    for (int c = 0; c < width; c++) {
		if (r % 4 == 1 && c % 4 == 1) {
		    mapSquares[r+2][c+2] = SquareType.HOUSE;
		}
	    }
	}
    }

    public void resetMap() {
	for (int r = 0; r < height + BORDER*2; r++) {
	    for (int c = 0; c < width + BORDER*2; c++) {
		if( r < BORDER || c < BORDER || r >= height + BORDER || c >= width + BORDER) {
		    mapSquares[r][c] = SquareType.OUTSIDE;
		}
		else {
		    mapSquares[r][c] = SquareType.GROUND;
		}
	    }
	}
	enemies.clear();
	houseCreator();
	randomEnemy(5);
	randomGearCoords();
    }

    public void randomEnemy(int quantity) {
	while (enemies.size() < quantity) {
	    int enemyLevel = rnd.nextInt(player.getLevel())+1;
	    Enemy enemy = createEnemy(enemyLevel);
	    enemy.setCoords(rnd.nextInt(height), rnd.nextInt(width));
	    checkEnemy(enemy);
	    enemies.add(enemy);
	}
    }

    public void checkEnemy(Enemy enemy){
	System.out.println("checks enemy coords");
	for (Enemy e : enemies) {
	    if (e.getEnemyX() == enemy.getEnemyX() && e.getEnemyY() == enemy.getEnemyY()) {
		System.out.println("already enemy");
		newRandomCoords(enemy);
	    }
	}
	if (getSquareType(enemy.getEnemyY(), enemy.getEnemyX()) != SquareType.GROUND) {
	    newRandomCoords(enemy);
	    System.out.println("not grass");
	}
	if (enemy.getEnemyX() == playerX && enemy.getEnemyY() == playerY) {
	    newRandomCoords(enemy);
	    System.out.println("thats player dang it");
	}
    }

    public void newRandomCoords(Enemy enemy) {
	System.out.println("new enemy coords");
	enemy.setCoords(rnd.nextInt(height), rnd.nextInt(width));
	checkEnemy(enemy);
    }

    public void defeatedEnemy(Enemy enemy) {
	enemies.remove(enemy);
	randomEnemy(5);
	//remove enemy (collidedEnemy) from enemies
    }

    public Enemy createEnemy(int enemyLevel) {
	Enemy enemyType;
	switch(player.getLevel()) {
	    case 1:
	    case 2:
		enemyType = new Human(enemyLevel);
		break;
	    case 3:
	    case 4:
		enemyType = new Vampire(enemyLevel);
		break;
	    case 5:
	    case 6:
		//enemyType = new Werewolf(enemyLevel);
		//break;
	    case 7:
	    case 8:
		enemyType = new Mermaid(enemyLevel);
		break;
	    case 9:
	    case 10:
		//enemyType = new Alien(enemyLevel);
		//break;
	    default:
		enemyType = new Human(enemyLevel);
	}
	return enemyType;
    }

    public void enterHouse() {
	for (int r = 0; r < height + BORDER*2; r++) {
	    for (int c = 0; c < width + BORDER*2; c++) {
		if( r < BORDER || c < BORDER || r >= height + BORDER || c >= width + BORDER) {
		    mapSquares[r][c] = SquareType.OUTSIDE;
		}
		else {
		    mapSquares[r][c] = SquareType.GROUND;
		}
	    }
	}
	mapSquares[BORDER][(width+BORDER)/2] = SquareType.DOOR;
	enemies.clear();
	randomEnemy(rnd.nextInt(5));
	randomGearCoords();
	notifyListeners();
    }

    /* newMap() som gör om kartan in case of emergency lmao*/

    private void notifyListeners() {
	for (GameListener ml : gameListeners) {
	    ml.hasChanged();
	}
    }

    public void addListener(GameListener ml) {
	gameListeners.add(ml);
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
	if ( (getSquareType(playerY, playerX) == SquareType.GROUND)) {
	    boo = false;
	}
	if (getSquareType(playerY, playerX) == SquareType.HOUSE){
	    boo = true;
	    enterHouse();
	}
	if (getSquareType(playerY, playerX) == SquareType.DOOR) {
	    boo = false;
	    resetMap();
	}
	for (Enemy enemy : enemies) {
	    if (enemy.getEnemyY() == playerY && enemy.getEnemyX() == playerX) {
		boo = true;
		player.setState("arena");
		collidedEnemy = enemy;
	    }
	}
	if (playerX == gearX && playerY == gearY) {
	    backpack.addToBackpack(gear);
	    gear = Gear.values()[rnd.nextInt(Gear.values().length)];
	    randomGearCoords();
	    notifyListeners();
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

    public Enemy getCollidedEnemy() {
	return collidedEnemy;
    }

    public int getGearX() {
	return gearX;
    }

    public int getGearY() {
	return gearY;
    }

    public void randomGearCoords() {
	gearX = rnd.nextInt(width);
	gearY = rnd.nextInt(height);

	for (Enemy e : enemies) {
	    if (e.getEnemyX() == gearX && e.getEnemyY() == gearY) {
		randomGearCoords();
	    }
	}
	if (getSquareType(gearY, gearX) != SquareType.GROUND) {
	    randomGearCoords();
	}
	if (gearX == playerX && gearY == playerY) {
	    randomGearCoords();
	}
    }
}



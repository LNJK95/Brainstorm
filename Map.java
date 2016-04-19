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

    public Map(final int height, final int width, final Player player, final Backpack backpack) {
	this.backpack = backpack;
	this.player = player;
	this.height = height;
	this.width = width;
	playerX = width/2-BORDER;
	playerY = height/2-BORDER;
	gearX = playerX +1;
	gearY = playerY +1;
	gear = Gear.PIZZA_SLICER;

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
	randomEnemy();
    }

    public void resetMap() {
	enemies.clear();
	randomEnemy();
    }

    public void randomEnemy() {
	while (enemies.size() < 5) {
	    Random rnd = new Random();
	    int enemyLevel = rnd.nextInt(player.getLevel())+1;
	    Enemy enemy = createEnemy(enemyLevel);
	    //new Human(rnd.nextInt(player.getLevel())+1);
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
	if (getSquareType(enemy.getEnemyY(), enemy.getEnemyX()) != SquareType.GRASS) {
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
	Random rnd = new Random();
	enemy.setCoords(rnd.nextInt(height), rnd.nextInt(width));
	checkEnemy(enemy);
    }

    public void defeatedEnemy(Enemy enemy) {
	enemies.remove(enemy);
	randomEnemy();
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
		enemyType = new Werewolf(enemyLevel);
		break;
	    case 7:
	    case 8:
		enemyType = new Mermaid(enemyLevel);
		break;
	    case 9:
	    case 10:
		enemyType = new Alien(enemyLevel);
		break;
	    default:
		enemyType = new Human(enemyLevel);
	}
	return enemyType;
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
	if ( (getSquareType(playerY, playerX) == SquareType.GRASS)) {
	    boo = false;
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
}



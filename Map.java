package brainstorm;

import java.util.*;

/** Map contains all the playrules for the map
 *  and houses (a type of map). There is one piece of
 *  Gear available on the map at all times.*/

public class Map extends ListenedTo
{
    //The border around the map to keep the player from walking outside the map.
    private final static int BORDER = 2;

    private SquareType[][] mapSquares;
    private int height;
    private int width;

    private Player player;
    private int playerX;
    private int playerY;

    private Gear gear = Gear.NOTHING;
    private int gearX;
    private int gearY;

    private Backpack backpack;

    private Enemy collidedEnemy = new Nobody(0);

    private Collection<Enemy> enemies = new ArrayList<Enemy>(5);

    private Random rnd = new Random();

    private boolean inHouse = false;

    public Map(final int height, final int width, final Player player, final Backpack backpack) {
	this.backpack = backpack;
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
		    mapSquares[r][c] = SquareType.GROUND;
		}
	    }
	}

	newRandomGear();
	houseCreator();
	randomGearCoords();
	randomEnemy(5);
    }

    public void newRandomGear() {
	gear = Gear.values()[rnd.nextInt(Gear.values().length)];

	if (gear == Gear.NOTHING) {
	    newRandomGear();
	}
    }

    public void houseCreator() {
	for (int r = 0; r < height; r++) {
	    for (int c = 0; c < width; c++) {
		if (r % 5 == 2 && c % 5 == 2) {
		    mapSquares[r+BORDER][c+BORDER] = SquareType.HOUSE;
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
	for (Enemy e : enemies) {
	    if (e.getEnemyX() == enemy.getEnemyX() && e.getEnemyY() == enemy.getEnemyY()) {
		newRandomCoords(enemy);
	    }
	}
	if (getSquareType(enemy.getEnemyY(), enemy.getEnemyX()) != SquareType.GROUND ||
	    (enemy.getEnemyX() == playerX && enemy.getEnemyY() == playerY)) {
	    newRandomCoords(enemy);
	}
    }

    public void newRandomCoords(Enemy enemy) {
	enemy.setCoords(rnd.nextInt(height), rnd.nextInt(width));
	checkEnemy(enemy);
    }

    public void defeatedEnemy(Enemy enemy) {
	enemies.remove(enemy);
	if (!inHouse) {
	    randomEnemy(5);
	}
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

    //maybe make smaller! :)
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
	inHouse = true;
	mapSquares[BORDER][(width+BORDER)/2] = SquareType.DOOR;
	enemies.clear();
	playerY = 1;
	playerX = width/2-1;
	randomEnemy(rnd.nextInt(5));
	randomGearCoords();
	notifyListeners();
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
	boolean collision = true;
	if ( (getSquareType(playerY, playerX) == SquareType.GROUND)) {
	    collision = false;
	}
	if (getSquareType(playerY, playerX) == SquareType.HOUSE){
	    collision = false;
	    enterHouse();
	}
	if (getSquareType(playerY, playerX) == SquareType.DOOR) {
	    inHouse = false;
	    collision = false;
	    resetMap();
	}
	for (Enemy enemy : enemies) {
	    if (enemy.getEnemyY() == playerY && enemy.getEnemyX() == playerX) {
		collision = true;
		player.setState(FrameState.ARENA);
		collidedEnemy = enemy;
	    }
	}
	if (playerX == gearX && playerY == gearY) {
	    backpack.addToBackpack(gear);
	    gear = Gear.values()[rnd.nextInt(Gear.values().length)];
	    randomGearCoords();
	    notifyListeners();
	}
	return collision;
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

    public Iterable<Enemy> getEnemies() {
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
	if (getSquareType(gearY, gearX) != SquareType.GROUND || (gearX == playerX && gearY == playerY) ) {
	    randomGearCoords();
	}
    }

    public boolean isInHouse() {
	return inHouse;
    }
}



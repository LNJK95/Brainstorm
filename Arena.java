package brainstorm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena
{
    private Player player;
    private Enemy enemy;
    private List<GameListener> gameListeners = new ArrayList<GameListener>();
    private int enemySpeed;
    private int playerSpeed;

    private boolean win;
    private boolean death;


    public Arena(final Player player) {
	this.player = player;
	this.playerSpeed = player.getSpeed();
    }

    public void addListener(GameListener ml) {
	gameListeners.add(ml);
    }

    private void notifyListeners() {
    	for (GameListener ml : gameListeners) {
    	    ml.hasChanged();
    	}
    }

    public Player getPlayer() {
	return player;
    }

    public Enemy getEnemy() {
	return enemy;
    }

    public void setEnemy(Enemy enemy) {
	this.enemy = enemy;
	enemySpeed = enemy.getSpeed();
	notifyListeners();
    }

    public void flee() {
	Random rnd = new Random();
	int chance = rnd.nextInt(100);
	System.out.println(chance + " " + playerSpeed + " " + enemySpeed);
	if (player.getAlly().toString() != "Dumbfucks") {
	    player.setState("map");
	    player.setAlly(new Dumbfuck(0));
	}
	if (playerSpeed < enemySpeed) {
	    if (chance <= 25) {
		System.out.println(chance + "/25");
		player.setState("map");
	    }
	}
	else if (playerSpeed == enemySpeed) {
	    if (chance <= 50) {
		System.out.println(chance + "/50");
		player.setState("map");
	    }
	}
	else if (playerSpeed > enemySpeed) {
	    if (chance <= 75) {
		System.out.println(chance + "/75");
		player.setState("map");
	    }
	}
    }

    //lägg till chans
    //omorganisera
    public void fight() {
	if (keepFighting()) {
	    if (playerSpeed < enemySpeed) {
		System.out.println("enemy attacks first");
		enemyAttack();
		if (keepFighting()) {
		    playerAttack();
		}
		System.out.println("enemy: " + enemy.getHealth() + " player: " + player.getHealth());
	    } else if (playerSpeed == enemySpeed) {
		System.out.println("player attacks first");
		playerAttack();
		if (keepFighting()) {
		    enemyAttack();
		}
		System.out.println("enemy: " + enemy.getHealth() + " player: " + player.getHealth());
	    } else if (playerSpeed > enemySpeed) {
		System.out.println("player attacks first");
		playerAttack();
		if (keepFighting()) {
		    enemyAttack();
		}
		System.out.println("enemy: " + enemy.getHealth() + " player: " + player.getHealth());
	    }
	}
	keepFighting();
	notifyListeners();
    }

    public void flirt()  {
	//;)
	for (Gear gear : player.getEquippedGear()) {
	    if (gear.getFlirt() != null) {
		if (enemy.toString() == gear.getFlirt()) {
		    player.setAlly(enemy);
		    win = true;
		}
	    }
	}
    }

    public void enemyAttack() {
	int damage = enemy.getAttack() - player.getDefence();
	if (enemy.getAttack() < player.getDefence()) {
	    damage = 0;
	}
	player.setHealth(player.getHealth() - damage);
    }

    public void playerAttack() {
	int damage = player.getAttack() - enemy.getDefence();
	if (player.getAttack() < enemy.getDefence()) {
	    damage = 0;
	}
	enemy.setHealth(enemy.getHealth() - damage);
    }

    public boolean keepFighting() {
	boolean bool = true;
	if (player.getHealth() <= 0) {
	    bool = false;
	    win = false;
	    death = true;
	}
	else if (enemy.getHealth() <= 0) {
	    bool = false;
	    win = true;
	    death = false;
	}
	return bool;
    }

    public void win() {
	player.setState("map");
	player.addExperience(enemy);
	win = false;
    }

    public void lose() {
	player.setState("map");
	player.setHealth(player.getMaxHealth());
	death = false;
    }

    public boolean isWin() {
	return win;
    }

    public void setWin(final boolean win) {
	this.win = win;
    }

    public boolean isDeath() {
	return death;
    }

    public void setDeath(final boolean death) {
	this.death = death;
    }
}

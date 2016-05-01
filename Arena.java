package brainstorm;

import brainstorm.enemies.Nobody;

import java.util.Random;

/** Arena contains all the playrules of the
 * fighting sequences. */

public class Arena extends ListenedTo
{
    /*These constants are the percentage chance to escape from a powerful foe.
    *Low is for when you have lower speed than the enemy, medium for when
    *you have the same and high for when you have higher speed.*/
    private static final int LOW = 25;
    private static final int MEDIUM = 50;
    private static final int HIGH = 75;

    private Player player;
    private Enemy enemy = null;
    private int enemySpeed;
    private int playerSpeed;

    private boolean win;
    private boolean death;

    public Arena(final Player player) {
	this.player = player;
	this.playerSpeed = player.getSpeed();
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
	if (!player.getAlly().toString().equals("Nobody")) {
	    enemy.setStats();
	    player.setState(FrameState.MAP);
	    player.setAlly(new Nobody(0));
	}
	if (playerSpeed < enemySpeed) {
	    if (chance <= LOW) {
		enemy.setStats();
		player.setState(FrameState.MAP);
	    }
	}
	else if (playerSpeed == enemySpeed) {
	    if (chance <= MEDIUM) {
		enemy.setStats();
		player.setState(FrameState.MAP);
	    }
	}
	else if (playerSpeed > enemySpeed) {
	    if (chance <= HIGH) {
		enemy.setStats();
		player.setState(FrameState.MAP);
	    }
	}
    }

    public void fight() {
	if (keepFighting()) {
	    if (playerSpeed < enemySpeed) {
		enemyAttack();
		if (keepFighting()) {
		    playerAttack();
		}
	    } else if (playerSpeed >= enemySpeed) {
		playerAttack();
		if (keepFighting()) {
		    enemyAttack();
		}
	    }
	}
	keepFighting();
	notifyListeners();
    }

    public void flirt()  {
	boolean isFlirt = false;
	for (Gear gear : player.getEquippedGear()) {
	    if (gear.getFlirt() != null) {
		if (enemy.toString().equals(gear.getFlirt())) {
		    player.setAlly(enemy);
		    win = true;
		    isFlirt = true;
		}
	    }
	}
	if (!isFlirt) {
	    enemyAttack();
	    keepFighting();
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
	boolean keepFighting = true;
	if (player.getHealth() <= 0) {
	    keepFighting = false;
	    win = false;
	    death = true;
	}
	else if (enemy.getHealth() <= 0) {
	    keepFighting = false;
	    win = true;
	    death = false;
	}
	return keepFighting;
    }

    public void win() {
	player.setState(FrameState.MAP);
	player.addExperience(enemy);
	win = false;
    }

    public void lose() {
	player.setState(FrameState.MAP);
	player.setHealth(player.getMaxHealth());
	death = false;
    }

    public boolean isWin() {
	return win;
    }

    public boolean isDeath() {
	return death;
    }

}

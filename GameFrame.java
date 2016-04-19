package brainstorm;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame
{
    private MapFrame mapFrame;
    private ArenaFrame arenaFrame;
    private Map map;
    private Arena arena;
    private final Player player = new Player();
    private final Timer clockTimer;
    private final Backpack backpack;


    public GameFrame() {
	backpack = new Backpack();
	map = new Map(15, 20, player, backpack);
	arena = new Arena(player);
	mapFrame = new MapFrame(map, backpack);
	arenaFrame = new ArenaFrame(arena, backpack);

	//mapFrame.setVisible(true);
	//arenaFrame.setVisible(true);
	//mapFrame.toFront();

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		if (player.getState() == "map") {
		    arenaFrame.setVisible(false);
		    mapFrame.setVisible(true);
		    mapFrame.toFront();
		}
		else if (player.getState() == "arena") {
		    if (arena.isWin()) {
			backpack.loot();
			map.defeatedEnemy(map.getCollidedEnemy());
			arena.win();
		    }
		    if (arena.isDeath()) {
			map.resetMap();
			arena.lose();
			backpack.resetBackpack();
		    }
		    mapFrame.setVisible(false);
		    if (map.getCollidedEnemy() != null) {
			arena.setEnemy(map.getCollidedEnemy());
		    }
		    arenaFrame.setVisible(true);
		    arenaFrame.toFront();
		}
	    }
	};

	clockTimer = new Timer(500, doOneStep);
	startTimer();
    }

    public void startTimer() {
    	clockTimer.setCoalesce(true);
    	clockTimer.start();
    }

    public void stopTimer() {
	clockTimer.stop();
    }
}

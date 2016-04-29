package brainstorm;

import javax.swing.*;
import java.awt.event.ActionEvent;

/** GameFrame is the "controller". GameFrame
 * switches between MapFrame and ArenaFrame
 * whenever there is a change of "state".*/

public class GameFrame extends JFrame
{
    //The amount of mapsquares on the map.
    private static final int MAP_WIDTH = 20;
    private static final int MAP_HEIGHT = 15;

    private MapFrame mapFrame;
    private ArenaFrame arenaFrame;
    private Map map;
    private Arena arena;
    private final Player player = new Player();
    private final Timer clockTimer;
    private final Backpack backpack;
    private FrameState state;

    public GameFrame() {
	backpack = new Backpack();
	map = new Map(MAP_HEIGHT, MAP_WIDTH, player, backpack);
	arena = new Arena(player);
	mapFrame = new MapFrame(map, backpack, player);
	arenaFrame = new ArenaFrame(arena, backpack, player);
	state = FrameState.MAP;

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		if (player.getState().equals(FrameState.MAP) && !state.equals(FrameState.MAP)) {
		    arenaFrame.setVisible(false);
		    mapFrame.setVisible(true);
		    state = FrameState.MAP;
		}
		else if (player.getState().equals(FrameState.ARENA) && !state.equals(FrameState.ARENA)) {
		    mapFrame.setVisible(false);
		    state = FrameState.ARENA;
		    if (map.getCollidedEnemy() != null) {
			arena.setEnemy(map.getCollidedEnemy());
		    }
		    arenaFrame.setVisible(true);
		}
		else if (player.getState().equals(FrameState.ARENA) && state.equals(FrameState.ARENA)) {
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
		}
	    }
	};

	mapFrame.setVisible(true);

	clockTimer = new Timer(500, doOneStep);
	startTimer();
    }

    public void startTimer() {
    	clockTimer.setCoalesce(true);
    	clockTimer.start();
    }
}

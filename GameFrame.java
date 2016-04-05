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

    public GameFrame() {
	map = new Map(15, 20, player);
	arena = new Arena(player, new Human(1));
	mapFrame = new MapFrame(map);
	arenaFrame = new ArenaFrame(arena);
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
		    mapFrame.setVisible(false);
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

package brainstorm;

import java.util.ArrayList;
import java.util.Collection;

/**ListenedTo contains the common methods for the classes that are being listened to,
 * Player, Backpack, Arena and Map. */

public class ListenedTo
{
    protected Collection<GameListener> gameListeners = new ArrayList<GameListener>();

    protected void notifyListeners() {
    	for (GameListener gl : gameListeners) {
    	    gl.changed();
    	}
    }

    public void addListener(GameListener gl) {
    	gameListeners.add(gl);
    }
}

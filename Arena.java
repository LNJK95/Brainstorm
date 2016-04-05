package brainstorm;

public class Arena
{
    private Player player;

    public Arena(final Player player, final Enemy enemy) {
	this.player = player;
    }

    public Player getPlayer() {
	return player;
    }
}

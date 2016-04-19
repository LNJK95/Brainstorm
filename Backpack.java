package brainstorm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backpack
{
    private static final int BACKPACK_SIZE = 4;

    private Gear[][] gears;

    private List<GameListener> gameListeners = new ArrayList<GameListener>();

    public Backpack() {
	gears = new Gear[BACKPACK_SIZE][BACKPACK_SIZE];
	for (int r = 0; r < BACKPACK_SIZE; r++) {
	    for (int c = 0; c < BACKPACK_SIZE; c++) {
		gears[r][c] = Gear.NOTHING;
	    }
	}
    }

    public void resetBackpack() {
	gears = new Gear[BACKPACK_SIZE][BACKPACK_SIZE];
	for (int r = 0; r < BACKPACK_SIZE; r++) {
	    for (int c = 0; c < BACKPACK_SIZE; c++) {
		gears[r][c] = Gear.NOTHING;
	    }
	}
	notifyListeners();
    }

    public void addToBackpack(Gear gear) {
	boolean bool = true;
	    for (int r = 0; r < BACKPACK_SIZE; r++) {
		for (int c = 0; c < BACKPACK_SIZE; c++) {
		    if (gears[r][c] == Gear.NOTHING) {
			if (bool) {
			    gears[r][c] = gear;
			    bool = false;
			}
		    }
		}
	    }
	notifyListeners();
    }


    public void loot() {
	Random rnd = new Random();
	addToBackpack(Gear.BRAINS);
	addToBackpack(Gear.values()[rnd.nextInt(Gear.values().length)]);
	notifyListeners();
    }

    public void remove(int y, int x) {
	gears[y][x] = Gear.NOTHING;
	notifyListeners();
    }

    public void addListener(GameListener ml) {
	gameListeners.add(ml);
    }

    private void notifyListeners() {
	for (GameListener ml : gameListeners) {
	    ml.hasChanged();
	}
    }

    public int getSize() {
	return BACKPACK_SIZE;
    }

    public Gear getGear(int y, int x) {
	return gears[y][x];
    }
}

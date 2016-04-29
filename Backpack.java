package brainstorm;

import java.util.Random;

/** Backpack holds all the Gear the Player
 * acquires.*/

public class Backpack extends ListenedTo
{
    //The width and height of the backpack.
    private static final int BACKPACK_SIZE = 4;

    private Gear[][] gears;

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
	boolean canAdd = true;
	    for (int r = 0; r < BACKPACK_SIZE; r++) {
		for (int c = 0; c < BACKPACK_SIZE; c++) {
		    if (gears[r][c] == Gear.NOTHING) {
			if (canAdd) {
			    gears[r][c] = gear;
			    canAdd = false;
			}
		    }
		}
	    }
	notifyListeners();
    }

    public void loot() {
	Random rnd = new Random();
	addToBackpack(Gear.BRAINS);
	addToBackpack(Gear.values()[rnd.nextInt(Gear.values().length -1)]);
	notifyListeners();
    }

    public void remove(int y, int x) {
	gears[y][x] = Gear.NOTHING;
	notifyListeners();
    }

    public int getBackpackSize() {
	return BACKPACK_SIZE;
    }

    public Gear getGear(int y, int x) {
	return gears[y][x];
    }
}

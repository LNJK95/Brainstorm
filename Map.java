package brainstorm;

public class Map
{
    private final static int BORDER = 2;
    private SquareType[][] mapSquares;
    private int height;
    private int width;

    public Map(final int height, final int width) {
	this.height = height;
	this.width = width;

	mapSquares = new SquareType[height + BORDER*2][width + BORDER*2];

	for (int r = 0; r < height + BORDER*2; r++) {
	    for (int c = 0; c < width + BORDER*2; c++) {
		if( r < BORDER || c < BORDER || r >= height + BORDER || c >= width + BORDER) {
		    mapSquares[r][c] = SquareType.OUTSIDE;
		}
		else {
		    mapSquares[r][c] = SquareType.GRASS;
		}
	    }
	}
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
}

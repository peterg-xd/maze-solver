package mazeassignment;
/* * * * * Imports * * * * */
import java.awt.Color;
import java.awt.Graphics;
/**
 * [Maze.java]
 * This class represents a Maze
 * @author Peter Gao
 * @version 1.0
 * @since November 2021
 */
public class Maze {

	private final int TILE_WIDTH = 15;

	private char map[][];
	private int h, w;
	private int locations[] = new int[4];

	public Maze(char m[][], int h, int w) {
		this.map = m;
		this.h = h;
		this.w = w;
		this.locations = generateLoc();
		//locations[0] and locations[1] is the x y of start, locations[1] and locations[2] is the x y of end
		map[this.locations[1]][this.locations[0]] = Constants.START;
		map[this.locations[3]][this.locations[2]] = Constants.END;
	}

	private int[] generateLoc() {
		int ret[] = new int[4];
		for(int i=0; i<4; i+=2) {
			int x, y;
			do { 
				x = (int)(Math.random()*(this.w-1));
				y = (int)(Math.random()*(this.h-1));
			}while(map[y][x]!=Constants.OPEN);
			ret[i] = x; ret[i+1] = y;
		}
		return ret;
	}

	public void draw(Graphics g) {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]==Constants.WALL) {
					g.setColor(Color.BLACK);
				}else if(map[i][j]==Constants.END) {
					g.setColor(Color.RED);
				}else if(map[i][j]==Constants.START) {
					g.setColor(Color.GREEN);
				}else if(map[i][j]==Constants.PATH) {
					g.setColor(Color.BLUE);
				}else if(map[i][j]==Constants.DEADEND) {
					g.setColor(Color.GRAY);
				}else if(map[i][j]==Constants.OPEN) {
					g.setColor(Color.WHITE);
				}
				g.fillRect(j*this.TILE_WIDTH, i*this.TILE_WIDTH, this.TILE_WIDTH, this.TILE_WIDTH);
			}
		}
	}

	public char[][] getMap() {
		return this.map;
	}

	public int[] getLoc() {
		return this.locations;
	}

}

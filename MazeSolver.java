package mazeassignment;
/* * * * * Imports * * * * */
import java.util.Stack;
import java.util.concurrent.TimeUnit;
/**
 * [MazeSolver.java]
 * This class finds a path in a Maze using DFS
 * @author Peter Gao
 * @version 1.0
 * @since November 2021
 */
public class MazeSolver {

	private final int SLEEP_TIME = 10;
	private final int[] DIRECTIONS = {1,0,-1,0,0,1,0,-1};

	private char map[][];
	private boolean found;
	private int startX, startY, endX, endY;

	public void findExit(Maze m) {
		this.map = m.getMap();
		this.found = false;
		this.startX = m.getLoc()[0]; this.startY = m.getLoc()[1]; this.endX = m.getLoc()[2]; this.endY = m.getLoc()[3];

		dfs(startX, startY);
	}

	private void dfs(int curX, int curY) {
		try {
			TimeUnit.MILLISECONDS.sleep(this.SLEEP_TIME);
		} catch (InterruptedException e) {
		}
		if(curX==endX && curY==endY) {
			this.found = true;
			return; 
		}
		//continue traversing every valid direction
		for(int i=0; i<8; i+=2) {
			int newX = curX+this.DIRECTIONS[i], newY = curY+this.DIRECTIONS[i+1];
			if(newX>=0 && newX<map[0].length && newY>=0 && newY<map.length && (map[newY][newX]==Constants.OPEN || map[newY][newX]==Constants.END)) {
				if(map[newY][newX]==Constants.OPEN) {
					map[newY][newX] = Constants.PATH;
				}
				dfs(newX, newY);
				//stop searching for new paths
				if(this.found) {
					return;
				}
			}
		}

		//if no more new paths are being found, the current node does not lead to the final path, update it as an deadend
		map[curY][curX] = Constants.DEADEND; 
		try {
			TimeUnit.MILLISECONDS.sleep(this.SLEEP_TIME);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * [Point.java]
	 * This inner class represents a single square on the maze
	 * @author Peter Gao
	 * @version 1.0
	 * @since November 2021
	 */
	private class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

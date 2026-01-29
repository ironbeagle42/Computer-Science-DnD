import java.io.*;
import java.util.*;
import java.util.Stack;
import com.apple.laf.resources.aqua_zh_CN;
import java.util.Queue;

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make
// stacks and queues


public class CookieMonster {

	private int[][] cookieGrid;
	private int numRows;
	private int numCols;

	// Constructs a CookieMonster from a file with format:
	// numRows numCols
	// <<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try {
			Scanner input = new Scanner(new File(fileName));

			numRows = input.nextInt();
			numCols = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++)
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		} catch (Exception e) {
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int[][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows = cookieGrid.length;
		this.numCols = cookieGrid[0].length;
	}

	// You may find it VERY helpful to write this helper method. Or not!
	private boolean validPoint(int row, int col) {
		if (cookieGrid[row][col] == -1) {
			return false;
		}
		return true;
	}

	/*
	 * RECURSIVELY calculates the route which grants the most cookies. Returns the maximum number of
	 * cookies attainable.
	 */
	public int recursiveCookies() {
		return recursiveCookies(0, 0);
	}

	// Returns the maximum number of cookies edible starting from (and including)
	// cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		if (!validPoint(row, col)) {
			return -1;
		}
		int down = 0;
		int right = 0;
		if (row != cookieGrid.length - 1) {
			down = recursiveCookies(row + 1, col);
		}
		if (col != cookieGrid[0].length - 1) {
			right = recursiveCookies(row, col + 1);
		}
		if (down > right) {
			return down + cookieGrid[row][col];
		}
		return right + cookieGrid[row][col];
	}


	/*
	 * Calculate which route grants the most cookies using a QUEUE. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int queueCookies() {
		ArrayDeque<OrphanScout> queue = new ArrayDeque<OrphanScout>();
		OrphanScout orphan = new OrphanScout(0, 0, 0);
		queue.add(orphan);
		int maxCookies = 0;
		int numCookies = 0;
		int row = 0;
		int col = 0;
		while (!queue.isEmpty()) {
			orphan = queue.poll();
			row = orphan.getEndingRow();
			col = orphan.getEndingCol();
			if (cookieGrid[row][col] != -1) {
				numCookies = orphan.getCookiesDiscovered() + cookieGrid[row][col];
				if (col != cookieGrid[0].length - 1) {
					queue.add(new OrphanScout(row, col + 1, numCookies));
				}
				if (row != cookieGrid.length - 1) {
					queue.add(new OrphanScout(row + 1, col, numCookies));
				}
				if (numCookies > maxCookies && row == cookieGrid.length - 1 && col == cookieGrid[0].length - 1) {
					maxCookies = numCookies;
				}
			}
		}
		return maxCookies;
	}


	/*
	 * Calculate which route grants the most cookies using a stack. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int stackCookies() {
		Stack<OrphanScout> stack = new Stack<OrphanScout>();
		OrphanScout orphan = new OrphanScout(0, 0, 0);
		stack.push(orphan);
		int maxCookies = 0;
		int numCookies = 0;
		int row = 0;
		int col = 0;
		while (!stack.empty()) {
			orphan = stack.pop();
			row = orphan.getEndingRow();
			col = orphan.getEndingCol();
			if (cookieGrid[row][col] != -1) {
				numCookies = orphan.getCookiesDiscovered() + cookieGrid[row][col];
				if (row != cookieGrid.length - 1) {
					stack.push(new OrphanScout(row + 1, col, numCookies));
				}
				if (col != cookieGrid[0].length - 1) {
					stack.push(new OrphanScout(row, col + 1, numCookies));
				}
				if (numCookies > maxCookies && row == cookieGrid.length - 1 && col == cookieGrid[0].length - 1) {
					maxCookies = numCookies;
				}
			}
		}
		return maxCookies;
	}

}

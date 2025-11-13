import java.util.ArrayList;
import java.util.Arrays;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head.getNext() != null) {
			printListInReverse(head.getNext());
		}
		System.out.println(head.getValue());
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (!grid[r][c].equals("vaccinated") && !grid[r][c].equals("infected")) {
			grid[r][c] = "infected";
			if (grid[r - 1][c] != null) {
				infect(grid, r - 1, c);
			}
			if (grid[r + 1][c] != null) {
				infect(grid, r + 1, c);
			}
			if (grid[r][c - 1] != null) {
				infect(grid, r, c - 1);
			}
			if (grid[r][c + 1] != null) {
				infect(grid, r, c + 1);
			}
		}
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		// if (n == 1) {
		// return 2;
		// }
		// return n - 1 + countNonConsecutiveSubsets(n - 1);
		return fibonacciSequence(n + 2);

	}

	public static long fibonacciSequence(int n) {
		if (n <= 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		return fibonacciSequence(n - 1) + fibonacciSequence(n - 2);
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (n == 3) {
			return 4;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2)
				+ countWaysToJumpUpStairs(n - 3);

	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice
	public static void printSubsets(String str) {
		ArrayList<String> subsets = new ArrayList<>();
		merge(subsets, subsets(str));
		printFinal(subsets);
	}

	public static ArrayList<String> subsets(String str) {
		ArrayList<String> subsets2 = new ArrayList<>();
		ArrayList<String> subsets = new ArrayList<>();
		if (str.length() <= 1) {
			subsets.add("");
			subsets.add(str);
			return subsets;
		}
		String newStr = str.substring(0, str.length() - 1);
		subsets = merge(subsets(newStr),
				addLastLetter(subsets(newStr), str.substring(str.length() - 1)));
		return subsets;
	}

	public static ArrayList<String> addLastLetter(ArrayList<String> subsets, String letter) {
		for (int i = 0; i < subsets.size(); i++) {
			subsets.set(i, subsets.get(i) + letter);
		}
		return subsets;
	}

	public static ArrayList<String> merge(ArrayList<String> a1, ArrayList<String> a2) {
		for (int i = 0; i < a2.size(); i++) {
			a1.add(a2.get(i));
		}
		return a1;
	}


	public static void printFinal(ArrayList<String> subsets) {
		StringBuilder ret = new StringBuilder();
		ret.append("{");
		ret.append(subsets.get(0));
		for (int i = 1; i < subsets.size(); i++) {
			ret.append(", ");
			ret.append(subsets.get(i));
		}
		ret.append("}");
		System.out.println(ret);
	}



	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		ArrayList<String> chars = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			chars.add(str.substring(i, i + 1));
		}
		chars = makePermutations(chars);
		printFinal(chars);
	}

	public static ArrayList<String> makePermutations(ArrayList<String> chars) {
		ArrayList<String> subsets = new ArrayList<>();
		ArrayList<String> tempChars = new ArrayList<>();
		String startingChar = "";
		if (chars.size() == 1) {
			return chars;
		}
		for (int i = 0; i < chars.size(); i++) {
			tempChars = (ArrayList<String>)chars.clone();
			startingChar = chars.get(i);
			tempChars.remove(i);
			tempChars = makePermutations(tempChars);
			for (int j = 0; j < tempChars.size(); j++) {
				tempChars.set(j, (String)(startingChar + tempChars.get(j)));
			}
			// printFinal(tempChars);
			subsets = merge(subsets, tempChars);
		}
		return subsets;
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		int[] left = leftHalf(ints, ints.length / 2);
		int[] right = rightHalf(ints, ints.length / 2);
		mergeSort(left);
		mergeSort(right);
		ints = merge(left, right);
	}

	public static int[] leftHalf(int[] ints, int pivot) {

	}
	
	public static int[] rightHalf(int[] ints, int pivot) {

	}

	public static int[] merge(int[] ints1, int[] ints2) {
		int int1 = 0;
		int int2 = 0;
		int[] ret = new int[ints1.length + ints2.length];
		for (int i = 0; i < ret.length; i++) {
			if (int1 == ints1.length) {
				ret[i] = ints2[int2];
				int2++;
			} else if (int2 == ints2.length) {
				ret[i] = ints1[int1];
				int1++;
			} else if (ints1[int1] >= ints2[int2]) {
				ret[i] = ints2[int2];
				int2++;
			} else {
				ret[i] = ints1[int1];
				int1++;
			}
		}
		return ret;
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {

	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		printFinal(hanoiTowers(startingDisks, 1, 3));
		// System.out.println(hanoiTowers(startingDisks, 1, 3).size());

	}

	public static ArrayList<String> hanoiTowers(int startingDisks, int startTower, int endTower) {
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<String> tempMoves = new ArrayList<>();
		int thirdTower = 0;
		if (startTower == 1 || endTower == 1) {
			if (startTower == 3 || endTower == 3) {
				thirdTower = 2;
			} else {
				thirdTower = 3;
			}
		} else if (startTower == 2 || endTower == 2) {
			if (startTower == 3 || endTower == 3) {
				thirdTower = 1;
			} else {
				thirdTower = 3;
			}
		}
		if (startingDisks == 1) {
			moves.add(startTower + "-->" + endTower);
			return moves;
		}
		tempMoves = hanoiTowers(startingDisks - 1, startTower, thirdTower);
		tempMoves.add(startTower + "-->" + endTower);
		tempMoves = merge(tempMoves, hanoiTowers(startingDisks - 1, thirdTower, endTower));
		moves = merge(tempMoves, moves);
		return moves;
	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {
		return findMax(times, points, times[0]);
	}

	public static int findMax(int[] times, int[] points, int num) {
		int num2 = points[findIndex(times, num)];
		if (num >= times[times.length - 1]) {
			return 0;
		}
		if (num2 + findMax(times, points, num + 5) >= findMax(times, points, num + 1)) {
			return num2 + findMax(times, points, num+5);
		}
		return findMax(times, points, num+1);
	}

	public static int findIndex(int[] times, int num) {
		for (int i = 0; i < times.length; i++) {
			if (times[i] >= num) {
				return i;
			}
		}
		return 0;
	}

}

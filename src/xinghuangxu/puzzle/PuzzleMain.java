package xinghuangxu.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PuzzleMain {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("test")) {
			testAll();
		} else {
			Scanner in = new Scanner(System.in);
			byte[][] startState = getStateInteractively(3, "start", in);
			byte[][] goalState = getStateInteractively(3, "goal", in);
			Puzzle solution = PuzzleSolver.solve(startState, goalState);
			if (solution == null) {
				System.out
						.println("Sorry this puzzle is not solvable because the number of inversions is even.");
			} else {
				Puzzle temp = solution;
				List<Puzzle> steps = new ArrayList<Puzzle>();
				while (temp != null) {
					steps.add(0, temp);
					temp = temp.getParent();
				}
				for (int i = 0; i < steps.size(); i++) {
					System.out.println("Step " + i + ":");
					System.out.println(steps.get(i).toString());
				}
			}
			in.close();
		}

	}

	private static void testAll() {
		byte[][][] startCollection = {
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }, // 0
				{ { 4, 1, 5 }, { 7, 3, 6 }, { 0, 2, 8 } }, // 12 steps
				{ { 0, 3, 6 }, { 4, 8, 7 }, { 1, 5, 2 } }, // 24
				{ { 8, 6, 7 }, { 2, 0, 5 }, { 3, 1, 4 } }, // 28
				{ { 3, 1, 7 }, { 6, 4, 2 }, { 5, 8, 0 } }, // 24
				{ { 5, 8, 7 }, { 1, 0, 2 }, { 6, 4, 3 } }, // 20 steps!!!
				{ { 5, 3, 0 }, { 2, 6, 7 }, { 8, 4, 1 } }, // 24 steps!!!
				{ { 4, 8, 2 }, { 1, 3, 7 }, { 0, 5, 6 } }, // 26 steps!!!
				{ { 8, 6, 3 }, { 7, 0, 5 }, { 4, 2, 1 } }, // 26
				{ { 8, 7, 6 }, { 5, 4, 3 }, { 2, 1, 0 } }, // 30
				{ { 4, 2, 5 }, { 3, 0, 1 }, { 6, 7, 8 } }, // not solvable
				{ { 4, 2, 5 }, { 3, 0, 1 }, { 6, 7, 8 } }, // not solvable
				{ { 7, 0, 2 }, { 8, 5, 3 }, { 6, 4, 1 } }, // not solvable
				{ { 1, 2, 3 }, { 4, 5, 0 }, { 6, 7, 8 } }, // 13
				{ { 8, 0, 6 }, { 5, 4, 7 }, { 2, 3, 1 } }, // 31
				{ { 1, 2, 3 }, { 4, 5, 0 }, { 6, 7, 8 } }, // not solvable
		};
		byte[][][] goalCollection = {
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } },
				{ { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } },
				{ { 7, 0, 2 }, { 8, 5, 3 }, { 6, 4, 1 } }, };
		int[] solution = { 0, 12, 24, 28, 24, 20, 24, 26, 26, 30, -1, -1, -1,
				13, 31, -1 };
		long start = System.nanoTime();
		for (int i = 0; i < startCollection.length; i++) {
			testSingle(startCollection[i], goalCollection[i], solution[i]);
		}
		long end = System.nanoTime();
		System.out.println("Time Consumption:" + ((end - start) / 1000000));
	}

	private static void testSingle(byte[][] startState, byte[][] goalState,
			int sol) {
		Puzzle solution = PuzzleSolver.solve(startState, goalState);
		Puzzle temp = solution;
		List<Puzzle> steps = new ArrayList<Puzzle>();
		while (temp != null) {
			steps.add(0, temp);
			temp = temp.getParent();
		}
		if (sol + 1 == steps.size() || (steps.size() == 0 && sol == -1)) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
	}

	private static byte[][] getStateInteractively(int size, String tile,
			Scanner in) {
		byte[][] state = new byte[size][size];
		int max = size * size - 1;
		int min = 0;
		boolean[] visited = new boolean[size * size];

		int x, y;
		int nextNum = 0;
		for (int i = size - 1; i >= 0; i--) {

			for (int j = 0; j < size;) {
				boolean next = false;
				x = (size - 1 - i);
				while (!next) {
					System.out.println("Enter value for the tile located at ("
							+ i + ", " + j + ") coordinate position for "
							+ tile + " configuration :");
					nextNum = in.nextInt();
					if (nextNum < min || nextNum > max) {
						System.out.println("Number " + nextNum
								+ " out of range.(" + min + "-" + max + ")");
					} else if (visited[nextNum]) {
						System.out
								.println("Number "
										+ nextNum
										+ " has been picked before. Please try a different number.");
					} else {
						visited[nextNum] = true;
						state[x][j] = (byte) nextNum;
						next = true;
						j++;
					}
				}
			}
		}

		return state;
	}

}

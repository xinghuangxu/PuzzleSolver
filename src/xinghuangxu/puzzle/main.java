package xinghuangxu.puzzle;

import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
//		Integer[] startState = getStateInteractively(3);
//		Integer[] goalState = getStateInteractively(3);
		int[][] startState ={{1,2,3},{4,5,6},{7,8,0}};
		int[][] goalState ={{0,1,2},{3,4,5},{6,7,8}};
		Puzzle solution=PuzzleSolver.solve(startState,goalState);
	}

	private static Integer[] getStateInteractively(int size) {
		Integer[] state = new Integer[size * size];
		int max = size * size - 1;
		int min = 0;
		boolean[] visited = new boolean[size * size];
		Scanner in = new Scanner(System.in);
		int tempIndex;
		int nextNum = 0;
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size;) {
				tempIndex = (size - 1 - i) * size + j;
				while (state[tempIndex] == null) {
					System.out
							.println("Enter value for the tile located at ("
									+ i
									+ ", "
									+ j
									+ ") coordinate position for start configuration :");
					nextNum = in.nextInt();
					if (nextNum < min || nextNum > max) {
						System.out.println("Number " + nextNum
								+ " out of range.("+min+"-"+max+")");
					}
					else if (visited[nextNum]) {
						System.out
								.println("Number "
										+ nextNum
										+ " has been picked before. Please try a different number.");
					} else {
						visited[nextNum] = true;
						state[tempIndex] = nextNum;
						j++;
					}
				}
			}
		}
		in.close();
		return state;
	}

}

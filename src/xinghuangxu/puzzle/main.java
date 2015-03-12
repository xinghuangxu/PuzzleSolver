package xinghuangxu.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
//		Integer[] startState = getStateInteractively(3);
//		Integer[] goalState = getStateInteractively(3);
//		int[][] startState ={{1,2,3},{4,5,6},{7,8,0}};
//		int[][] startState ={{7,2,4},{5,0,6},{8,3,1}};
//		int[][] startState ={{4,2,5},{3,0,1},{6,7,8}}; //not solvable?
//		int[][] startState ={{8,1,0},{7,5,3},{2,4,6}};
//		int[][] startState ={{4,1,5},{7,3,6},{0,2,8}}; //12 steps
//		int[][] startState ={{8,1,0},{7,5,3},{2,4,6}};
//		int[][] startState ={{0,3,6},{4,8,7},{1,5,2}}; //24
//		int[][] startState ={{8,6,7},{2,0,5},{3,1,4}}; //28
//		int[][] startState ={{3,1,7},{6,4,2},{5,8,0}}; //24
		int[][] startState ={{5,8,7},{1,0,2},{6,4,3}}; //20 steps!!!
		int[][] goalState ={{1,2,3},{4,5,6},{7,8,0}};
		Puzzle solution=PuzzleSolver.solve(startState,goalState);
		Puzzle temp=solution;
		List<Puzzle> steps=new ArrayList<Puzzle>();
		while(temp!=null){
			steps.add(0,temp);
			temp=temp.getParent();
		}
		for(int i=0;i<steps.size();i++){
			System.out.println("Step "+i+":");
			System.out.println(steps.get(i).toString());
			System.out.println("\n");
		}
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

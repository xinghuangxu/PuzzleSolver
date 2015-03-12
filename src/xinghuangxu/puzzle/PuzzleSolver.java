package xinghuangxu.puzzle;

import java.util.HashSet;
import java.util.PriorityQueue;


public class PuzzleSolver {
	
	public static int size=3;
	private static int[] gx;
	private static int[] gy;

	private static PriorityQueue<Puzzle> frontier;
	public static Puzzle solve(byte[][] startState, byte[][] goalState) {
		if(!isSolvable(startState,goalState)){//if not solvable return null
			return null;
		}
		frontier=new PriorityQueue<Puzzle>();
		Puzzle.Size=size;
		gx=new int[size*size];
		gy=new int[size*size];
		int blankx=0;
		int blanky=0;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(startState[i][j]==0){
					blankx=i;
					blanky=j;
				}
				gx[goalState[i][j]]=i;
				gy[goalState[i][j]]=j;
			}
		}
		Puzzle start=new Puzzle(startState,null,blankx,blanky);
		
		start.setHeuristic(CalculateHeuristicCost(startState, gx, gy));
		HashSet<Puzzle> visited=new HashSet<Puzzle>();
		frontier.add(start);
		visited.add(start);
		while(!frontier.isEmpty()){
			Puzzle temp=frontier.poll();
			if(temp.H==0){
				return temp;
			}else{
				for(Action a: Action.values()){
					Puzzle newPuzzle=temp.move(a);
					if(newPuzzle!=null&&!visited.contains(newPuzzle)){
						newPuzzle.setHeuristic(CalculateHeuristicCost(newPuzzle.State, gx, gy));
						frontier.add(newPuzzle);
						visited.add(newPuzzle);
					}
				}
			}
		}
		return null;
	}
	
	private static boolean isSolvable(byte[][] startState, byte[][] goalState) {
		byte[] start=new byte[size*size];
		byte[] goal=new byte[size*size];
		byte[] goalLoc=new byte[size*size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				start[i*size+j]=startState[i][j];
				goal[i*size+j]=goalState[i][j];
				goalLoc[goal[i*size+j]]=(byte) (i*size+j+1);
			}
		}
		for(int i=0;i<size*size;i++){
			if(start[i]!=0){
				start[i]=goalLoc[start[i]];
			}
		}
		int inversion=0;
		for(int i=0;i<size*size-1;i++){
			for(int j=i+1;j<size*size;j++){
				if(start[i]>start[j]){
					if(start[j]!=0)
						inversion++;
				}
			}
		}
		if(inversion%2==0)return true;
		return false;
	}

	public static int CalculateHeuristicCost(byte[][] state,int[] gx,int[] gy){
		int h=0;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(state[i][j]!=0){
					h+=Math.abs(i-gx[state[i][j]]);
					h+=Math.abs(j-gy[state[i][j]]);
				}
			}
		}
		return h;
	}
	
}

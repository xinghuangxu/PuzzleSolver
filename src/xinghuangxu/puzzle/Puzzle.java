package xinghuangxu.puzzle;

public class Puzzle implements Comparable<Puzzle>,Cloneable {
	public static int Size;
	private int F;//heuristic function f(n)=g(n)+h(n)
	private int G;
	public int H;
	public int[][] State;
	private Puzzle parent;
	private int blankx;
	private int blanky;
	
	public Puzzle(int[][] state,Puzzle parent,int blankx,int blanky){
		this.parent=parent;
		this.State=state;
		this.blankx=blankx;
		this.blanky=blanky;
	}
	
	public void setHeuristic(int h){
		this.H=h;
		this.G=parent.G+1;
		this.F=this.G+this.H;
	}
	
	protected Puzzle clone() {
		int[][] newState=new int[Puzzle.Size][Puzzle.Size];
		for(int i=0;i<Puzzle.Size;i++){
			for(int j=0;j<Puzzle.Size;j++){
				newState[i][j]=this.State[i][j];
			}
		}
        Puzzle temp=new Puzzle(newState,parent,blankx,blanky);
        return temp;
    }
	
	
	@Override
	public int compareTo(Puzzle o) {
		if(this.F>o.F){
			return 1;
		}else if(this.F<o.F){
			return -1;
		}
		return 0;
	}


	public Puzzle move(Action a) {
		switch(a){
		case UP:
			if(blankx!=0){
				Puzzle temp=this.clone();
				temp.moveBlank(a);
			}
			break;
		case DOWN:
			
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
		return null;
	}

	private void moveBlank(Action a) {
		int temp;
		switch(a){
		case UP:
			temp=State[blankx][blanky];
			State[blankx][blanky]=State[blankx-1][blanky];
			State[blankx-1][blanky]=temp;
			blankx=blankx-1;
			break;
		case DOWN:
			temp=State[blankx][blanky];
			State[blankx][blanky]=State[blankx+1][blanky];
			State[blankx+1][blanky]=temp;
			blankx=blankx+1;
			break;
		case LEFT:
			temp=State[blankx][blanky];
			State[blankx][blanky]=State[blankx][blanky-1];
			State[blankx][blanky-1]=temp;
			blanky=blanky-1;
			break;
		case RIGHT:
			temp=State[blankx][blanky];
			State[blankx][blanky]=State[blankx][blanky+1];
			State[blankx][blanky+1]=temp;
			blanky=blanky+1;
			break;
		}
	}
}

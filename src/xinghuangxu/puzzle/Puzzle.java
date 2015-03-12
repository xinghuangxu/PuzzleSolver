package xinghuangxu.puzzle;

import java.util.Arrays;

public class Puzzle implements Comparable<Puzzle>, Cloneable {
	public static int Size;
	private int F;// heuristic function f(n)=g(n)+h(n)
	private int G;
	public int H;
	public byte[][] State;
	private Puzzle parent;
	private int blankx;
	private int blanky;

	@Override
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < Puzzle.Size; i++) {
			for (int j = 0; j < Puzzle.Size; j++) {
				hash = hash * 10 + this.State[i][j];
			}
		}
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
//		Puzzle temp=(Puzzle)obj;
		if (obj.hashCode() == this.hashCode()) {
			return true;
		} else
			return false;
	}

	public Puzzle getParent() {
		return this.parent;
	}

	public Puzzle(byte[][] state, Puzzle parent, int blankx, int blanky) {
		this.parent = parent;
		this.State = state;
		this.blankx = blankx;
		this.blanky = blanky;
	}

	public void setHeuristic(int h) {
		this.H = h;
		this.G = parent == null ? 0 : parent.G + 1;
		this.F = this.G + this.H;
	}

	protected Puzzle clone() {
		byte[][] newState = new byte[Puzzle.Size][Puzzle.Size];
		for (int i = 0; i < Puzzle.Size; i++) {
			for (int j = 0; j < Puzzle.Size; j++) {
				newState[i][j] = this.State[i][j];
			}
		}
		Puzzle temp = new Puzzle(newState, this, blankx, blanky);
		return temp;
	}

	@Override
	public int compareTo(Puzzle o) {
		if (this.F > o.F) {
			return 1;
		} else if (this.F < o.F) {
			return -1;
		}
		return 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Puzzle.Size; i++) {
			for (int j = 0; j < Puzzle.Size; j++) {
				sb.append(this.State[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Puzzle move(Action a) {
		Puzzle temp = null;
		switch (a) {
		case UP:
			if (blankx != 0) {
				temp = this.clone();
				temp.State[blankx][blanky] = temp.State[blankx - 1][blanky];
				temp.State[blankx - 1][blanky] = 0;
				temp.blankx = blankx - 1;
			}
			break;
		case DOWN:
			if (blankx != Puzzle.Size - 1) {
				temp = this.clone();
				temp.State[blankx][blanky] = temp.State[blankx + 1][blanky];
				temp.State[blankx + 1][blanky] = 0;
				temp.blankx = blankx + 1;
			}
			break;
		case LEFT:
			if (blanky != 0) {
				temp = this.clone();
				temp.State[blankx][blanky] = temp.State[blankx][blanky - 1];
				temp.State[blankx][blanky - 1] = 0;
				temp.blanky = blanky - 1;
			}
			break;
		case RIGHT:
			if (blanky != Puzzle.Size - 1) {
				temp = this.clone();
				temp.State[blankx][blanky] = temp.State[blankx][blanky + 1];
				temp.State[blankx][blanky + 1] = 0;
				temp.blanky = blanky + 1;
			}
			break;
		}
		return temp;
	}

}

package mines;

import java.util.ArrayList;
import java.util.Random;

public class Mines {

	private int height,width,numMines;
	private Place[][] board;
	private boolean showAll;

	private class Place{ //class which implements a place in the board
		int x,y,mine_beside;
		boolean mine,flag,opened;

		Place(int x, int y){
			this.x=x;
			this.y=y;
			mine_beside=0;
		}
	}

	//constructor initial board and randomize numMines mines
	public Mines(int height, int width, int numMines) { 
		int x,y;
		Random rand =new Random();
		this.height = height;
		this.width = width;
		this.numMines = numMines;
		board=new Place[height][width];
		for (int i=0; i<height; i++)//build places
			for (int j=0; j<width; j++)
				board[i][j] = new Place(i,j);
		if (numMines<height*width) { //add random mines only if it is not above the size of board
			for (int i=0; i<numMines; i++) {
				do {
					x=rand.nextInt(height);
					y=rand.nextInt(width);
				}while (addMine(x,y)==false); // check that there isn't a mine already there 		
			}
		}
	}

	/*
	 *  a function for adding a mine (only after constructor and before other methods)
	 */
	public boolean addMine(int i, int j) {
		if (i<0 || i>=height) //place is not legal
			return false;
		if (j<0 || j>=width) //place is not legal
			return false;
		if ((board[i][j]).mine==true)
			return false;
		board[i][j].mine=true; //a mine was added
		this.numMines++;
		for (Place p : this.neighbors(i,j))//updating neighbors
			p.mine_beside++;
		return true;
	}
	/*
	 * function will return a list of neighbors of a given square
	 */
	private ArrayList<Place> neighbors(int x, int y) { //return a list of all the neighbors
		ArrayList<Place> l =new ArrayList<Place>();
		if (x>0) {
			l.add(board[x-1][y]);
			if(y>0)
				l.add(board[x-1][y-1]);
			if (y<width-1)
				l.add(board[x-1][y+1]);
		}
		if(y>0)
			l.add(board[x][y-1]);
		if (y<width-1)
			l.add(board[x][y+1]);

		if (x<height-1) {
			l.add(board[x+1][y]);
			if(y>0)
				l.add(board[x+1][y-1]);
			if (y<width-1)
				l.add(board[x+1][y+1]);
		}
		return l;
	}

	/*
	 *  open a square - return false if a mine is there
	 */
	public boolean open(int i, int j) {
		if (board[i][j].opened != true) {
			if (board[i][j].mine) // a mine in this square
				return false;

			board[i][j].opened=true;

			if (board[i][j].mine_beside==0) //open neighbors
				for (Place p : this.neighbors(i, j))
					open(p.x,p.y);
		}
		return true;
	}
	/*
	 *put or delete flag 
	 */
	public void toggleFlag(int x, int y) {
		if (board[x][y].flag) //delete
			board[x][y].flag=false;
		else //put flag
			board[x][y].flag=true;
	}
	/*
	 * return true if all the squares that are not mines are open
	 */
	public boolean isDone() {
		int cnt=0;
		for (int i=0; i<height; i++)
			for (int j=0; j<width; j++)
				if (board[i][j].opened)
					cnt++;
		return ((height*width)-this.numMines==cnt);
	}

	public String get(int i, int j) {
		if (!showAll) {
			if (!board[i][j].opened) {
				if (board[i][j].flag)
					return "F";
				else
					return ".";
			}
			if (board[i][j].mine)
				return "X";
			else {
				if(board[i][j].mine_beside==0)
					return " ";
				return ""+board[i][j].mine_beside;
			}
		}
		else { //show all
			if (board[i][j].flag)//in the original game when loosing the flags stay closed
				return "F";
			if (board[i][j].mine)
				return "X";
			if(board[i][j].mine_beside==0)
				return " ";
			return ""+board[i][j].mine_beside;
		}
	}
	public void setShowAll(boolean showAll) {
		this.showAll=showAll;
	}
	public String toString() {
		String st="";
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++)
				st+=this.get(i, j);
			st+="\n";
		}
		return st;
				
	}
	//for gui implementation
	public boolean isOpen(int i,int j) {
		return board[i][j].opened;
	}
	public boolean isLost() {
		return this.showAll;
	}
}

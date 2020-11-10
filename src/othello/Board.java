package othello;
	public class Board{
		
//		this is the Board class to handle the board related part of the game 
		
		private char board[][];//this is the board on which the game is to be played
		private int boardSize;//this is the size of that square board	
		
		public Board(int boardSize, char ch1, char ch2) {
			
			this.boardSize=boardSize;//set the board size to the given value
			
			board=new char[boardSize][boardSize];//make a board of that size
			
			int index=(boardSize-1)/2;//setting the initial arrangement of the board
			board[index][index]=ch2;
			board[index+1][index+1]=ch2;
			board[index][index+1]=ch1;
			board[index+1][index]=ch1;
					
		}
		
		public boolean isFull() {//checking whether board is full or not
			
			for(int i=0;i<boardSize;i++) {
				for(int j=0;j<boardSize;j++) {
					if(board[i][j]=='\0') {
						return false;
					}
				}
			}
			return true;
			
		}
		
		public boolean inRange(int x, int y) {//to check if x, y point is within board range or not
			
			return (x>=0 && x<boardSize && y>=0 && y<boardSize);
			
		}
		
		public boolean isAvailable(int x, int y) {//to check if x, y is a valid point in terms of availability
			
			return board[x][y]=='\0';
			
		}
		
		public boolean canMove(int x, int y, char ch) {//to check if x, y is a valid point or not in terms of rules of game
						
			int arr[][]= {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};			
			
			for(int i=0;i<8;i++) {				
				int X=x+arr[i][0];
				int Y=y+arr[i][1];	
				int count=0;
				while(inRange(X, Y) && board[X][Y]!='\0' && board[X][Y]!=ch) {					
					X+=arr[i][0];
					Y+=arr[i][1];
					count++;
				}
				if(inRange(X, Y) && board[X][Y]==ch && count>=1) {					
					return true;					
				}				
			}
			return false;
			
		}
		
		public void makeMove(int x, int y, char ch) {//to make a move at x, y point if a move can be made there
			
			int arr[][]= {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};		
			
			board[x][y]=ch;
			for(int i=0;i<8;i++) {				
				int X=x+arr[i][0];
				int Y=y+arr[i][1];	
				int count=0;
				while(inRange(X, Y) && board[X][Y]!='\0' && board[X][Y]!=ch) {
					X+=arr[i][0];
					Y+=arr[i][1];
					count++;
				}
				if(inRange(X, Y) && board[X][Y]==ch && count>=1) {					
					X=x+arr[i][0];
					Y=y+arr[i][1];
					while(inRange(X, Y) && board[x][y]!='\0' && board[X][Y]!=ch) {
						board[X][Y]=ch;
						X+=arr[i][0];
						Y+=arr[i][1];
					}
				}
			}			
					
		}
		
		public char getWinner(char ch1, char ch2) {//to evaluate the winner of the game when no further moves can be made
			
			int count1=0;
			int count2=0;
			for(int i=0;i<boardSize;i++) {
				for(int j=0;j<boardSize;j++) {
					if(board[i][j]==ch1) {
						count1++;
					}
				}
			}
			count2=((boardSize*boardSize)-count1);
			
			System.out.println("player1("+ch1+")'s score is : "+count1);
			System.out.println("player2("+ch2+")'s score is : "+count2);
			
			if(count1>count2) {
				return ch1;
			}
			else if(count1<count2) {
				return ch2;
			}
			else {
				return '\0';
			}
			
		}
		
		public void print() {//to print the board
			
			System.out.print("   ");
			for(int i=0;i<boardSize;i++) {
				System.out.print("---");
			}
			System.out.println();
			
			System.out.print("   ");
			for(int i=0;i<boardSize;i++) {
				System.out.print("|"+i+"|");
			}
			System.out.println();
			
			for(int i=0;i<boardSize;i++) {				
				System.out.print("|"+i+"|");
				for(int j=0;j<boardSize;j++) {
					if(board[i][j]=='\0') {
						System.out.print("| |");
					}
					else {
						System.out.print("|"+board[i][j]+"|");
					}
				}
				System.out.println();
			}
			
			System.out.print("   ");
			for(int i=0;i<boardSize;i++) {
				System.out.print("---");
			}
			System.out.println();
			
		}
		
		public boolean noMovesAvailable(char ch) {
			
			for(int i=0;i<boardSize;i++) {
				for(int j=0;j<boardSize;j++) {
					if(board[i][j]=='\0' && canMove(i, j, ch)) {
						return false;
					}
				}
			}
			return true;
			
		}
		
	}
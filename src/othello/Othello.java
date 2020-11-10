package othello;

import java.util.Scanner;

public class Othello{
	
//		this Othello is the manager class which is responsible for conducting the game
		
		private Player player1, player2;//these are the two game players
		private Board board;//this is the board on which the game is to be played
		
		public Othello() {//default constructor of this class
						
			
		}
		
		public void startGame() {
			
			Scanner s=new Scanner(System.in);
			
			player1=takeInput(1);//taking input of player1
			player2=takeInput(2);//taking input of player2
			
			System.out.println("enter the side length(even and >=4) of board : ");
			int boardSize=s.nextInt();//getting the board size
			
			while(!(boardSize>=4) || !(boardSize%2==0)) {//to make sure we get a valid board size i.e. even and >=4
				System.out.println("enter a valid side length(even and >=4) : ");
				boardSize=s.nextInt();
			}
			
			board=new Board(boardSize, player1.getSymbol(), player2.getSymbol());//making a Board object
			
			System.out.println("the initial arrangement of board is : ");
			board.print();//printing the initial arrangement of the board
			
			boolean player1Turn=true;//first turn is of player1 by default
		
			while(!board.isFull()) {//to keep playing till the board is full
				
				if(player1Turn) {
					if(board.noMovesAvailable(player1.getSymbol())) {//shift the turn to the other player if the current 
//						player does not have a move						
						System.out.println("player1 ("+player1.getSymbol()+") cannot make a move");	
						player1Turn=false;
						continue;
					}
					else {
						System.out.println("enter player1("+player1.getSymbol()+")'s move : ");
					}
				}
				else {
					if(board.noMovesAvailable(player2.getSymbol())) {
						System.out.println("player2 ("+player2.getSymbol()+") cannot make a move");
						player1Turn=true;
						continue;
					}
					else {
						System.out.println("enter player2("+player2.getSymbol()+")'s move : ");
					}
				}
				
				int x=s.nextInt();
				int y=s.nextInt();
				
				while(!board.inRange(x, y) || !(board.isAvailable(x, y))) {//to check if the move made by the player is valid
//					or not in terms of range and availability 
					System.out.println("enter a valid point : ");
					x=s.nextInt();
					y=s.nextInt();				
				}				
				
				if(player1Turn) {										
					while(!board.canMove(x, y, player1.getSymbol())) {//to check if the move made by the player is valid or 
//						not in terms of the rules of the game
						System.out.println("enter a point where a move can be made : ");					
						x=s.nextInt();
						y=s.nextInt();
					}					
				}
				else {										
					while(!board.canMove(x, y, player2.getSymbol())) {								
						System.out.println("enter a point where a move can be made : ");					
						x=s.nextInt();
						y=s.nextInt();
					}							
				}
				
				if(player1Turn) {
					board.makeMove(x, y, player1.getSymbol());//make the move at the x, y point
					player1Turn=false;//shift the turn to the other player
				}
				else {
					board.makeMove(x, y, player2.getSymbol());
					player1Turn=true;
				}
				
				board.print();//print the board after a move is made
				
			}			
			
			char ch=board.getWinner(player1.getSymbol(), player2.getSymbol());//evaluate the winner after the board is full
			
			if(ch==player1.getSymbol()) {
				System.out.println("PLAYER1 ("+player1.getName()+") WINS!!!");
			}
			else if(ch==player2.getSymbol()){
				System.out.println("PLAYER2 ("+player2.getName()+") WINS!!!");
			}
			else {
				System.out.println("ITS A DRAW...");
			}
			
		}
		
		private Player takeInput(int num) {//to take input of player and return a Player object
						
			Scanner s=new Scanner(System.in);
						
			System.out.println("enter the name of player"+num+"(one word) : ");
			String name=s.next();//take input of name
			
			System.out.println("enter the symbol of player"+num+"(one character) : ");			
			String str=s.next();
			char ch=str.charAt(0);//player's symbol						
			
			Player p=new Player(name, ch);//make Player object
			return p;//return the Player
			
		}
		
	}
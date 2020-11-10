package othello;
	public class Player{
		
//		this is the Player class to handle the player related part of the game
		
		private String name;//this is the name of the player
		private char symbol;//this is the symbol of the player
			
		public 	Player(String name, char symbol) {
			
			this.name=name;//setting the name to given value		
			this.symbol=symbol;//setting the symbol to given value
			
		}
		
		public void setName(String name) {//to set the name of the player
			
			this.name=name;
			
		}
		
		public String getName() {//to get the name of the player
			
			return this.name;
			
		}
		
		public void setSymbol(char symbol) {//to set the symbol of the player
			
			this.symbol=symbol;
			
		}
		
		public char getSymbol() {//to get the symbol of the player
			
			return this.symbol;
			
		}
		
	}
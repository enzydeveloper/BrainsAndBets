package com.bb.game.gameObjects;

public class Guess{
        private String guess = "";     
        private Bet bet;
        
        public Guess(String guess, Bet bet){
                this.guess = guess;
                this.bet = bet;
        }
        
        public Guess(String guess){
                this.guess = guess;
        }

        public String getGuessString(){
                return guess;
        }
		public void setGuess(String guess) {
			this.guess = guess;
		}

		public Bet getBet() {
			return bet;
		}

		public void setBet(Bet bet) {
			this.bet = bet;
		}

//		/* (non-Javadoc)
//		 * @see java.lang.Object#equals(java.lang.Object)
//		 */
//		@Override
//		public boolean equals(Object o) {
//			if(o instanceof Guess){
//				this.getGuess()
//			}
//			
//			return super.equals(this);
//		}
}

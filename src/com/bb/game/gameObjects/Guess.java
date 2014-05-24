package com.bb.game.gameObjects;

public class Guess{
        private String playerName;
        private int guess;
        private int odds;
        
        public String getName(){
                return playerName;
        }
        
        public int getGuess(){
                return guess;
        }
        
        public Guess(int guess, int odds){
                this.guess = guess;
                this.odds = odds;
        }
        
        public Guess(String playerName, int guess){
                this.playerName = playerName;
                this.guess = guess;
        }

        public void setOdds(int odds) {
                this.odds = odds;
        }

        public int getOdds() {
                return odds;
        }
}

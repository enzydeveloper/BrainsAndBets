package com.bb.game.gameObjects;

public class Bet {
        private String playerName;
        private int choice;
        private int permanentChips;
        private int gameChips;
        
        public String getName(){
                return playerName;
        }
        
        public int getChoice(){
                return choice;
        }
        
        public int getPermaChips() {
                return permanentChips;
        }
        
        public int getGameChips() {
                return gameChips;
        }
        
        public Bet(String playerName, int choice, int permanentChips, int gameChip){
                this.playerName = playerName;
                this.choice = choice;
                this.permanentChips = permanentChips;
                this.gameChips = gameChip;
        }
}

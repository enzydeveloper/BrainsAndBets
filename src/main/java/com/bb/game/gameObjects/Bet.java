package com.bb.game.gameObjects;

import java.util.UUID;

public class Bet {
        private UUID playerUUID;
        private String choice;
        private int permanentChips;
        private int gameChips;
        
        
		public UUID getPlayerUUID() {
			return playerUUID;
		}

		public String getChoice(){
                return choice;
        }
        
        public int getPermaChips() {
                return permanentChips;
        }
        
        public int getGameChips() {
                return gameChips;
        }
        
        /**
         * 
         * @param playerUUID
         * @param choice
         * @param permanentChips
         * @param gameChip
         */
        public Bet(UUID playerUUID, String choice, int permanentChips, int gameChip){
                this.playerUUID = playerUUID;
                this.choice = choice;
                this.permanentChips = permanentChips;
                this.gameChips = gameChip;
        }
}

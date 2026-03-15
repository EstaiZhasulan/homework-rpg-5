package com.narxoz.rpg.facade;


public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward (invalid result)";
        }
        if (battleResult.getWinner() == null) {
            return "No reward (unknown winner)";
        }

        String heroName = battleResult.getHeroName();
        String winner = battleResult.getWinner();

        if ("Draw".equalsIgnoreCase(winner)) {
            return "No reward (draw)";
        }

        boolean heroWon = heroName != null && heroName.equals(winner);
        if (!heroWon) {
            return "Consolation: 5 gold";
        }

        int rounds = battleResult.getRounds();
        if (rounds <= 3) {
            return "Legendary chest: 120 gold";
        }
        if (rounds <= 6) {
            return "Rare chest: 60 gold";
        }
        return "Common chest: 30 gold";
    }
}

package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }
    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult finalResult = new AdventureResult();

        finalResult.setHeroName(hero != null ? hero.getName() : null);
        finalResult.setBossName(boss != null ? boss.getName() : null);

        finalResult.addLine("=== Adventure Start ===");

        String preparationSummary = preparationService.prepare(hero, boss, action);
        finalResult.addLine(preparationSummary);

        if (preparationSummary.startsWith("Preparation FAILED")) {
            finalResult.setWinner("Draw");
            finalResult.setRounds(0);
            finalResult.setReward("No reward (preparation failed)");
            finalResult.addLine("=== Adventure End ===");
            return finalResult;
        }

        AdventureResult battleResult = battleService.battle(hero, boss, action);
        for (String line : battleResult.getLog()) {
            finalResult.addLine(line);
        }
        finalResult.setWinner(battleResult.getWinner());
        finalResult.setRounds(battleResult.getRounds());

        String reward = rewardService.determineReward(finalResult);
        finalResult.setReward(reward);
        finalResult.addLine("Reward: " + reward);

        finalResult.addLine("=== Adventure End ===");
        return finalResult;
    }
}

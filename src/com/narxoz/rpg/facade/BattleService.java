package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Objects;
import java.util.Random;


public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        Objects.requireNonNull(hero, "hero must not be null");
        Objects.requireNonNull(boss, "boss must not be null");
        Objects.requireNonNull(action, "action must not be null");

        AdventureResult result = new AdventureResult();
        result.addLine("=== Battle Start ===");

        boolean heroFirst = random.nextBoolean();
        result.addLine("Initiative: " + (heroFirst ? hero.getName() : boss.getName()) + " starts");

        int rounds = 0;
        final int maxRoundsSafety = 1_000;

        while (hero.isAlive() && boss.isAlive() && rounds < maxRoundsSafety) {
            rounds++;
            result.addLine("");
            result.addLine("--- Round " + rounds + " ---");

            if (heroFirst) {
                heroTurn(hero, boss, action, result);
                if (!boss.isAlive()) {
                    break;
                }
                bossTurn(hero, boss, result);
            } else {
                bossTurn(hero, boss, result);
                if (!hero.isAlive()) {
                    break;
                }
                heroTurn(hero, boss, action, result);
            }
        }

        result.setRounds(rounds);

        String winner;
        if (hero.isAlive() && boss.isAlive()) {
            winner = "Draw";
            result.addLine("Safety stop: max rounds reached (" + maxRoundsSafety + ")");
        } else if (hero.isAlive()) {
            winner = hero.getName();
        } else if (boss.isAlive()) {
            winner = boss.getName();
        } else {
            winner = "Draw";
        }

        result.setWinner(winner);
        result.addLine("");
        result.addLine("=== Battle End ===");
        result.addLine("Winner: " + winner);

        return result;
    }

    private static void heroTurn(HeroProfile hero, BossEnemy boss, AttackAction action, AdventureResult result) {
        int dmg = Math.max(0, action.getDamage());
        int bossHpBefore = boss.getHealth();

        result.addLine(hero.getName() + " uses '" + action.getActionName() + "' dealing " + dmg
                + " dmg | effects=" + action.getEffectSummary());

        boss.takeDamage(dmg);

        int bossHpAfter = boss.getHealth();
        result.addLine("  Boss HP: " + bossHpBefore + " -> " + bossHpAfter);
    }

    private void bossTurn(HeroProfile hero, BossEnemy boss, AdventureResult result) {
        int variance = random.nextInt(5) - 2;
        int dmg = Math.max(0, boss.getAttackPower() + variance);
        int heroHpBefore = hero.getHealth();

        result.addLine(boss.getName() + " attacks dealing " + dmg + " dmg (variance " + variance + ")");

        hero.takeDamage(dmg);

        int heroHpAfter = hero.getHealth();
        result.addLine("  Hero HP: " + heroHpBefore + " -> " + heroHpAfter);
    }
}

package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        AttackAction base = new BasicAttack("Strike", 10);

        AttackAction fireOnly = new FireRuneDecorator(base);
        AttackAction poisonOnly = new PoisonCoatingDecorator(base);

        AttackAction orderA = new CriticalFocusDecorator(new FireRuneDecorator(base));
        AttackAction orderB = new FireRuneDecorator(new CriticalFocusDecorator(base));

        AttackAction multiLayer = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(base)
                )
        );

        System.out.println("--- Decorator Demo ---");
        printAction("Base", base);
        printAction("Fire only", fireOnly);
        printAction("Poison only", poisonOnly);

        System.out.println("\nDecorator order matters:");
        printAction("Focus(Fire(Base))", orderA);
        printAction("Fire(Focus(Base))", orderB);

        System.out.println("\nMulti-layer chain:");
        printAction("Fire(Poison(Focus(Base)))", multiLayer);


        System.out.println("\n--- Facade Demo: Full Dungeon Run ---");
        long seed = 42L;

        AdventureResult run1 = runAdventure(seed, multiLayer);
        AdventureResult run2 = runAdventure(seed, multiLayer);

        boolean deterministic = run1.getWinner().equals(run2.getWinner())
                && run1.getRounds() == run2.getRounds()
                && run1.getLog().equals(run2.getLog());

        System.out.println("Deterministic with seed " + seed + "? " + deterministic);

        printAdventureResult("Run #1", run1);

        long differentSeed = 7L;
        AdventureResult run3 = runAdventure(differentSeed, multiLayer);
        System.out.println("\nDifferent seed (" + differentSeed + ") changes log? " + !run1.getLog().equals(run3.getLog()));

        System.out.println("\n=== Demo Complete ===");
    }

    private static AdventureResult runAdventure(long seed, AttackAction action) {
        HeroProfile hero = new HeroProfile("Arthas", 120);
        BossEnemy boss = new BossEnemy("Lich King", 180, 18);

        DungeonFacade facade = new DungeonFacade().setRandomSeed(seed);
        return facade.runAdventure(hero, boss, action);
    }

    private static void printAction(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  name   = " + action.getActionName());
        System.out.println("  damage = " + action.getDamage());
        System.out.println("  effects= " + action.getEffectSummary());
    }

    private static void printAdventureResult(String title, AdventureResult result) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("--- Log ---");
        for (String line : result.getLog()) {
            System.out.println(line);
        }
    }
}

package com.narxoz.rpg.enemy;

import java.util.Objects;

public class BossEnemy {
    private final String name;
    private int health;
    private final int attackPower;

    public BossEnemy(String name, int health, int attackPower) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.health = Math.max(0, health);
        this.attackPower = Math.max(0, attackPower);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void takeDamage(int amount) {
        int dmg = Math.max(0, amount);
        health = Math.max(0, health - dmg);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

package com.narxoz.rpg.hero;

import java.util.Objects;

public class HeroProfile {
    private final String name;
    private int health;

    public HeroProfile(String name, int health) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.health = Math.max(0, health);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        int dmg = Math.max(0, amount);
        health = Math.max(0, health - dmg);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

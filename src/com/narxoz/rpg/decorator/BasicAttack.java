package com.narxoz.rpg.decorator;

import java.util.Objects;

public class BasicAttack implements AttackAction {
    private final String actionName;
    private final int baseDamage;

    public BasicAttack(String actionName, int baseDamage) {
        this.actionName = Objects.requireNonNull(actionName, "actionName must not be null");
        this.baseDamage = baseDamage;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public int getDamage() {
        return Math.max(0, baseDamage);
    }

    @Override
    public String getEffectSummary() {
        return "Physical";
    }
}

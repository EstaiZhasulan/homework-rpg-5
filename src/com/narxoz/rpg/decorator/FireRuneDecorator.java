package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {
    private static final int FIRE_BONUS = 4;

    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + FireRune";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + FIRE_BONUS;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Fire(+" + FIRE_BONUS + ")";
    }
}

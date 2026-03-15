package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    private static final int CRIT_MULTIPLIER = 2;

    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Focus";
    }

    @Override
    public int getDamage() {
        return super.getDamage() * CRIT_MULTIPLIER;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Critical(x" + CRIT_MULTIPLIER + ")";
    }
}

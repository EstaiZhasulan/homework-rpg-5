package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    private static final int POISON_BONUS = 3;

    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Poison";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + POISON_BONUS;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Poison(+" + POISON_BONUS + ")";
    }
}

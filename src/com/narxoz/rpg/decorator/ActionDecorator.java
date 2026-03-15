package com.narxoz.rpg.decorator;

import java.util.Objects;

public abstract class ActionDecorator implements AttackAction {
    private final AttackAction wrappedAction;

    protected ActionDecorator(AttackAction wrappedAction) {
        this.wrappedAction = Objects.requireNonNull(wrappedAction, "wrappedAction must not be null");
    }

    protected AttackAction getWrappedAction() {
        return wrappedAction;
    }

    @Override
    public String getActionName() {
        return wrappedAction.getActionName();
    }

    @Override
    public int getDamage() {
        return wrappedAction.getDamage();
    }

    @Override
    public String getEffectSummary() {
        return wrappedAction.getEffectSummary();
    }
}


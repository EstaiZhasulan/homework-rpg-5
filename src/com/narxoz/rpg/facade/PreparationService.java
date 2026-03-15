package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null) {
            return "Preparation FAILED: hero is null";
        }
        if (boss == null) {
            return "Preparation FAILED: boss is null";
        }
        if (action == null) {
            return "Preparation FAILED: action is null";
        }
        if (!hero.isAlive()) {
            return "Preparation FAILED: hero is not alive";
        }
        if (!boss.isAlive()) {
            return "Preparation FAILED: boss is not alive";
        }

        return "Preparation OK: hero=" + hero.getName() + "(HP=" + hero.getHealth() + ")"
                + ", boss=" + boss.getName() + "(HP=" + boss.getHealth() + ")"
                + ", action='" + action.getActionName() + "' dmg=" + action.getDamage()
                + ", effects=" + action.getEffectSummary();
    }
}

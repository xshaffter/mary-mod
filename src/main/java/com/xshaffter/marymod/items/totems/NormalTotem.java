package com.xshaffter.marymod.items.totems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Random;

public class NormalTotem {

    @SuppressWarnings("unused")
    public void performResurrection(Entity resurrected) {
        resurrected.world.sendEntityStatus(resurrected, EntityStatuses.USE_TOTEM_OF_UNDYING);
    }


    @SuppressWarnings("unused")
    public void postRevive(Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.setHealth(1.0f);
            living.clearStatusEffects();
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0));
        }
    }
}
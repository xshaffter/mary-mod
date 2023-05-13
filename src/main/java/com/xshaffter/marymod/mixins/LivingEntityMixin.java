package com.xshaffter.marymod.mixins;

import com.xshaffter.marymod.events.AdvancementManager;
import com.xshaffter.marymod.items.totems.NormalTotem;
import com.xshaffter.marymod.util.IEntityDataSaver;
import com.xshaffter.marymod.util.LivingEntityBridge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements IEntityDataSaver {
    private boolean isFirstDead(){
        return this.getPersistentData().getBoolean("firstDead");
    }
    private NbtCompound persistentData;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
    public void useCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> callback) {
        //initializes PlayerEntity entity, which is a copy of this cast to Living Entity and then PlayerEntity
        if (source.isOutOfWorld()) {
            callback.cancel();
        }
        //noinspection ConstantValue
        if (((Entity)this) instanceof ServerPlayerEntity player) {
            var totem_item = new NormalTotem();
            totem_item.performResurrection(this);
            totem_item.postRevive(this);
            callback.setReturnValue(true);
            if (isFirstDead()) {
                this.getPersistentData().putBoolean("firstDead", false);
                player.sendMessage(Text.literal("¿Qué? ¿enserio creíste que te dejaríamos morir?"));
                AdvancementManager.grantAdvancement(player, "undying");
            }
        }

    }

    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    protected void writeNbt(NbtCompound nbt, CallbackInfo info) {
        if (persistentData != null) {
            nbt.put("marymod.data", persistentData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    protected void injectedReadNBT(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("marymod.data", NbtElement.COMPOUND_TYPE)) {
            persistentData = nbt.getCompound("marymod.data");
        }
    }

}
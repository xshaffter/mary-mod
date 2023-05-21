package com.xshaffter.marymod.mixins;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.items.totems.NormalTotem;
import com.xshaffter.marymod.util.IPlayerFunctions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IPlayerFunctions {

    @Shadow
    public abstract Text getName();

    @Shadow
    public abstract Text getDisplayName();

    @Shadow public abstract boolean canHarvest(BlockState state);

    private MutableText getNameAsMutable() {
        return (MutableText) this.getName();
    }

    private LiteralTextContent getNameText() {
        return (LiteralTextContent) getNameAsMutable().getContent();
    }

    public String getNameString() {
        return getNameText().string();
    }

    public String getTeamName() {
        var team = this.getScoreboardTeam();
        if (team != null) {
            return this.getScoreboardTeam().getName();
        } else {
            return "";
        }
    }

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "isBlockBreakingRestricted", cancellable = true)
    public void isBlockBreakingRestrictedForMary(World world, BlockPos pos, GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if (this.canHarvest(world.getBlockState(pos))) {
            cir.cancel();
        }

        if (getNameString().equalsIgnoreCase("maryblog") || this.getTeamName().equalsIgnoreCase("people")) {
            cir.setReturnValue(true);
        } else {
            cir.cancel();
        }
    }
}
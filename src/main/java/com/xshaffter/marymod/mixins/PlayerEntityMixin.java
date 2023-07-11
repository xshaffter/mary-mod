package com.xshaffter.marymod.mixins;

import com.mojang.datafixers.types.Func;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.events.AdvancementManager;
import com.xshaffter.marymod.items.totems.NormalTotem;
import com.xshaffter.marymod.util.Boundaries;
import com.xshaffter.marymod.util.Functions;
import com.xshaffter.marymod.util.IPlayerFunctions;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IPlayerFunctions {

    @Shadow
    public abstract Text getName();

    @Shadow
    public abstract Text getDisplayName();

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
    @Inject(at = @At("HEAD"), method = "travel", cancellable = true)
    public void onTravel(Vec3d movementInput, CallbackInfo ci) {
        //noinspection ConstantValue
        if (((LivingEntity)this) instanceof ServerPlayerEntity player) {
            var DANGER_BOUNDS = new Boundaries(new Vec3d(-188, -30, 325), new Vec3d(-182, -28, 327));
            var GALLERY_BOUNDS = new Boundaries(new Vec3d(-189, -30, 347), new Vec3d(-181, -27, 348));
            var COUSINE_BOUNDS = new Boundaries(new Vec3d(-204, -30, 355), new Vec3d(-202, -26, 358));
            var CEMENTERY_BOUNDS = new Boundaries(new Vec3d(-186, -58, 267), new Vec3d(-185, -56, 268));
            var SANCTUARY_BOUNDS = new Boundaries(new Vec3d(-186, -58, 205), new Vec3d(-184, -55, 206));
            var DISCO_BOUNDS = new Boundaries(new Vec3d(-187, -58, 178), new Vec3d(-180, -52, 181));
            var TRAVEL_BOUNDS = new Boundaries(new Vec3d(-199, -58, 162), new Vec3d(-197, -55, 166));
            var PARTY_BOUNDS = new Boundaries(new Vec3d(-224, -57, 202), new Vec3d(-221, -54, 208));
            if (Functions.playerInBounds(player, DANGER_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "danger");
            } else if (Functions.playerInBounds(player, GALLERY_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "gallery");
            } else if (Functions.playerInBounds(player, COUSINE_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "cook");
            } else if (Functions.playerInBounds(player, CEMENTERY_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "piece_of_way");
            } else if (Functions.playerInBounds(player, SANCTUARY_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "inner_peace");
            } else if (Functions.playerInBounds(player, DISCO_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "big_party");
            } else if (Functions.playerInBounds(player, TRAVEL_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "polaroid");
            } else if (Functions.playerInBounds(player, PARTY_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "end");
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "handleFallDamage", cancellable = true)
    public void onFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {

        //noinspection ConstantValue
        if (((LivingEntity)this) instanceof ServerPlayerEntity player) {
            var CAVE_BOUNDS = new Boundaries(new Vec3d(-107, -28, 244), new Vec3d(-99, -21, 254));
            var JUDGE_BOUNDS = new Boundaries(new Vec3d(-176, -57, 294), new Vec3d(-168, -52, 302));
            var DARK_ROOM_BOUNDS = new Boundaries(new Vec3d(-219, -39, 342), new Vec3d(-215, -35, 347));
            if (Functions.playerInBounds(player, CAVE_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "death");
            } else if (Functions.playerInBounds(player, JUDGE_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "gastronomia");
            } else if (Functions.playerInBounds(player, DARK_ROOM_BOUNDS)) {
                AdvancementManager.grantAdvancement(player, "mary_corazon");
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void canDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (PlayerEntityBridge.getTeam(this).equalsIgnoreCase(MaryModClient.DANGER_TEAM)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isBlockBreakingRestricted", cancellable = true)
    public void isBlockBreakingRestrictedForMary(World world, BlockPos pos, GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).isOf(Blocks.COARSE_DIRT)) {
            cir.setReturnValue(false);
        } else {
            if (getNameString().equalsIgnoreCase("maryblog") || this.getTeamName().equalsIgnoreCase(MaryModClient.PLAYER_TEAM)) {
                cir.setReturnValue(true);
            } else {
                cir.cancel();
            }
        }
    }
}
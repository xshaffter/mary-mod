package com.xshaffter.marymod.mixins;


import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.events.AdvancementManager;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Timer;
import java.util.TimerTask;

@Mixin(BoatEntity.class)
abstract class BoatEntityMixin extends Entity {

    @Accessor("velocityDecay")
    abstract float getVelocityDecay();

    @Accessor("location")
    abstract BoatEntity.Location getLocation();

    @Accessor("lastLocation")
    abstract BoatEntity.Location getLastLocation();

    @Accessor("waterLevel")
    abstract double getWaterLevel();

    @Accessor("yawVelocity")
    abstract void setYawVelocity(float value);

    @Accessor("velocityDecay")
    abstract void setVelocityDecay(float value);

    @Accessor("yawVelocity")
    abstract float getYawVelocity();

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "updateVelocity", cancellable = true)
    private void updateVelocity(CallbackInfo ci) {
        double d = -0.03999999910593033;
        double e = this.hasNoGravity() ? 0.0 : -0.03999999910593033;
        double f = 0.0;
        this.setVelocityDecay(0.05f);
        if (this.getLastLocation() != BoatEntity.Location.IN_AIR || this.getLocation() == BoatEntity.Location.IN_AIR || this.getLocation() == BoatEntity.Location.ON_LAND) {
            if (this.getLocation() == BoatEntity.Location.IN_WATER) {
                f = (this.getWaterLevel() - this.getY()) / (double) this.getHeight();
                this.setVelocityDecay(0.4f);
                ci.cancel();
            }

            Vec3d vec3d = this.getVelocity();
            this.setVelocity(vec3d.x * (double) this.getVelocityDecay(), vec3d.y + e, vec3d.z * (double) this.getVelocityDecay());
            this.setYawVelocity(getYawVelocity() * this.getVelocityDecay());
            if (f > 0.0) {
                Vec3d vec3d2 = this.getVelocity();
                this.setVelocity(vec3d2.x, (vec3d2.y + f * 0.06153846016296973) * 0.75, vec3d2.z);
            }
        }
    }
}

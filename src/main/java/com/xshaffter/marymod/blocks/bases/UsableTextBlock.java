package com.xshaffter.marymod.blocks.bases;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

public abstract class UsableTextBlock extends RotableBlock{
    private final Text text;
    private boolean canUse = true;

    protected UsableTextBlock(Settings settings, Text text) {
        super(settings);
        this.text = text;
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (canUse) {
            player.sendMessage(text);

            canUse = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    canUse = true;
                }
            }, 150);
        }
        return ActionResult.SUCCESS;
    }
}

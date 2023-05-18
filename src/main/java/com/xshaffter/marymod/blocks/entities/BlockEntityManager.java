package com.xshaffter.marymod.blocks.entities;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.blocks.BlockManager;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class BlockEntityManager {
    public static BlockEntityType<CandyMachineEntity> CANDY_MACHINE_ENTITY;

    public static void registerEntities() {
        CANDY_MACHINE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MaryMod.MOD_ID, "candy_machine_entity"),
                FabricBlockEntityTypeBuilder.create(CandyMachineEntity::new, BlockManager.CANDY_MACHINE).build(null));
    }
}

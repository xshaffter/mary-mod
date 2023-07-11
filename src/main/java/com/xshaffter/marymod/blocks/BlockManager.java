package com.xshaffter.marymod.blocks;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.blocks.bases.OnActionBlock;
import com.xshaffter.marymod.blocks.bases.UsableTextBlock;
import com.xshaffter.marymod.blocks.custom.*;
import com.xshaffter.marymod.events.AdvancementManager;
import com.xshaffter.marymod.items.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShapes;

public class BlockManager {
    public static final Block MARY_COIN = new MaryCoin();
    public static final Block MARY_BLUE = new MaryBlue();
    public static final Block PORO_GALLETA = new PoroGalleta();
    public static final Block CANDY_MACHINE = new CandyMachine();

    public static final Block CEIL_FLOOR_BLOCK = new Block(
            FabricBlockSettings.of(Material.STONE)
                    .sounds(BlockSoundGroup.STONE)
                    .strength(4f)
                    .hardness(1f)
    );
    public static final Block AMAZON_BOX = new UsableTextBlock(
            Material.WOOD,
            BlockSoundGroup.WOOL,
            Text.literal("Una caja!!! sera otro ajolote?")
    );
    public static final Block TERRY = new UsableTextBlock(
            Material.WOOL,
            BlockSoundGroup.WOOL,
            Text.literal("Mi mejor amigo, a veces siento que me habla")
    );
    public static final Block COMPUTER = new UsableTextBlock(
            Material.METAL,
            BlockSoundGroup.GLASS,
            Text.literal("La mamalona"),
            VoxelShapes.cuboid(0f, 0f, 0f, 0.6f, 0.6f, 1f)
    );
    public static final Block DESKTOP = new UsableTextBlock(
            FabricBlockSettings.of(Material.WOOD)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOD)
                    .strength(4f)
                    .hardness(1f),
            Text.literal("Creo que no quiero streamer de momento"),
            VoxelShapes.cuboid(-0.7f, 0f, 0f, 2f, 1f, 1f)
    );
    public static final Block MONITOR = new UsableTextBlock(
            FabricBlockSettings.of(Material.METAL)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.GLASS)
                    .strength(4f)
                    .hardness(1f),
            Text.literal("Mi pequeño setup"),
            VoxelShapes.cuboid(-1f, 0f, 0f, 1f, 1f, 1f)
    );

    public static final Block PAINT_SHELF = new UsableTextBlock(
            Text.literal("Mis pinturas para los ajolotes"),
            FabricBlockSettings.of(Material.WOOL)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f)
    );

    public static final Block TOMB = new TombBlock();

    public static final Block EASEL = new UsableTextBlock(
            Text.literal("Aqui deberian estar mis pinturas... donde estaran?"),
            FabricBlockSettings.of(Material.STONE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.STONE)
                    .strength(4f)
                    .hardness(1f)
    );

    public static final Block CAKE = new UsableTextBlock(
            Text.literal("Feliz cumpleaños!"),
            FabricBlockSettings.of(Material.CAKE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f)
    );
    public static final Block DARK_PANCITO = new UsableTextBlock(
            Text.literal("Pancito oscuro"),
            FabricBlockSettings.of(Material.CAKE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f)
    );
    public static final Block MISS_PANCITO = new UsableTextBlock(
            Text.literal("La señora pancito"),
            FabricBlockSettings.of(Material.CAKE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f)
    );
    public static final Block SIR_PANCITO = new UsableTextBlock(
            Text.literal("El señor pancito"),
            FabricBlockSettings.of(Material.CAKE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f)
    );

    public static final Block CUBY_FRIEND_ANSI = new CubyFriend();
    public static final Block CUBY_FRIEND_AMILCAR = new CubyFriend();
    public static final Block CUBY_FRIEND_28 = new CubyFriend();
    public static final Block CUBY_FRIEND_ANTONI = new CubyFriend();
    public static final Block CUBY_FRIEND_EMET = new CubyFriend();
    public static final Block CUBY_FRIEND_CRISGREEN = new CubyFriend();
    public static final Block CUBY_FRIEND_EMIKUKIS = new CubyFriend();
    public static final Block CUBY_FRIEND_JULI = new CubyFriend();
    public static final Block CUBY_FRIEND_SHADOUNE = new CubyFriend();
    public static final Block MURAL_AJOLOTE = new MuralBlock();
    public static final Block MURAL_AVION = new MuralBlock();
    public static final Block MURAL_BATA = new MuralBlock();
    public static final Block MURAL_CORAZON = new MuralBlock();
    public static final Block MURAL_CUEVA = new MuralBlock();
    public static final Block MURAL_ESPADA = new MuralBlock();
    public static final Block MURAL_HORNO = new MuralBlock();
    public static final Block MURAL_INFERIOR_DERECHA = new MuralBlock();
    public static final Block MURAL_INFERIOR_IZQUIERDA = new MuralBlock();
    public static final Block MURAL_MAZO = new MuralBlock();
    public static final Block MURAL_MICROFONO = new MuralBlock();
    public static final Block MURAL_PASTEL = new MuralBlock();
    public static final Block MURAL_PILLARMARY = new MuralBlock();
    public static final Block MURAL_RELLENO1 = new MuralBlock();
    public static final Block MURAL_RELLENO2 = new MuralBlock();
    public static final Block MURAL_RELLENO3 = new MuralBlock();
    public static final Block MURAL_RELLENO4 = new MuralBlock();
    public static final Block MURAL_RELLENO5 = new MuralBlock();
    public static final Block MURAL_RELLENO6 = new MuralBlock();
    public static final Block MURAL_RELLENO7 = new MuralBlock();
    public static final Block MURAL_RELLENO8 = new MuralBlock();
    public static final Block MURAL_RELLENO9 = new MuralBlock();
    public static final Block MURAL_SUPERIOR_DERECHA = new MuralBlock();
    public static final Block MURAL_SUPERIOR_IZQUIERDA = new MuralBlock();
    public static final Block MURAL_TEMPLO = new MuralBlock();
    public static final Block MURAL_TUMBA = new MuralBlock();
    public static final Block CABIN = new PoliceCabin();

    public static final Block BANDERA = new UsableTextBlock(
            FabricBlockSettings.of(Material.CAKE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f),
            Text.literal("Una bandera del reino"),
            VoxelShapes.cuboid(0,0,0,1,2,1)
    );

    public static final Block ALARM = new OnActionBlock(
            FabricBlockSettings.of(Material.METAL)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.STONE)
                    .strength(4f)
                    .hardness(1f),
            VoxelShapes.cuboid(0.3f,0f,0.35f,0.7f,0.2f,0.55f),
            (state, world, pos, player, hand, hit) -> {
                if (!world.isClient) {
                    AdvancementManager.grantAdvancement((ServerPlayerEntity) player, "morning");
                }
                return ActionResult.SUCCESS;
            }
    );

    private static void registerBlock(final String name, final Block block, final BlockItem blockItem) {
        Registry.register(Registry.BLOCK, new Identifier(MaryMod.MOD_ID, name), block);
        registerBlockItem(name, blockItem);
    }

    private static void registerBlockItem(final String name, final BlockItem blockItem) {
        Registry.register(Registry.ITEM, new Identifier(MaryMod.MOD_ID, name), blockItem);
    }

    private static void registerBlockAuto(final String name, final Block block) {
        registerBlock(name, block, new BlockItem(block, new FabricItemSettings().fireproof().maxCount(64).group(ItemGroups.MARY_MOD_GROUP)));
    }

    public static void registerModBlocks() {

        registerBlockAuto("mary_blue", MARY_BLUE);
        registerBlockAuto("mary_coin", MARY_COIN);
        registerBlockAuto("poro_galletas", PORO_GALLETA);
        registerBlockAuto("candy_machine", CANDY_MACHINE);
        registerBlockAuto("amazon_box", AMAZON_BOX);
        registerBlockAuto("terry", TERRY);
        registerBlockAuto("computer", COMPUTER);
        registerBlockAuto("desktop", DESKTOP);
        registerBlockAuto("monitor", MONITOR);
        registerBlockAuto("paint_shelf", PAINT_SHELF);
        registerBlockAuto("easel", EASEL);
        registerBlockAuto("tomb", TOMB);
        registerBlockAuto("alarm", ALARM);
        registerBlockAuto("cake", CAKE);
        registerBlockAuto("cabin", CABIN);
        registerBlockAuto("cuby_friend_ansi", CUBY_FRIEND_ANSI);
        registerBlockAuto("cuby_friend_amilcar", CUBY_FRIEND_AMILCAR);
        registerBlockAuto("cuby_friend_28", CUBY_FRIEND_28);
        registerBlockAuto("cuby_friend_antoni", CUBY_FRIEND_ANTONI);
        registerBlockAuto("cuby_friend_emet", CUBY_FRIEND_EMET);

        registerBlockAuto("cuby_friend_crisgreen", CUBY_FRIEND_CRISGREEN);
        registerBlockAuto("cuby_friend_emikukis", CUBY_FRIEND_EMIKUKIS);
        registerBlockAuto("cuby_friend_juli", CUBY_FRIEND_JULI);
        registerBlockAuto("cuby_friend_shadoune", CUBY_FRIEND_SHADOUNE);

        registerBlockAuto("ceil_floor", CEIL_FLOOR_BLOCK);
        registerBlockAuto("dark_pancito", DARK_PANCITO);
        registerBlockAuto("miss_pancito", MISS_PANCITO);
        registerBlockAuto("sir_pancito", SIR_PANCITO);
        registerBlockAuto("mural_ajolote", MURAL_AJOLOTE);
        registerBlockAuto("mural_avion", MURAL_AVION);
        registerBlockAuto("mural_bata", MURAL_BATA);
        registerBlockAuto("mural_corazon", MURAL_CORAZON);
        registerBlockAuto("mural_cueva", MURAL_CUEVA);
        registerBlockAuto("mural_espada", MURAL_ESPADA);
        registerBlockAuto("mural_horno", MURAL_HORNO);
        registerBlockAuto("mural_inferior_derecha", MURAL_INFERIOR_DERECHA);
        registerBlockAuto("mural_inferior_izquierda", MURAL_INFERIOR_IZQUIERDA);
        registerBlockAuto("mural_mazo", MURAL_MAZO);
        registerBlockAuto("mural_microfono", MURAL_MICROFONO);
        registerBlockAuto("mural_pastel", MURAL_PASTEL);
        registerBlockAuto("mural_pillarmary", MURAL_PILLARMARY);
        registerBlockAuto("mural_relleno1", MURAL_RELLENO1);
        registerBlockAuto("mural_relleno2", MURAL_RELLENO2);
        registerBlockAuto("mural_relleno3", MURAL_RELLENO3);
        registerBlockAuto("mural_relleno4", MURAL_RELLENO4);
        registerBlockAuto("mural_relleno5", MURAL_RELLENO5);
        registerBlockAuto("mural_relleno6", MURAL_RELLENO6);
        registerBlockAuto("mural_relleno7", MURAL_RELLENO7);
        registerBlockAuto("mural_relleno8", MURAL_RELLENO8);
        registerBlockAuto("mural_relleno9", MURAL_RELLENO9);
        registerBlockAuto("mural_superior_derecha", MURAL_SUPERIOR_DERECHA);
        registerBlockAuto("mural_superior_izquierda", MURAL_SUPERIOR_IZQUIERDA);
        registerBlockAuto("mural_templo", MURAL_TEMPLO);
        registerBlockAuto("mural_tumba", MURAL_TUMBA);;
        registerBlockAuto("bandera", BANDERA);
    }
}
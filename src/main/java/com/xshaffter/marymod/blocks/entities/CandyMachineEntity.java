package com.xshaffter.marymod.blocks.entities;

import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.items.ItemManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Merchant;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.jetbrains.annotations.Nullable;

public class CandyMachineEntity extends BlockEntity implements Merchant, Nameable {
    PlayerEntity customer = null;
    TradeOfferList offers;

    public CandyMachineEntity(BlockPos pos, BlockState state) {
        super(BlockEntityManager.CANDY_MACHINE_ENTITY, pos, state);
        this.offers = new TradeOfferList();
        offers.add(new TradeOffer(new ItemStack(ItemManager.MARY_COIN_ITEM), new ItemStack(BlockManager.MARY_BLUE), 1, 0, 1));
    }

    @Override
    public Text getName() {
        return Text.literal("Maquina de dulces");
    }

    @Override
    public void setCustomer(@Nullable PlayerEntity customer) {
        if (customer instanceof ServerPlayerEntity) {
            this.customer = customer;
        }
    }

    @Nullable
    @Override
    public PlayerEntity getCustomer() {
        return customer;
    }

    @Override
    public TradeOfferList getOffers() {
        return offers;
    }

    @Override
    public void setOffersFromServer(TradeOfferList offers) {

    }

    @Override
    public void trade(TradeOffer offer) {

    }

    @Override
    public void onSellingItem(ItemStack stack) {
        offers.remove(stack);
    }

    @Override
    public int getExperience() {
        return 0;
    }

    @Override
    public void setExperienceFromServer(int experience) {
    }

    @Override
    public boolean isLeveledMerchant() {
        return false;
    }

    @Override
    public SoundEvent getYesSound() {
        return SoundEvents.ENTITY_PLAYER_LEVELUP;
    }

    @Override
    public boolean isClient() {
        assert this.world != null;
        return this.world.isClient;
    }

}

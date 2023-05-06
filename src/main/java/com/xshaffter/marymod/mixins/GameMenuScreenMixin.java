package com.xshaffter.marymod.mixins;

import net.minecraft.SharedConstants;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {


    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo info) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, Text.translatable("menu.returnToGame"), (button) -> {
            this.client.setScreen((Screen)null);
            this.client.mouse.lockCursor();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 48 + -16, 98, 20, Text.translatable("gui.advancements"), (button) -> {
            this.client.setScreen(new AdvancementsScreen(this.client.player.networkHandler.getAdvancementHandler()));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 48 + -16, 98, 20, Text.translatable("gui.stats"), (button) -> {
            this.client.setScreen(new StatsScreen(this, this.client.player.getStatHandler()));
        }));
        String string = SharedConstants.getGameVersion().isStable() ? "https://aka.ms/javafeedback?ref=game" : "https://aka.ms/snapshotfeedback?ref=game";
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 72 + -16, 98, 20, Text.translatable("menu.sendFeedback"), (button) -> {
            this.client.setScreen(new ConfirmLinkScreen((confirmed) -> {
                if (confirmed) {
                    Util.getOperatingSystem().open(string);
                }

                this.client.setScreen(this);
            }, string, true));
        }));
        ButtonWidget buttonWidget = (ButtonWidget)this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 72 + -16, 98, 20, Text.translatable("menu.reportBugs"), (button) -> {
            this.client.setScreen(new ConfirmLinkScreen((confirmed) -> {
                if (confirmed) {
                    Util.getOperatingSystem().open("https://aka.ms/snapshotbugs?ref=game");
                }

                this.client.setScreen(this);
            }, "https://aka.ms/snapshotbugs?ref=game", true));
        }));
        buttonWidget.active = !SharedConstants.getGameVersion().getSaveVersion().isNotMainSeries();
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.options"), (button) -> {
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }));
        if (this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote()) {
            this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.shareToLan"), (button) -> {
                this.client.setScreen(new OpenToLanScreen(this));
            }));
        } else {
            this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.playerReporting"), (buttonWidgetx) -> {
                this.client.setScreen(new SocialInteractionsScreen());
            }));
        }

        Text text = this.client.isInSingleplayer() ? Text.translatable("menu.returnToMenu") : Text.translatable("menu.disconnect");
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20, text, (button) -> {
            boolean bl = this.client.isInSingleplayer();
            button.active = false;
            this.client.world.disconnect();
            if (bl) {
                this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }

            TitleScreen titleScreen = new TitleScreen();
            this.client.setScreen(titleScreen);

        }));
    }

}
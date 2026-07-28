package com.pixlrainbow.sablesublevelsavenotification.mixin;

import dev.ryanhcode.sable.sublevel.storage.holding.SubLevelHoldingChunkMap;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SubLevelHoldingChunkMap.class)
public abstract class SublevelSaveMixin implements AutoCloseable {

    @Shadow
    @Final
    private ServerLevel level;

    @WrapMethod(method = "saveAll()V")
    public void logSave(Operation<Void> original) {
        PlayerList playerList = this.level.getServer().getPlayerList();
        String dimensionName = this.level.dimension().location().getPath();

        playerList.broadcastSystemMessage(
            Component.translatable("sub_level.chat.savingSubLevels", dimensionName),
            false
        );
        original.call();
        playerList.broadcastSystemMessage(
            Component.translatable("sub_level.chat.savingSubLevelsDone", dimensionName),
            false
        );
    }

}

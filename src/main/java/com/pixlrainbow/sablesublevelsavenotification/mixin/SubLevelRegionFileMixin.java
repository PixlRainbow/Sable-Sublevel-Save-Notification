package com.pixlrainbow.sablesublevelsavenotification.mixin;

import java.io.IOException;
import java.nio.file.Path;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.pixlrainbow.sablesublevelsavenotification.SableSublevelSaveNotification;

import dev.ryanhcode.sable.sublevel.storage.holding.SubLevelHoldingChunk;
import dev.ryanhcode.sable.sublevel.storage.region.SubLevelRegionFile;
import dev.ryanhcode.sable.sublevel.storage.region.SubLevelStorageFile;
import net.minecraft.nbt.CompoundTag;

@Mixin(SubLevelRegionFile.class)
public abstract class SubLevelRegionFileMixin extends SubLevelStorageFile {

    public SubLevelRegionFileMixin(Path path, Path externalFileDir) throws IOException {
        super(path, externalFileDir, SubLevelRegionFile.SECTOR_SIZE);
    }

    @ModifyArg(
        method = "trySave(IILdev/ryanhcode/sable/sublevel/storage/holding/SubLevelHoldingChunk;)V",
        at = @At(
            value = "INVOKE",
            target = "Ldev/ryanhcode/sable/sublevel/storage/region/SubLevelRegionFile;write(ILnet/minecraft/nbt/CompoundTag;)V"
        ),
        index = 1
    )
    @Nullable
    public CompoundTag skipSavingEmptyRegions(CompoundTag tag) {
        if (this.shouldCullRegion(tag)) {
            SableSublevelSaveNotification.LOGGER.info("Cleaning sublevel pointers for chunk with no sublevels.");
            return null;
        }
        return tag;
    } 

    @ModifyReturnValue(
        method = "read(Lnet/minecraft/world/level/ChunkPos;)Ldev/ryanhcode/sable/sublevel/storage/holding/SubLevelHoldingChunk;",
        at = @At("RETURN")
    )
    @Nullable
    public SubLevelHoldingChunk skipLoadingEmptyRegions(SubLevelHoldingChunk originalReturn, @Local CompoundTag tag) {
        if (originalReturn != null && tag != null && this.shouldCullRegion(tag)) {
            SableSublevelSaveNotification.LOGGER.info("Skipping sublevel pointers for chunk with no sublevels.");
            return null;
        }
        return originalReturn;
    }

    private boolean shouldCullRegion(CompoundTag tag) {
        return tag.contains("pointers", CompoundTag.TAG_INT_ARRAY) && tag.getIntArray("pointers").length < 1;
    }

}

package com.pixlrainbow.sablesublevelsavetweaks;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;


public class Config {

    private static final Builder BUILDER = new Builder();

    private static final String TRANSLATION_KEY_PREFIX = SableSublevelSaveTweaks.MODID + ".config";

    /**
     * Whether to broadcast to all players in chat when Sable is saving sub-levels.
     */
    public static final BooleanValue BROADCAST_CHAT_SABLE_SAVE = BUILDER
            .comment("Broadcast to all players in chat when Sable is saving sub-levels.")
            .translation(TRANSLATION_KEY_PREFIX + ".broadcastSableSave")
            .define("broadcastSableSave", true);

    /**
     * Whether to skip loading sub-level to chunk mappings for chunks with no sub-levels.
     */
    public static final BooleanValue SKIP_EMPTY_CHUNKS_WORLD_LOAD = BUILDER
            .comment("During world load, skip loading sub-level save data on chunks with no sub-levels.")
            .translation(TRANSLATION_KEY_PREFIX + ".skipEmptyChunksWorldLoad")
            .define("skipEmptyChunksWorldLoad", false);

    /**
     * Whether to clear from file the sub-level to chunk mappings for chunks with no sub-levels.
     */
    public static final BooleanValue CLEAR_EMPTY_CHUNKS_WORLD_SAVE = BUILDER
            .comment("During world save, clear sub-level save data on chunks with no sub-levels.")
            .translation(TRANSLATION_KEY_PREFIX + ".clearEmptyChunksWorldSave")
            .define("clearEmptyChunksWorldSave", false);

    static final ModConfigSpec SPEC = BUILDER.build();

}

package com.pixlrainbow.sablesublevelsavenotification;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;


public class Config {

    private static final Builder BUILDER = new Builder();

    /**
     * Whether to broadcast to all players in chat when Sable is saving sub-levels.
     */
    public static final BooleanValue BROADCAST_CHAT_SABLE_SAVE = BUILDER
            .comment("Broadcast to all players in chat when Sable is saving sub-levels")
            .define("broadcastSableSave", true);

    /**
     * Whether to skip loading sub-level to chunk mappings for chunks with no sub-levels.
     */
    public static final BooleanValue SKIP_EMPTY_CHUNKS_WORLD_LOAD = BUILDER
            .comment("Skip save loading sub-level chunk mappings for chunks with no sub-levels")
            .define("skipEmptyChunksWorldLoad", false);

    /**
     * Whether to clear from file the sub-level to chunk mappings for chunks with no sub-levels.
     */
    public static final BooleanValue CLEAR_EMPTY_CHUNKS_WORLD_SAVE = BUILDER
            .comment("Clear save data for sub-level chunk mappings for chunks with no sub-levels")
            .define("clearEmptyChunksWorldSave", false);

    static final ModConfigSpec SPEC = BUILDER.build();

}

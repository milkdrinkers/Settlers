package io.github.milkdrinkers.settlers.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@ConfigSerializable
public class PluginConfig {
    @Comment("Should the plugin check for plugin updates on startup?")
    public boolean updateChecker = true;

    @Comment("Do not change this value!")
    public int configVersion = 1;
}

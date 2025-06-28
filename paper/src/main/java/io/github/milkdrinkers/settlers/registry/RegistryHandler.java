package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A handler for managing {@link NPCRegistry} access {@literal &} life-cycle.
 */
public final class RegistryHandler implements IRegistryHandler {
    private RegistryHolder registry;

    @Override
    public void onLoad(ISettlersPlugin plugin) {
        registry = new RegistryHolder(plugin);
        registry.onLoad(plugin);
    }

    @Override
    public void onEnable(ISettlersPlugin plugin) {
        registry.onEnable(plugin);
        plugin.getServer().getPluginManager().registerEvents(registry, plugin);
    }

    @Override
    public void onDisable(ISettlersPlugin plugin) {
        registry.onDisable(plugin);
    }

    @Override
    public @NotNull RegistryHolder getRegistry() {
        Objects.requireNonNull(registry, "registry holder instance is null");
        return registry;
    }
}

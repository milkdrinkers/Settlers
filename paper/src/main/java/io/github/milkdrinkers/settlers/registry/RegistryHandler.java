package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import net.citizensnpcs.api.npc.NPCRegistry;

/**
 * A handler for managing {@link NPCRegistry} access {@literal &} life-cycle.
 */
public class RegistryHandler implements IRegistryHandler {
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
    public RegistryHolder getRegistry() {
        return registry;
    }
}

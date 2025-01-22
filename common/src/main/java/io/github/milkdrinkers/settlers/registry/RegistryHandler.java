package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.Reloadable;
import io.github.milkdrinkers.settlers.SettlersPlugin;
import net.citizensnpcs.api.npc.NPCRegistry;

/**
 * A handler for managing {@link NPCRegistry} access & life-cycle.
 */
public class RegistryHandler implements Reloadable {
    private SettlersRegistries registry;

    @Override
    public void onLoad(SettlersPlugin plugin) {
    }

    @Override
    public void onEnable(SettlersPlugin plugin) {
        registry = new SettlersRegistries(plugin);
    }

    @Override
    public void onDisable(SettlersPlugin plugin) {
        getRegistry().onDisable(plugin);
    }

    public SettlersRegistries getRegistry() {
        return registry;
    }
}

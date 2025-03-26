package io.github.milkdrinkers.settlers.utility;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPILoadedEvent;
import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPIUnloadedEvent;
import io.github.milkdrinkers.settlers.lookup.LookupHolder;
import io.github.milkdrinkers.settlers.registry.RegistryHolder;

public final class APILifecycle {
    /**
     * Call API load events and iterate npc's to add them to lookup tables.
     */
    public static void startup(ISettlersPlugin plugin) {
        final RegistryHolder registries = (RegistryHolder) plugin.getRegistryHandler().getRegistry();
        final LookupHolder lookupHolder = (LookupHolder) plugin.getLookupHandler().getHolder();
        registries.onInit(plugin);
        lookupHolder.onInit(plugin);

        // Notify plugins that API loaded
        plugin.setAPIState(true);
        new SettlersAPILoadedEvent().callEvent();
    }

    /**
     * Call unload events on server shutdown and plugin reload.
     *
     * @implNote These events are never processed if fired during onDisable (Default behavior in bukkit)
     */
    public static void shutdown(ISettlersPlugin plugin) {
        final RegistryHolder registries = (RegistryHolder) plugin.getRegistryHandler().getRegistry();
        final LookupHolder lookupHolder = (LookupHolder) plugin.getLookupHandler().getHolder();

        new SettlersAPIUnloadedEvent().callEvent();

        registries.onUnload(plugin);
        lookupHolder.onUnload(plugin);

        plugin.setAPIState(false);
    }
}

package io.github.milkdrinkers.settlers.listener.citizensapi;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.trait.persistence.PersistenceHandler;
import io.github.milkdrinkers.settlers.utility.APILifecycle;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.event.CitizensPreReloadEvent;
import net.citizensnpcs.api.event.CitizensReloadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

/**
 * Handles Settlers reacting to Citizens API lifecycle events.
 */
public class CitizensLifecycleListener implements Listener {
    private final ISettlersPlugin plugin;

    public CitizensLifecycleListener(ISettlersPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    @SuppressWarnings("unused")
    void onLoad(CitizensEnableEvent e) {
        new PersistenceHandler();
        APILifecycle.startup(plugin);
    }

    /**
     * Handle the event of Citizens being disabled.
     *
     * @param e event
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    @SuppressWarnings("unused")
    void onUnload(PluginDisableEvent e) {
        if (!e.getPlugin().getName().equalsIgnoreCase("Citizens"))
            return;

        APILifecycle.shutdown(plugin);
    }

    /**
     * Citizens unloads everything without saving after this event is fired.
     * We react by rebooting our API.
     *
     * @param e event
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    @SuppressWarnings("unused")
    void onPreReload(CitizensPreReloadEvent e) {
        APILifecycle.shutdown(plugin);
    }

    /**
     * Citizens loads everything from file storage before this event is fired (And spawns NPC's).
     * We react starting our API again.
     *
     * @param e event
     */
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    @SuppressWarnings("unused")
    void onReload(CitizensReloadEvent e) {
        APILifecycle.startup(plugin);
    }
}

package io.github.milkdrinkers.settlers.api;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.event.CitizensPreReloadEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Implemented on objects reacting to the lifecycle of citizens.
 */
public interface ILifecycle {
    /**
     * Called when the plugin and its dependencies are initialized. This is triggered by {@link CitizensEnableEvent}.
     *
     * @param plugin plugin
     * @see CitizensEnableEvent
     */
    void onInit(ISettlersPlugin plugin);

    /**
     * Called when the plugin is disabled or Citizens reloaded. This can be triggered by {@link PluginDisableEvent} or {@link CitizensPreReloadEvent} or {@link JavaPlugin#onDisable()}.
     *
     * @param plugin plugin
     * @see PluginDisableEvent
     * @see CitizensPreReloadEvent
     * @see JavaPlugin#onDisable()
     */
    void onUnload(ISettlersPlugin plugin);
}

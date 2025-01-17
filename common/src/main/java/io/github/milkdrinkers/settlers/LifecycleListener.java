package io.github.milkdrinkers.settlers;

import io.github.milkdrinkers.settlers.event.settlersapi.lifecycle.SettlersAPILoadedEvent;
import io.github.milkdrinkers.settlers.event.settlersapi.lifecycle.SettlersAPIPreReloadEvent;
import io.github.milkdrinkers.settlers.event.settlersapi.lifecycle.SettlersAPIReloadEvent;
import io.github.milkdrinkers.settlers.event.settlersapi.lifecycle.SettlersAPIUnloadedEvent;
import net.citizensnpcs.api.event.CitizensDisableEvent;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.event.CitizensPreReloadEvent;
import net.citizensnpcs.api.event.CitizensReloadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LifecycleListener implements Listener {
    private final SettlersPlugin plugin;

    public LifecycleListener(SettlersPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onEnable(CitizensEnableEvent e) {
        SettlersAPI.onEnable(plugin.getDataFolder());
        new SettlersAPILoadedEvent().callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPreReload(CitizensPreReloadEvent e) {
        new SettlersAPIPreReloadEvent().callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onReload(CitizensReloadEvent e) {
        new SettlersAPIReloadEvent().callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onDisable(CitizensDisableEvent e) {
        new SettlersAPIUnloadedEvent().callEvent();
        SettlersAPI.onDisable();
    }
}

package io.github.milkdrinkers.settlers.listener.citizensapi;

import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPILoadedEvent;
import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPIPreReloadEvent;
import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPIReloadEvent;
import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPIUnloadedEvent;
import io.github.milkdrinkers.settlers.api.settler.SettlerLookup;
import net.citizensnpcs.api.event.CitizensDisableEvent;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.event.CitizensPreReloadEvent;
import net.citizensnpcs.api.event.CitizensReloadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizensLifecycleListener implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    void onLoad(CitizensEnableEvent e) {
        SettlerLookup.loadRegistriesIntoLookup();
        final SettlersAPILoadedEvent event = new SettlersAPILoadedEvent();
        event.callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    void onUnload(CitizensDisableEvent e) {
        final SettlersAPIUnloadedEvent event = new SettlersAPIUnloadedEvent();
        event.callEvent();
        SettlerLookup.clear();
    }

    @EventHandler
    @SuppressWarnings("unused")
    void onPreReload(CitizensPreReloadEvent e) {
        final SettlersAPIPreReloadEvent event = new SettlersAPIPreReloadEvent();
        event.callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    void onReload(CitizensReloadEvent e) {
        final SettlersAPIReloadEvent event = new SettlersAPIReloadEvent();
        event.callEvent();
    }
}

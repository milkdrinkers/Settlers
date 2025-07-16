package io.github.milkdrinkers.settlers.listener.settlersapi;

import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPILoadedEvent;
import io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle.SettlersAPIUnloadedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SettlersListener implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onLoad(SettlersAPILoadedEvent e) {
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onLoad(SettlersAPIUnloadedEvent e) {
    }
}

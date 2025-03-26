package io.github.milkdrinkers.settlers.listener;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.Reloadable;
import io.github.milkdrinkers.settlers.listener.citizensapi.*;
import io.github.milkdrinkers.settlers.listener.settlersapi.SettlersListener;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerHandler implements Reloadable {
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public void onLoad(ISettlersPlugin plugin) {
        listeners.addAll(
            List.of(
                new CitizensLifecycleListener(plugin),
                new CitizenLookupTableListener(plugin),
                new CitizensListener(plugin),
                new CitizensNavigationListener(plugin),
                new CitizensSpeechListener(plugin),
                new SettlersListener()
            )
        );
    }

    @Override
    public void onEnable(ISettlersPlugin plugin) {
        for (Listener listener : listeners)
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    @Override
    public void onDisable(ISettlersPlugin plugin) {
        listeners.clear();
    }
}

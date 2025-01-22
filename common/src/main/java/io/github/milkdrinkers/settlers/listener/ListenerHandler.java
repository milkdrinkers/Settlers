package io.github.milkdrinkers.settlers.listener;

import io.github.milkdrinkers.settlers.Reloadable;
import io.github.milkdrinkers.settlers.SettlersPlugin;
import io.github.milkdrinkers.settlers.listener.citizensapi.CitizensLifecycleListener;
import io.github.milkdrinkers.settlers.listener.npc.NPCClickedListener;

public class ListenerHandler implements Reloadable {
    @Override
    public void onLoad(SettlersPlugin plugin) {

    }

    @Override
    public void onEnable(SettlersPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new CitizensLifecycleListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new NPCClickedListener(), plugin);
    }

    @Override
    public void onDisable(SettlersPlugin plugin) {

    }
}

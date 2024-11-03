package io.github.milkdrinkers.settlers.listener;

import io.github.milkdrinkers.settlers.Reloadable;
import io.github.milkdrinkers.settlers.Settlers;

public class ListenerHandler implements Reloadable {

    private final Settlers instance;

    public ListenerHandler(Settlers instance) {
        this.instance = instance;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        instance.getServer().getPluginManager().registerEvents(new NPCClickedListener(), instance);
    }

    @Override
    public void onDisable() {

    }
}

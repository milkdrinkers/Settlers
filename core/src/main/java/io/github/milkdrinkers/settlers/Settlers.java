package io.github.milkdrinkers.settlers;

import io.github.milkdrinkers.settlers.listener.ListenerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class Settlers extends JavaPlugin {

    private static Settlers instance;
    private ListenerHandler listenerHandler;

    public static Settlers getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        listenerHandler = new ListenerHandler(instance);

        listenerHandler.onLoad();
    }

    @Override
    public void onEnable() {
        listenerHandler.onEnable();
    }

    @Override
    public void onDisable() {
        listenerHandler.onDisable();
    }

}

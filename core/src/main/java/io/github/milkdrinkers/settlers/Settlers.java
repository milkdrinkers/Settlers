package io.github.milkdrinkers.settlers;

import org.bukkit.plugin.java.JavaPlugin;

public class Settlers extends JavaPlugin {

    private static Settlers instance;


    public static Settlers getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}

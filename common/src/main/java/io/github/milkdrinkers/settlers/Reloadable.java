package io.github.milkdrinkers.settlers;

public interface Reloadable {
    /**
     * On plugin load.
     */
    void onLoad(SettlersPlugin plugin);

    /**
     * On plugin enable.
     */
    void onEnable(SettlersPlugin plugin);

    /**
     * On plugin disable.
     */
    void onDisable(SettlersPlugin plugin);
}

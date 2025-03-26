package io.github.milkdrinkers.settlers;

public interface Reloadable {
    /**
     * On plugin load.
     */
    void onLoad(ISettlersPlugin plugin);

    /**
     * On plugin enable.
     */
    void onEnable(ISettlersPlugin plugin);

    /**
     * On plugin disable.
     */
    void onDisable(ISettlersPlugin plugin);
}

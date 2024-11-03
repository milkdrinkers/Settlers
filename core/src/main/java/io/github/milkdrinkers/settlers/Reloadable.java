package io.github.milkdrinkers.settlers;

public interface Reloadable {
    /**
     * On plugin load.
     */
    void onLoad();

    /**
     * On plugin enable.
     */
    void onEnable();

    /**
     * On plugin disable.
     */
    void onDisable();
}

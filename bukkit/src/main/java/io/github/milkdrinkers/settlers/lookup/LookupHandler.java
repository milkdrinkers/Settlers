package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class LookupHandler implements ILookupHandler {
    private ILookupHolder holder;

    @Override
    public void onLoad(ISettlersPlugin plugin) {
        holder = new LookupHolder(plugin);
    }

    @Override
    public void onEnable(ISettlersPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(holder, plugin);
    }

    @Override
    public void onDisable(ISettlersPlugin plugin) {
        holder = null;
    }

    @Override
    public @NotNull ILookupHolder getHolder() {
        Objects.requireNonNull(holder, "lookup holder instance is null");
        return holder;
    }
}

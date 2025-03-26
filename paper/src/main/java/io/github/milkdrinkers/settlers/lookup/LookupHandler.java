package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;

public class LookupHandler implements ILookupHandler {
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
    public ILookupHolder getHolder() {
        return holder;
    }
}

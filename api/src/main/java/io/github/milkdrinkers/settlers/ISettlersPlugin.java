package io.github.milkdrinkers.settlers;

import io.github.milkdrinkers.settlers.lookup.ILookupHandler;
import io.github.milkdrinkers.settlers.registry.IRegistryHandler;
import io.github.milkdrinkers.settlers.trait.ITraitRegistryHandler;
import org.bukkit.plugin.Plugin;

public interface ISettlersPlugin extends Plugin {
    String DIR_DATA = "data";

    ITraitRegistryHandler getTraitHandler();

    IRegistryHandler getRegistryHandler();

    ILookupHandler getLookupHandler();

    void setAPIState(boolean enabled);
}

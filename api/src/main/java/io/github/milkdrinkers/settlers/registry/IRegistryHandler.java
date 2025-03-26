package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.Reloadable;

public interface IRegistryHandler extends Reloadable {
    IRegistryHolder getRegistry();
}

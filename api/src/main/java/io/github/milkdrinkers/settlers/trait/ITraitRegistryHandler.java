package io.github.milkdrinkers.settlers.trait;

import io.github.milkdrinkers.settlers.Reloadable;

public interface ITraitRegistryHandler extends Reloadable {
    ITraitRegistry getRegistry();
}

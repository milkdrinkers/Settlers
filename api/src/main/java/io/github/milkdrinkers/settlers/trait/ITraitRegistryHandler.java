package io.github.milkdrinkers.settlers.trait;

import io.github.milkdrinkers.settlers.Reloadable;
import org.jetbrains.annotations.NotNull;

public interface ITraitRegistryHandler extends Reloadable {
    @NotNull ITraitRegistry getRegistry();
}

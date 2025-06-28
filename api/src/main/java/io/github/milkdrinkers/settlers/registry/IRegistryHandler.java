package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.Reloadable;
import org.jetbrains.annotations.NotNull;

public interface IRegistryHandler extends Reloadable {
    @NotNull IRegistryHolder getRegistry();
}

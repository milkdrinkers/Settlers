package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.Reloadable;
import org.jetbrains.annotations.NotNull;

public interface ILookupHandler extends Reloadable {
    @NotNull ILookupHolder getHolder();
}

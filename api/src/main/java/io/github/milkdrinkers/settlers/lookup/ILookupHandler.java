package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.Reloadable;

public interface ILookupHandler extends Reloadable {
    ILookupHolder getHolder();
}

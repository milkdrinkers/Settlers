package io.github.milkdrinkers.settlers.lookup;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class LookupTable<T, R> implements ILookupTable<T, R> {
    private final HashMap<T, R> keyMap = new HashMap<>();
    private final HashMap<R, T> valueMap = new HashMap<>();

    @ApiStatus.Internal
    public LookupTable() {
    }

    @ApiStatus.Internal
    @Override
    public void add(T object1, R object2) {
        keyMap.put(object1, object2);
        valueMap.put(object2, object1);
    }

    @ApiStatus.Internal
    @Override
    public void removeByKey(T object1) {
        valueMap.remove(lookupValue(object1));
        keyMap.remove(object1);
    }

    @ApiStatus.Internal
    @Override
    public void removeByValue(R object2) {
        keyMap.remove(lookupKey(object2));
        valueMap.remove(object2);
    }

    @Override
    public @Nullable T lookupKey(R object2) {
        return valueMap.get(object2);
    }

    @Override
    public @Nullable R lookupValue(T object1) {
        return keyMap.get(object1);
    }

    @ApiStatus.Internal
    @Override
    public void clear() {
        keyMap.clear();
        valueMap.clear();
    }
}

package io.github.milkdrinkers.settlers.lookup;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LookupTable<K, V> implements ILookupTable<K, V> {
    private final Map<K, V> keyMap = new ConcurrentHashMap<>();
    private final Map<V, K> valueMap = new ConcurrentHashMap<>();

    @ApiStatus.Internal
    public LookupTable() {
    }

    @ApiStatus.Internal
    @Override
    public void add(@NotNull K object1, @NotNull V object2) {
        keyMap.put(object1, object2);
        valueMap.put(object2, object1);
    }

    @ApiStatus.Internal
    @Override
    public void removeByKey(@NotNull K object1) {
        valueMap.remove(lookupValue(object1));
        keyMap.remove(object1);
    }

    @ApiStatus.Internal
    @Override
    public void removeByValue(@NotNull V object2) {
        keyMap.remove(lookupKey(object2));
        valueMap.remove(object2);
    }

    @Override
    public @Nullable K lookupKey(@NotNull V object2) {
        return valueMap.get(object2);
    }

    @Override
    public @Nullable V lookupValue(@NotNull K object1) {
        return keyMap.get(object1);
    }

    @ApiStatus.Internal
    @Override
    public void clear() {
        keyMap.clear();
        valueMap.clear();
    }

    public boolean hasKey(@NotNull K object1) {
        return keyMap.containsKey(object1);
    }

    public boolean hasValue(@NotNull V object2) {
        return valueMap.containsKey(object2);
    }
}

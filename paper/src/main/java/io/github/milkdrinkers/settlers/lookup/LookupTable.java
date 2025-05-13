package io.github.milkdrinkers.settlers.lookup;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class LookupTable<K, V> implements ILookupTable<K, V> {
    private final HashMap<K, V> keyMap = new HashMap<>();
    private final HashMap<V, K> valueMap = new HashMap<>();

    @ApiStatus.Internal
    public LookupTable() {
    }

    @ApiStatus.Internal
    @Override
    public void add(K object1, V object2) {
        keyMap.put(object1, object2);
        valueMap.put(object2, object1);
    }

    @ApiStatus.Internal
    @Override
    public void removeByKey(K object1) {
        valueMap.remove(lookupValue(object1));
        keyMap.remove(object1);
    }

    @ApiStatus.Internal
    @Override
    public void removeByValue(V object2) {
        keyMap.remove(lookupKey(object2));
        valueMap.remove(object2);
    }

    @Override
    public @Nullable K lookupKey(V object2) {
        return valueMap.get(object2);
    }

    @Override
    public @Nullable V lookupValue(K object1) {
        return keyMap.get(object1);
    }

    @ApiStatus.Internal
    @Override
    public void clear() {
        keyMap.clear();
        valueMap.clear();
    }

    public boolean hasKey(K object1) {
        return keyMap.containsKey(object1);
    }

    public boolean hasValue(V object2) {
        return valueMap.containsKey(object2);
    }
}

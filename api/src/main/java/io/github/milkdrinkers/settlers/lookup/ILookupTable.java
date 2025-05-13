package io.github.milkdrinkers.settlers.lookup;

import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a lookup table that can be used to lookup values by keys and vice-versa.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface ILookupTable<K, V> {
    /**
     * Adds a key-value pair to the lookup table.
     *
     * @param object1 the key
     * @param object2 the value
     */
    @ApiStatus.Internal
    void add(K object1, V object2);

    /**
     * Removes a key-value pair from the lookup table by key.
     *
     * @param object1 the key
     */
    @ApiStatus.Internal
    void removeByKey(K object1);

    /**
     * Removes a key-value pair from the lookup table by value.
     *
     * @param object2 the value
     */
    @ApiStatus.Internal
    void removeByValue(V object2);

    /**
     * Looks up a key by value.
     *
     * @param object2 the value
     * @return the key
     */
    K lookupKey(V object2);

    /**
     * Looks up a value by key.
     *
     * @param object1 the key
     * @return the value
     */
    V lookupValue(K object1);

    /**
     * Clears the lookup table.
     */
    @ApiStatus.Internal
    void clear();

    /**
     * Checks if a given key exists.
     *
     * @param object1 the key.
     * @return true if the key exists.
     */
    boolean hasKey(K object1);

    /**
     * Checks if a given value exists.
     *
     * @param object2 the value
     * @return true if the value exists.
     */
    boolean hasValue(V object2);
}

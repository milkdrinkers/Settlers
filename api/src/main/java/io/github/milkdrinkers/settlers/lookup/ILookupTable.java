package io.github.milkdrinkers.settlers.lookup;

import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a lookup table that can be used to lookup values by keys and vice-versa.
 *
 * @param <T> the key type
 * @param <R> the value type
 */
public interface ILookupTable<T, R> {
    /**
     * Adds a key-value pair to the lookup table.
     *
     * @param object1 the key
     * @param object2 the value
     */
    @ApiStatus.Internal
    void add(T object1, R object2);

    /**
     * Removes a key-value pair from the lookup table by key.
     *
     * @param object1 the key
     */
    @ApiStatus.Internal
    void removeByKey(T object1);

    /**
     * Removes a key-value pair from the lookup table by value.
     *
     * @param object2 the value
     */
    @ApiStatus.Internal
    void removeByValue(R object2);

    /**
     * Looks up a key by value.
     *
     * @param object2 the value
     * @return the key
     */
    T lookupKey(R object2);

    /**
     * Looks up a value by key.
     *
     * @param object1 the key
     * @return the value
     */
    R lookupValue(T object1);

    /**
     * Clears the lookup table.
     */
    @ApiStatus.Internal
    void clear();
}

package io.github.milkdrinkers.settlers.trait;

import net.citizensnpcs.api.trait.TraitInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * The trait registry holds a list of traits that are registered by settlers on startup.
 * The registry is accessible through settlers API, thus other plugins can register traits through settlers.
 */
public class TraitRegistry implements Iterable<TraitInfo> {
    private final List<TraitInfo> traitInfoList = new ArrayList<>();

    /**
     * Get a list of all traits that will/have been registered by settlers on startup
     * @return list of traits
     */
    public List<TraitInfo> getTraits() {
        return traitInfoList;
    }

    /**
     * Adds a trait that will be registered on startup through Settlers
     * @param trait the trait info instance
     * @return success
     */
    public boolean addTrait(TraitInfo trait) {
        return getTraits().add(trait);
    }

    /**
     * Used on plugin shutdown to clear the registry of traits to add
     */
    @ApiStatus.Internal
    protected void clearTraits() {
        getTraits().clear();
    }

    @Override
    public @NotNull Iterator<TraitInfo> iterator() {
        return getTraits().iterator();
    }

    @Override
    public void forEach(Consumer<? super TraitInfo> action) {
        getTraits().forEach(action);
    }

    @Override
    public Spliterator<TraitInfo> spliterator() {
        return getTraits().spliterator();
    }
}

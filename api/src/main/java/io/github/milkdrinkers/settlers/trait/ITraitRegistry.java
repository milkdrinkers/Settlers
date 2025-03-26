package io.github.milkdrinkers.settlers.trait;

import net.citizensnpcs.api.trait.TraitInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface ITraitRegistry extends Iterable<TraitInfo> {
    /**
     * Get a list of all traits that will/have been registered by settlers on startup
     *
     * @return list of traits
     */
    List<TraitInfo> getTraits();

    /**
     * Adds a trait that will be registered on startup through Settlers
     *
     * @param trait the trait info instance
     * @return success
     */
    boolean addTrait(TraitInfo trait);

    /**
     * Used on plugin shutdown to clear the registry of traits to add
     */
    @ApiStatus.Internal
    void clearTraits();

    @Override
    @NotNull Iterator<TraitInfo> iterator();

    @Override
    void forEach(Consumer<? super TraitInfo> action);

    @Override
    Spliterator<TraitInfo> spliterator();
}

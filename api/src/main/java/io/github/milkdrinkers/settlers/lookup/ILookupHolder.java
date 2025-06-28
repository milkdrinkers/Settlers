package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A holder for the lookup tables used by Settlers.
 */
public interface ILookupHolder extends Listener {
    /**
     * Get the lookup table for NPC's.
     *
     * @return The lookup table for NPC's.
     */
    ILookupTable<AbstractSettler, NPC> getNpcLookupTable();

    /**
     * Get the lookup table for entities.
     *
     * @return The lookup table for entities.
     */
    ILookupTable<AbstractSettler, Entity> getEntityLookupTable();

    /**
     * Adds the settler, npc, and potentially entity to the lookup tables.
     *
     * @param settler the settler to add.
     */
    @ApiStatus.Internal
    default void addToLookupTables(@NotNull AbstractSettler settler) {
        Objects.requireNonNull(settler, "settler is null when adding to lookup tables");
        getNpcLookupTable().add(settler, settler.getNpc());
        if (settler.isSpawned())
            getEntityLookupTable().add(settler, settler.getEntity());
    }

    /**
     * Registers a settler for the given NPC. This will create a new settler object and add it to the lookup tables.
     *
     * @param npc the NPC to register
     */
    @ApiStatus.Internal
    void registerSettler(@NotNull NPC npc);
}

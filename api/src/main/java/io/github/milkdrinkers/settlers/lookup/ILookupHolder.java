package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

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
        getNpcLookupTable().add(settler, settler.getNpc());
        if (settler.isSpawned())
            getEntityLookupTable().add(settler, settler.getEntity());
    }
}

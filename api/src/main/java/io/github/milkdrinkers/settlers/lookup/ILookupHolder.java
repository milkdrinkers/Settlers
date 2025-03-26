package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

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
}

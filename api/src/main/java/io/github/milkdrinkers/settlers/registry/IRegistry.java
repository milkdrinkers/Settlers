package io.github.milkdrinkers.settlers.registry;

import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPCRegistry;

public interface IRegistry {
    NPCRegistry getPersistentRegistry();

    NPCRegistry getEphemeralRegistry();

    IDataStore getDataStore();

    /**
     * Saves the {@link NPCRegistry}'s to their datastore
     *
     * @implNote Internally executes {@link NPCRegistry#saveToStore()}
     */
    void save();

    /**
     * Despawn all npc's from the {@link NPCRegistry}'s without saving first
     */
    void despawn(DespawnReason reason);
}

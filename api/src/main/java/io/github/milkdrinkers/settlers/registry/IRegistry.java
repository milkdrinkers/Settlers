package io.github.milkdrinkers.settlers.registry;

import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.jetbrains.annotations.NotNull;

public interface IRegistry {
    @NotNull NPCRegistry getPersistentRegistry();

    @NotNull NPCRegistry getEphemeralRegistry();

    @NotNull IDataStore getDataStore();

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

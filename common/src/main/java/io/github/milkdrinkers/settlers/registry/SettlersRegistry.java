package io.github.milkdrinkers.settlers.registry;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;

import java.io.File;

/**
 * A wrapper class for a collection of Citizens {@link NPCRegistry}'s.
 * Each SettlerRegistry contains one registry which persists in a .yml save file of the registry name,
 * and one ephemeral (temporary/in-memory) registry for ephemeral settlers.
 */
public class SettlersRegistry {
    final SettlersDataStore dataStore;
    final NPCRegistry persistentRegistry;
    final NPCRegistry ephemeralRegistry;

    public SettlersRegistry(File directory, String name) {
        dataStore = new SettlersDataStore(directory, name);
        persistentRegistry = CitizensAPI.createNamedNPCRegistry(name, dataStore.getDataStore());
        ephemeralRegistry = CitizensAPI.createInMemoryNPCRegistry("ephemeral_%s".formatted(name));
    }

    public NPCRegistry getPersistentRegistry() {
        return persistentRegistry;
    }

    public NPCRegistry getEphemeralRegistry() {
        return ephemeralRegistry;
    }

    public SettlersDataStore getDataStore() {
        return dataStore;
    }

    /**
     * Saves the {@link NPCRegistry}'s to their datastore
     * @implNote Internally executes {@link NPCRegistry#saveToStore()}
     */
    public void save() {
        getPersistentRegistry().saveToStore();
        getEphemeralRegistry().saveToStore();
    }
}

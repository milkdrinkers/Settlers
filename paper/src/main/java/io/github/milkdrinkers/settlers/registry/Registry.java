package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.LocationLookup;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPCRegistry;

import java.io.File;

/**
 * A wrapper class for a collection of Citizens {@link NPCRegistry}'s.
 * Each Registry contains one registry which persists in a .yml save file of the registry name,
 * and one ephemeral (temporary/in-memory) registry for ephemeral settlers.
 */
public class Registry implements IRegistry {
    final IDataStore dataStore;
    final NPCRegistry persistentRegistry;
    final NPCRegistry ephemeralRegistry;
    final LocationLookup persistentLocationLookup;
    final LocationLookup ephemeralLocationLookup;

    public Registry(ISettlersPlugin plugin, File directory, String name) {
        dataStore = new DataStore(directory, name);
        persistentRegistry = CitizensAPI.createNamedNPCRegistry(name, dataStore.getDataStore());
        dataStore.getDataStore().loadInto(persistentRegistry); // Load stored settlers into the registry
        ephemeralRegistry = CitizensAPI.createInMemoryNPCRegistry("ephemeral_%s".formatted(name));
        persistentLocationLookup = new LocationLookup(persistentRegistry);
        ephemeralLocationLookup = new LocationLookup(ephemeralRegistry);
        persistentLocationLookup.runTaskTimer(plugin, 0, 5);
        ephemeralLocationLookup.runTaskTimer(plugin, 0, 5);
    }

    @Override
    public NPCRegistry getPersistentRegistry() {
        return persistentRegistry;
    }

    @Override
    public NPCRegistry getEphemeralRegistry() {
        return ephemeralRegistry;
    }

    @Override
    public IDataStore getDataStore() {
        return dataStore;
    }

    /**
     * Saves the {@link NPCRegistry}'s to their datastore
     *
     * @implNote Internally executes {@link NPCRegistry#saveToStore()}
     */
    @Override
    public void save() {
        getPersistentRegistry().saveToStore();
        getEphemeralRegistry().saveToStore();
    }

    /**
     * Despawns all NPC's in the registries
     *
     * @param reason the reason for despawning
     */
    @Override
    public void despawn(DespawnReason reason) {
        getPersistentRegistry().despawnNPCs(reason);
        getEphemeralRegistry().despawnNPCs(reason);
    }
}

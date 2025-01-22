package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.api.exception.SettlersLoadException;
import net.citizensnpcs.api.npc.NPCDataStore;
import net.citizensnpcs.api.npc.SimpleNPCDataStore;
import net.citizensnpcs.api.util.Storage;
import net.citizensnpcs.api.util.YamlStorage;

import java.io.File;

public class SettlersDataStore {
    private final NPCDataStore dataStore;

    SettlersDataStore(File directory, String name) {
        dataStore = SimpleNPCDataStore.create(getOrCreateSaveFile(new File(directory, "%s.yml".formatted(name))));
    }

    public NPCDataStore getDataStore() {
        return dataStore;
    }

    /**
     * Get or creates a yaml file for storing settlers in a {@link NPCDataStore}.
     * @param file the file
     * @return storage
     * @throws SettlersLoadException if the file could not be parsed or created
     */
    private static Storage getOrCreateSaveFile(File file) throws SettlersLoadException {
        final Storage storage = new YamlStorage(file, "Settlers NPC Storage");
        if (!storage.load())
            throw new SettlersLoadException("Failed to load stored settlers!");

        return storage;
    }
}

package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.api.exception.SettlersLoadException;
import net.citizensnpcs.api.npc.NPCDataStore;
import net.citizensnpcs.api.npc.SimpleNPCDataStore;
import net.citizensnpcs.api.util.Storage;
import net.citizensnpcs.api.util.YamlStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

/**
 * A wrapper class for storing settlers in a {@link NPCDataStore}.
 */
public final class DataStore implements IDataStore {
    private final NPCDataStore dataStore;

    DataStore(@NotNull File directory, @NotNull String name) {
        Objects.requireNonNull(directory, "directory cannot be null when creating settlers datastore");
        Objects.requireNonNull(name, "name cannot be null when creating settlers datastore");
        dataStore = SimpleNPCDataStore.create(getOrCreateSaveFile(new File(directory, "%s.yml".formatted(name))));
    }

    @Override
    public @NotNull NPCDataStore getDataStore() {
        Objects.requireNonNull(dataStore, "npc datastore instance is null in settlers datastore instance");
        return dataStore;
    }

    /**
     * Get or creates a yaml file for storing settlers in a {@link NPCDataStore}.
     *
     * @param file the file
     * @return storage
     * @throws SettlersLoadException if the file could not be parsed or created
     */
    private static Storage getOrCreateSaveFile(File file) throws SettlersLoadException {
        final YamlStorage storage = new YamlStorage(file);
        if (!storage.load())
            throw new SettlersLoadException("Failed to load stored settlers!");

        return storage;
    }
}

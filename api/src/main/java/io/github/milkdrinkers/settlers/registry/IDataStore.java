package io.github.milkdrinkers.settlers.registry;

import net.citizensnpcs.api.npc.NPCDataStore;
import org.jetbrains.annotations.NotNull;

public interface IDataStore {
    @NotNull NPCDataStore getDataStore();
}

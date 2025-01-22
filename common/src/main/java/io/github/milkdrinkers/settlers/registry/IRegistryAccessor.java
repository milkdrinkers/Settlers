package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.jetbrains.annotations.Nullable;

public interface IRegistryAccessor {
    @Nullable SettlersRegistry getRegistry(String name);

    SettlersRegistry getRegistry(SettlerType type);

    NPCRegistry getRegistryCompanion();

    NPCRegistry getRegistryGuard();

    NPCRegistry getRegistryNation();

    NPCRegistry getRegistryTown();

    NPCRegistry getRegistryEphemeralCompanion();

    NPCRegistry getRegistryEphemeralGuard();

    NPCRegistry getRegistryEphemeralNation();

    NPCRegistry getRegistryEphemeralTown();

    Iterable<NPCRegistry> getRegistries();

    Iterable<NPC> getNPCs();
}

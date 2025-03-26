package io.github.milkdrinkers.settlers.registry;

import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface IRegistryHolder extends Iterable<NPCRegistry> {
    Map<String, IRegistry> getRegistryMap();

    @Nullable IRegistry getRegistry(String name);

    IRegistry getRegistry(SettlerType type);

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

    @Override
    @NotNull Iterator<NPCRegistry> iterator();

    @Override
    void forEach(Consumer<? super NPCRegistry> action);

    @Override
    Spliterator<NPCRegistry> spliterator();
}

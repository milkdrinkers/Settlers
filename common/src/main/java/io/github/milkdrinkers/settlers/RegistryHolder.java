package io.github.milkdrinkers.settlers;

import com.google.common.collect.Iterables;
import io.github.milkdrinkers.settlers.enums.SettlerType;
import io.github.milkdrinkers.settlers.registry.SettlersRegistry;
import net.citizensnpcs.api.npc.NPCRegistry;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RegistryHolder {
    private static SettlersRegistry REGISTRY_COMPANION = null;
    private static SettlersRegistry REGISTRY_GUARD = null;
    private static SettlersRegistry REGISTRY_NATION = null;
    private static SettlersRegistry REGISTRY_TOWN = null;

    public static NPCRegistry getRegistry(SettlerType type) {
        return switch (type) {
            case COMPANION -> getCompanionRegistry();
            case GUARD -> getGuardRegistry();
            case NATION -> getNationRegistry();
            case TOWN -> getTownRegistry();
        };
    }

    public static NPCRegistry getCompanionRegistry() {
        return REGISTRY_COMPANION.getPersistentRegistry();
    }

    public static NPCRegistry getGuardRegistry() {
        return REGISTRY_GUARD.getPersistentRegistry();
    }

    public static NPCRegistry getNationRegistry() {
        return REGISTRY_NATION.getPersistentRegistry();
    }

    public static NPCRegistry getTownRegistry() {
        return REGISTRY_TOWN.getPersistentRegistry();
    }

    public static NPCRegistry getTemporaryCompanionRegistry() {
        return REGISTRY_COMPANION.getEphemeralRegistry();
    }

    public static NPCRegistry getTemporaryGuardRegistry() {
        return REGISTRY_GUARD.getEphemeralRegistry();
    }

    public static NPCRegistry getTemporaryNationRegistry() {
        return REGISTRY_NATION.getEphemeralRegistry();
    }

    public static NPCRegistry getTemporaryTownRegistry() {
        return REGISTRY_TOWN.getEphemeralRegistry();
    }

    public static Iterable<NPCRegistry> getRegistries() {
        return () -> new Iterator<>() {
            Iterator<NPCRegistry> iter;

            @Override
            public boolean hasNext() {
                return iter == null || iter.hasNext();
            }

            @Override
            public NPCRegistry next() {
                if (iter == null) {
                    iter = Iterables
                        .concat(
                            Collections.singleton(List.of(
                                getCompanionRegistry(),
                                getGuardRegistry(),
                                getNationRegistry(),
                                getTownRegistry(),
                                getTemporaryCompanionRegistry(),
                                getTemporaryGuardRegistry(),
                                getTemporaryNationRegistry(),
                                getTemporaryTownRegistry()
                            ))
                        )
                        .iterator();
                }
                return iter.next();
            }
        };
    }

    protected static void onEnable(File directory) {
        REGISTRY_COMPANION = new SettlersRegistry(directory, "companions");
        REGISTRY_GUARD = new SettlersRegistry(directory, "guards");
        REGISTRY_NATION = new SettlersRegistry(directory, "nations");
        REGISTRY_TOWN = new SettlersRegistry(directory, "towns");
    }

    protected static void onDisable() {
        REGISTRY_COMPANION.getPersistentRegistry().saveToStore();
        REGISTRY_GUARD.getPersistentRegistry().saveToStore();
        REGISTRY_NATION.getPersistentRegistry().saveToStore();
        REGISTRY_TOWN.getPersistentRegistry().saveToStore();

        REGISTRY_COMPANION = null;
        REGISTRY_GUARD = null;
        REGISTRY_NATION = null;
        REGISTRY_TOWN = null;
    }
}

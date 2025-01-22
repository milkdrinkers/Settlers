package io.github.milkdrinkers.settlers.registry;

import com.google.common.collect.Iterables;
import io.github.milkdrinkers.settlers.SettlersPlugin;
import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

import static io.github.milkdrinkers.settlers.SettlersPlugin.DIR_DATA;

public class SettlersRegistries implements Iterable<NPCRegistry>, IRegistryAccessor {
    // Holds the Settler Registries which contain NPC Registries
    private final Map<String, SettlersRegistry> registryMap = new HashMap<>();

    // References
    private SettlersRegistry REGISTRY_REF_COMPANION;
    private SettlersRegistry REGISTRY_REF_GUARD;
    private SettlersRegistry REGISTRY_REF_NATION;
    private SettlersRegistry REGISTRY_REF_TOWN;

    /**
     * Instantiates a new settlers registry for {@link NPCRegistry}'s
     * @param plugin plugin
     * @implSpec Needs to be run on onEnabled as CitizensAPI needs to be available
     */
    @ApiStatus.Internal
    protected SettlersRegistries(SettlersPlugin plugin) {
        final File dir = plugin.getDataFolder().toPath().resolve(DIR_DATA).toFile();

        for (SettlerType type : SettlerType.values()) {
            final String typeName = type.getName();

            // Add registry to hash map
            getRegistryMap().put(typeName, new SettlersRegistry(dir, typeName));

            // Point fields to reference the hash map entries
            switch (type) {
                case COMPANION -> REGISTRY_REF_COMPANION = getRegistryMap().get(typeName);
                case GUARD -> REGISTRY_REF_GUARD = getRegistryMap().get(typeName);
                case NATION -> REGISTRY_REF_NATION = getRegistryMap().get(typeName);
                case TOWN -> REGISTRY_REF_TOWN = getRegistryMap().get(typeName);
            }
        }
    }

    /**
     * Saves all persistent npc's to their data stores before unloading the npc registries
     */
    @ApiStatus.Internal
    protected void onDisable(SettlersPlugin plugin) {
        // Clear references to jvm can collect these objects
        REGISTRY_REF_COMPANION = null;
        REGISTRY_REF_GUARD = null;
        REGISTRY_REF_NATION = null;
        REGISTRY_REF_TOWN = null;

        // Save data
        for (SettlersRegistry registry : getRegistryMap().values()) {
            registry.save();
        }

        // Clear hash map
        getRegistryMap().clear();
    }

    public Map<String, SettlersRegistry> getRegistryMap() {
        return registryMap;
    }

    @Override
    public @Nullable SettlersRegistry getRegistry(String name) {
        return getRegistryMap().get(name);
    }

    @Override
    public SettlersRegistry getRegistry(SettlerType type) {
        return switch (type) {
            case COMPANION -> REGISTRY_REF_COMPANION;
            case GUARD -> REGISTRY_REF_GUARD;
            case NATION -> REGISTRY_REF_NATION;
            case TOWN -> REGISTRY_REF_TOWN;
        };
    }

    @Override
    public NPCRegistry getRegistryCompanion() {
        return REGISTRY_REF_COMPANION.getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryGuard() {
        return REGISTRY_REF_GUARD.getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryNation() {
        return REGISTRY_REF_NATION.getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryTown() {
        return REGISTRY_REF_TOWN.getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralCompanion() {
        return REGISTRY_REF_COMPANION.getEphemeralRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralGuard() {
        return REGISTRY_REF_GUARD.getEphemeralRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralNation() {
        return REGISTRY_REF_NATION.getEphemeralRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralTown() {
        return REGISTRY_REF_TOWN.getEphemeralRegistry();
    }

    @Override
    public Iterable<NPCRegistry> getRegistries() {
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
                                getRegistryCompanion(),
                                getRegistryGuard(),
                                getRegistryNation(),
                                getRegistryTown(),
                                getRegistryEphemeralCompanion(),
                                getRegistryEphemeralGuard(),
                                getRegistryEphemeralNation(),
                                getRegistryEphemeralTown()
                            ))
                        )
                        .iterator();
                }
                return iter.next();
            }
        };
    }

    @Override
    public Iterable<NPC> getNPCs() {
        return () -> new Iterator<>() {
            Iterator<NPC> iter;

            @Override
            public boolean hasNext() {
                return iter == null || iter.hasNext();
            }

            @Override
            public NPC next() {
                if (iter == null) {
                    iter = Iterables
                        .concat(
                            List.of(
                                getRegistryCompanion(),
                                getRegistryGuard(),
                                getRegistryNation(),
                                getRegistryTown(),
                                getRegistryEphemeralCompanion(),
                                getRegistryEphemeralGuard(),
                                getRegistryEphemeralNation(),
                                getRegistryEphemeralTown()
                            )
                        )
                        .iterator();
                }
                return iter.next();
            }
        };
    }

    @Override
    public @NotNull Iterator<NPCRegistry> iterator() {
        return getRegistries().iterator();
    }

    @Override
    public void forEach(Consumer<? super NPCRegistry> action) {
        getRegistries().forEach(action);
    }

    @Override
    public Spliterator<NPCRegistry> spliterator() {
        return getRegistries().spliterator();
    }
}

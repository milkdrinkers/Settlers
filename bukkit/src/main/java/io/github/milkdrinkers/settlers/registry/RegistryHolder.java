package io.github.milkdrinkers.settlers.registry;

import com.google.common.collect.Iterables;
import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.Reloadable;
import io.github.milkdrinkers.settlers.api.ILifecycle;
import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static io.github.milkdrinkers.settlers.ISettlersPlugin.DIR_DATA;

/**
 * A wrapper class holding and managing life of all {@link IRegistry}'s.
 */
public final class RegistryHolder implements IRegistryHolder, Listener, Reloadable, ILifecycle {
    // Holds the AbstractSettler Registries which contain NPC Registries
    private final ISettlersPlugin plugin;
    private final Map<String, IRegistry> registryMap = new ConcurrentHashMap<>();

    /**
     * Instantiates a new settlers registry for {@link NPCRegistry}'s
     *
     * @param plugin plugin
     * @implSpec Needs to be run on onEnabled as CitizensAPI needs to be available
     */
    @ApiStatus.Internal
    protected RegistryHolder(ISettlersPlugin plugin) {
        Objects.requireNonNull(plugin, "plugin instance is null in registry holder");
        this.plugin = plugin;
    }

    @Override
    public void onLoad(ISettlersPlugin plugin) {
    }

    @Override
    public void onEnable(ISettlersPlugin plugin) {
    }

    @Override
    public void onDisable(ISettlersPlugin plugin) {
    }

    @Override
    public void onInit(ISettlersPlugin plugin) {
        final File dir = plugin.getDataFolder().toPath().resolve(DIR_DATA).toFile();

        // Create registries
        for (SettlerType type : SettlerType.values()) {
            final String typeName = type.getName();

            // Add registry to hash map
            this.getRegistryMap().put(typeName, new Registry(plugin, dir, typeName));
        }
    }

    /**
     * Saves all persistent npc's to their data stores before unloading the npc registries
     *
     * @param plugin plugin
     */
    @Override
    public void onUnload(ISettlersPlugin plugin) {
        // Save data
        for (IRegistry registry : getRegistryMap().values()) {
            registry.save();
            registry.despawn(DespawnReason.RELOAD);
        }

        // Clear hash map
        getRegistryMap().clear();
    }

    @Override
    public Map<String, IRegistry> getRegistryMap() {
        return registryMap;
    }

    @Override
    public @Nullable IRegistry getRegistry(String name) {
        return getRegistryMap().get(name);
    }

    @Override
    public IRegistry getRegistry(SettlerType type) {
        return getRegistryMap().get(type.getName());
    }

    @Override
    public NPCRegistry getRegistryCompanion() {
        return getRegistryMap().get(SettlerType.COMPANION.getName()).getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryGuard() {
        return getRegistryMap().get(SettlerType.GUARD.getName()).getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryNation() {
        return getRegistryMap().get(SettlerType.NATION.getName()).getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryTown() {
        return getRegistryMap().get(SettlerType.TOWN.getName()).getPersistentRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralCompanion() {
        return getRegistryMap().get(SettlerType.COMPANION.getName()).getEphemeralRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralGuard() {
        return getRegistryMap().get(SettlerType.GUARD.getName()).getEphemeralRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralNation() {
        return getRegistryMap().get(SettlerType.NATION.getName()).getEphemeralRegistry();
    }

    @Override
    public NPCRegistry getRegistryEphemeralTown() {
        return getRegistryMap().get(SettlerType.TOWN.getName()).getEphemeralRegistry();
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

package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerDespawnEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerSpawnEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import static io.github.milkdrinkers.settlers.api.enums.SettlerType.*;

public class SettlerLookup implements Listener {
    private static final HashMap<Settler, NPC> settlerToNPCMap = new HashMap<>();
    private static final HashMap<NPC, Settler> npcToSettlerMap = new HashMap<>();
    private static final HashMap<Settler, Entity> settlerToEntityMap = new HashMap<>();
    private static final HashMap<Entity, Settler> entityToSettlerMap = new HashMap<>();

    public static @Nullable NPC lookup(Settler settler) {
        return settlerToNPCMap.get(settler);
    }

    public static @Nullable Settler lookup(NPC npc) {
        return npcToSettlerMap.get(npc);
    }

    public static @Nullable Settler lookup(Entity entity) {
        return entityToSettlerMap.get(entity);
    }

    public static void addSettlerToLookup(NPC npc, Settler settler) {
        settlerToNPCMap.put(settler, npc);
        npcToSettlerMap.put(npc, settler);
    }

    /**
     * Clear all settlers from lookup table
     */
    @ApiStatus.Internal
    public static void clear() {
        settlerToNPCMap.clear();
        npcToSettlerMap.clear();
        settlerToEntityMap.clear();
        entityToSettlerMap.clear();
    }

    @EventHandler
    public static void addEntity(SettlerSpawnEvent e) {
        if (!(e.getSettler() instanceof Settler settler))
            return;

        if (!(settler.getNpc() instanceof NPC npc))
            return;

        if (npc.getEntity() instanceof Entity entity) {
            settlerToEntityMap.put(settler, entity);
            entityToSettlerMap.put(entity, settler);
        }
    }

    @EventHandler
    public static void removeEntity(SettlerDespawnEvent e) {
        if (!(e.getSettler() instanceof Settler settler))
            return;

        if (!(settler.getNpc() instanceof NPC npc))
            return;

        if (npc.getEntity() instanceof Entity entity) {
            settlerToEntityMap.remove(settler, entity);
            entityToSettlerMap.remove(entity, settler);
        }
    }

    /**
     * Add existing NPC's from registries into settler lookup table
     */
    @ApiStatus.Internal
    public static void loadRegistriesIntoLookup() {
        for (NPC npc : SettlersAPI.getRegistryCompanion()) {
            final Settler settler = new SettlerBuilder()
                .setNpc(npc)
                .setType(COMPANION)
                .createCompanion();
            addSettlerToLookup(npc, settler);
        }
        for (NPC npc : SettlersAPI.getRegistryGuard()) {
            final Settler settler = new SettlerBuilder()
                .setNpc(npc)
                .setType(GUARD)
                .createGuard();
            addSettlerToLookup(npc, settler);
        }
        for (NPC npc : SettlersAPI.getRegistryNation()) {
            final Nationfolk settler = new SettlerBuilder()
                .setNpc(npc)
                .setType(NATION)
                .createNationfolk();
            addSettlerToLookup(npc, settler);
        }
        for (NPC npc : SettlersAPI.getRegistryTown()) {
            final Settler settler = new SettlerBuilder()
                .setNpc(npc)
                .setType(TOWN)
                .createTownfolk();
            addSettlerToLookup(npc, settler);
        }
    }
}

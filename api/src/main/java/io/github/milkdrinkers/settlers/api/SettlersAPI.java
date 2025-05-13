package io.github.milkdrinkers.settlers.api;

import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import io.github.milkdrinkers.settlers.api.trait.*;
import io.github.milkdrinkers.settlers.registry.IRegistryHolder;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SettlersAPI extends AbstractSettlersAPI {
    private SettlersAPI() {
    }

    public static final String META_NPC = "NPC";
    public static final String META_SETTLER = "SETTLER";
    public static final String META_COMPANION = "COMPANION";
    public static final String META_GUARD = "GUARD";
    public static final String META_NATIONFOLK = "NATIONFOLK";
    public static final String META_TOWNFOLK = "TOWNFOLK";

    /**
     * Check if the entity is a NPC.
     * @param entity entity
     * @return true if the entity is a NPC, false otherwise
     */
    public static boolean isNPC(@NotNull Entity entity) {
        return entity.isValid() && entity.hasMetadata(META_NPC);
    }

    /**
     * Check if the entity is a settler.
     * @param entity entity
     * @return true if the entity is a settler, false otherwise
     */
    public static boolean isSettler(@NotNull Entity entity) {
        return isNPC(entity) && entity.hasMetadata(META_SETTLER);
    }

    /**
     * Check if the entity is a companion.
     * @param entity entity
     * @return true if the entity is a companion, false otherwise
     */
    public static boolean isCompanion(@NotNull Entity entity) {
        return isNPC(entity) && entity.hasMetadata(META_COMPANION);
    }

    /**
     * Check if the entity is a guard.
     * @param entity entity
     * @return true if the entity is a guard, false otherwise
     */
    public static boolean isGuard(@NotNull Entity entity) {
        return isNPC(entity) && entity.hasMetadata(META_GUARD);
    }

    /**
     * Check if the entity is a nation folk.
     * @param entity entity
     * @return true if the entity is a nation folk, false otherwise
     */
    public static boolean isNationFolk(@NotNull Entity entity) {
        return isNPC(entity) && entity.hasMetadata(META_NATIONFOLK);
    }

    /**
     * Check if the entity is a town folk.
     * @param entity entity
     * @return true if the entity is a town folk, false otherwise
     */
    public static boolean isTownFolk(@NotNull Entity entity) {
        return isNPC(entity) && entity.hasMetadata(META_TOWNFOLK);
    }

    /**
     * Check if the npc is a settler.
     * @param npc npc
     * @return true if the npc is a settler, false otherwise
     */
    public static boolean isSettler(@NotNull NPC npc) {
        return npc.hasTrait(SettlerTrait.class);
    }

    /**
     * Check if the npc is a companion.
     * @param npc npc
     * @return true if the npc is a companion, false otherwise
     */
    public static boolean isCompanion(@NotNull NPC npc) {
        return npc.hasTrait(CompanionTrait.class);
    }

    /**
     * Check if the npc is a guard.
     * @param npc npc
     * @return true if the npc is a guard, false otherwise
     */
    public static boolean isGuard(@NotNull NPC npc) {
        return npc.hasTrait(GuardTrait.class);
    }

    /**
     * Check if the npc is a nation folk.
     * @param npc npc
     * @return true if the npc is a nation folk, false otherwise
     */
    public static boolean isNationFolk(@NotNull NPC npc) {
        return npc.hasTrait(NationFolkTrait.class);
    }

    /**
     * Check if the npc is a town folk.
     * @param npc npc
     * @return true if the npc is a town folk, false otherwise
     */
    public static boolean isTownFolk(@NotNull NPC npc) {
        return npc.hasTrait(TownFolkTrait.class);
    }

    /**
     * Get the {@link AbstractSettler} object of this entity if it exists.
     *
     * @param entity entity
     * @return the settler or, null if the entity is not a settler
     */
    public static @Nullable AbstractSettler getSettler(@NotNull Entity entity) {
        if (!isSettler(entity))
            return null;

        return getImplementation().getLookupHandler().getHolder().getEntityLookupTable().lookupKey(entity);
    }

    /**
     * Get the {@link AbstractSettler} object of this npc if it exists.
     *
     * @param npc npc
     * @return the settler or, null if the npc is not a settler
     */
    public static @Nullable AbstractSettler getSettler(@NotNull NPC npc) {
        if (!isSettler(npc))
            return null;

        return getImplementation().getLookupHandler().getHolder().getNpcLookupTable().lookupKey(npc);
    }

    /**
     * Check what type of settler a npc is
     *
     * @param npc npc
     * @return a settler type or null
     */
    public static @Nullable SettlerType getSettlerType(@NotNull NPC npc) {
        if (npc.hasTrait(CompanionTrait.class))
            return SettlerType.COMPANION;

        if (npc.hasTrait(GuardTrait.class))
            return SettlerType.GUARD;

        if (npc.hasTrait(NationFolkTrait.class))
            return SettlerType.NATION;

        if (npc.hasTrait(TownFolkTrait.class))
            return SettlerType.TOWN;

        return null;
    }

    // SECTION Settlers Registries access

    public static IRegistryHolder getSettlersRegistries() {
        return getImplementation().getRegistryHandler().getRegistry();
    }

    // SECTION NPC Registry access

    public static NPCRegistry getRegistryCompanion() {
        return getSettlersRegistries().getRegistryCompanion();
    }

    public static NPCRegistry getRegistryGuard() {
        return getSettlersRegistries().getRegistryGuard();
    }

    public static NPCRegistry getRegistryNation() {
        return getSettlersRegistries().getRegistryNation();
    }

    public static NPCRegistry getRegistryTown() {
        return getSettlersRegistries().getRegistryTown();
    }

    public static NPCRegistry getRegistryEphemeralCompanion() {
        return getSettlersRegistries().getRegistryCompanion();
    }

    public static NPCRegistry getRegistryEphemeralGuard() {
        return getSettlersRegistries().getRegistryEphemeralGuard();
    }

    public static NPCRegistry getRegistryEphemeralNation() {
        return getSettlersRegistries().getRegistryEphemeralNation();
    }

    public static NPCRegistry getRegistryEphemeralTown() {
        return getSettlersRegistries().getRegistryEphemeralTown();
    }

    public static Iterable<NPCRegistry> getRegistries() {
        return getSettlersRegistries().getRegistries();
    }

    public static Iterable<NPC> getNPCs() {
        return getSettlersRegistries().getNPCs();
    }
}

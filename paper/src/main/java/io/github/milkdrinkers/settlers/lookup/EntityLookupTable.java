package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.github.milkdrinkers.settlers.api.SettlersAPI.*;

public final class EntityLookupTable extends LookupTable<AbstractSettler, Entity> {
    private final ISettlersPlugin plugin;
    private final ILookupHolder holder;

    EntityLookupTable(ISettlersPlugin plugin, ILookupHolder holder) {
        Objects.requireNonNull(plugin, "plugin instance is null in lookup table");
        Objects.requireNonNull(holder, "holder instance is null in lookup table");
        this.plugin = plugin;
        this.holder = holder;
    }

    @Override
    public @Nullable AbstractSettler lookupKey(@NotNull Entity entity) {
        Objects.requireNonNull(entity, "entity is null in lookup table");
        // Create a missing Settler object for this NPC (Fix for Settlers not being added to lookup tables before Citizens triggers Settler events)
        if (entity.hasMetadata(META_SETTLER) && !super.hasValue(entity)) {
            final NPC npc = getNPC(entity);
            if (npc != null) {
                holder.registerSettler(npc);
            }
        }

        return super.lookupKey(entity);
    }

    @Override
    public boolean hasValue(@NotNull Entity entity) {
        Objects.requireNonNull(entity, "entity is null in lookup table");
        final boolean hasValue = super.hasValue(entity);

        // Create a missing Settler object for this NPC (Fix for Settlers not being added to lookup tables before Citizens triggers Settler events)
        if (entity.hasMetadata(META_SETTLER) && !hasValue) {
            final NPC npc = getNPC(entity);
            if (npc != null) {
                holder.registerSettler(npc);
                return true;
            }
        }

        return hasValue;
    }

    private @Nullable NPC getNPC(Entity entity) {
        @Nullable NPC npc = null;
        if (entity.hasMetadata(META_COMPANION)) {
            npc = plugin.getRegistryHandler().getRegistry().getRegistryCompanion().getNPC(entity);
            if (npc != null)
                return npc;

            npc = plugin.getRegistryHandler().getRegistry().getRegistryEphemeralCompanion().getNPC(entity);

        } else if (entity.hasMetadata(META_GUARD)) {
            npc = plugin.getRegistryHandler().getRegistry().getRegistryGuard().getNPC(entity);
            if (npc != null)
                return npc;

            npc = plugin.getRegistryHandler().getRegistry().getRegistryEphemeralGuard().getNPC(entity);

        } else if (entity.hasMetadata(META_NATIONFOLK)) {
            npc = plugin.getRegistryHandler().getRegistry().getRegistryNation().getNPC(entity);
            if (npc != null)
                return npc;

            npc = plugin.getRegistryHandler().getRegistry().getRegistryEphemeralNation().getNPC(entity);

        } else if (entity.hasMetadata(META_TOWNFOLK)) {
            npc = plugin.getRegistryHandler().getRegistry().getRegistryTown().getNPC(entity);
            if (npc != null)
                return npc;

            npc = plugin.getRegistryHandler().getRegistry().getRegistryEphemeralTown().getNPC(entity);
        }

        return npc;
    }
}

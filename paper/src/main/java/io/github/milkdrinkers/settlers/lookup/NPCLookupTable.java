package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import io.github.milkdrinkers.settlers.api.trait.SettlerTrait;
import net.citizensnpcs.api.npc.NPC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class NPCLookupTable extends LookupTable<AbstractSettler, NPC> {
    private final ISettlersPlugin plugin;
    private final ILookupHolder holder;

    NPCLookupTable(ISettlersPlugin plugin, ILookupHolder holder) {
        Objects.requireNonNull(plugin, "plugin instance is null in lookup table");
        Objects.requireNonNull(holder, "holder instance is null in lookup table");
        this.plugin = plugin;
        this.holder = holder;
    }

    @Override
    public @Nullable AbstractSettler lookupKey(@NotNull NPC npc) {
        Objects.requireNonNull(npc, "npc is null in lookup table");
        // Create a missing Settler object for this NPC (Fix for Settlers not being added to lookup tables before Citizens triggers Settler events)
        if (npc.hasTrait(SettlerTrait.class) && !super.hasValue(npc)) {
            holder.registerSettler(npc);
        }

        return super.lookupKey(npc);
    }

    @Override
    public boolean hasValue(@NotNull NPC npc) {
        Objects.requireNonNull(npc, "npc is null in lookup table");
        final boolean hasValue = super.hasValue(npc);

        // Create a missing Settler object for this NPC (Fix for Settlers not being added to lookup tables before Citizens triggers Settler events)
        if (npc.hasTrait(SettlerTrait.class) && !hasValue) {
            holder.registerSettler(npc);
            return true;
        }

        return hasValue;
    }
}

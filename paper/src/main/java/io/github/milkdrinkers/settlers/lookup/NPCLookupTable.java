package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import io.github.milkdrinkers.settlers.api.trait.SettlerTrait;
import net.citizensnpcs.api.npc.NPC;
import org.jetbrains.annotations.Nullable;

public class NPCLookupTable extends LookupTable<AbstractSettler, NPC> {
    private final ISettlersPlugin plugin;
    private final LookupHolder holder;

    NPCLookupTable(ISettlersPlugin plugin) {
        this.plugin = plugin;
        this.holder = (LookupHolder) plugin.getLookupHandler().getHolder();
    }

    @Override
    public @Nullable AbstractSettler lookupKey(NPC npc) {
        // Create a missing Settler object for this NPC (Fix for Settlers not being added to lookup tables before Citizens triggers Settler events)
        if (npc.hasTrait(SettlerTrait.class) && !super.hasValue(npc)) {
            holder.registerSettler(npc);
        }

        return super.lookupKey(npc);
    }

    @Override
    public boolean hasValue(NPC npc) {
        final boolean hasValue = super.hasValue(npc);

        // Create a missing Settler object for this NPC (Fix for Settlers not being added to lookup tables before Citizens triggers Settler events)
        if (npc.hasTrait(SettlerTrait.class) && !hasValue) {
            holder.registerSettler(npc);
            return true;
        }

        return hasValue;
    }
}

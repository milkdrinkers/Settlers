package io.github.milkdrinkers.settlers.utility;

import io.github.milkdrinkers.settlers.Settlers;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import io.github.milkdrinkers.settlers.api.settler.SettlerBuilder;
import io.github.milkdrinkers.settlers.api.trait.*;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.Nullable;

import static io.github.milkdrinkers.settlers.api.SettlersAPI.*;
import static io.github.milkdrinkers.settlers.api.SettlersAPI.META_NATIONFOLK;
import static io.github.milkdrinkers.settlers.api.SettlersAPI.META_TOWNFOLK;

public class Utils {
    public static void applySettlerEntityMetadata(NPC npc, Entity entity) {
        if (!npc.hasTrait(SettlerTrait.class))
            return;

        // Set metadata for the entity
        entity.setMetadata(META_SETTLER, new FixedMetadataValue(Settlers.getInstance(), META_SETTLER));
        if (npc.hasTrait(CompanionTrait.class)) {
            entity.setMetadata(META_COMPANION, new FixedMetadataValue(Settlers.getInstance(), META_COMPANION));
        } else if (npc.hasTrait(GuardTrait.class)) {
            entity.setMetadata(META_GUARD, new FixedMetadataValue(Settlers.getInstance(), META_GUARD));
        } else if (npc.hasTrait(NationFolkTrait.class)) {
            entity.setMetadata(META_NATIONFOLK, new FixedMetadataValue(Settlers.getInstance(), META_NATIONFOLK));
        } else if (npc.hasTrait(TownFolkTrait.class)) {
            entity.setMetadata(META_TOWNFOLK, new FixedMetadataValue(Settlers.getInstance(), META_TOWNFOLK));
        }
    }

    /**
     * Create a settler from the given NPC.
     * @param npc the NPC to create the settler from
     * @return the settler or null if the NPC is not a settler
     */
    public static @Nullable AbstractSettler createSettler(NPC npc) {
        if (!npc.hasTrait(SettlerTrait.class))
            return null;

        final SettlerBuilder builder = new SettlerBuilder()
            .setNpc(npc);
        @Nullable AbstractSettler settler = null;

        if (npc.hasTrait(CompanionTrait.class)) {
            settler = builder.createCompanion();
        } else if (npc.hasTrait(GuardTrait.class)) {
            settler = builder.createGuard();
        } else if (npc.hasTrait(NationFolkTrait.class)) {
            settler = builder.createNationfolk();
        } else if (npc.hasTrait(TownFolkTrait.class)) {
            settler = builder.createTownfolk();
        }

        return settler;
    }
}

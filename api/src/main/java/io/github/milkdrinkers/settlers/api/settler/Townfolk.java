package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.SettlersPlugin;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.metadata.FixedMetadataValue;

import static io.github.milkdrinkers.settlers.SettlersAPI.META_TOWNFOLK;

public class Townfolk extends Settler {
    protected Townfolk(NPC npc) {
        super(npc);
    }

    @Override
    public boolean spawn(Location location, SpawnReason spawnReason) {
        final boolean result = super.spawn(location, spawnReason);
        super.getNpc().getEntity().setMetadata(META_TOWNFOLK, new FixedMetadataValue(SettlersPlugin.getInstance(), META_TOWNFOLK));
        return result;
    }
}

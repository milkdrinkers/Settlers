package io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.event.NPCDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SettlerDeathEvent extends AbstractSettlerEvent {
    private final NPCDeathEvent event;

    public SettlerDeathEvent(Settler settler, NPCDeathEvent event) {
        super(settler);
        this.event = event;
    }

    public int getDroppedExp() {
        return event.getDroppedExp();
    }

    public List<ItemStack> getDrops() {
        return event.getDrops();
    }

    public void setDroppedExp(int exp) {
        event.setDroppedExp(exp);
    }
}

package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.block.Block;

public class SettlerOpenEvent extends AbstractCancellableSettlerEvent {
    public final Block block;

    public SettlerOpenEvent(Settler settler, Block block) {
        super(settler);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

}

package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact;

import io.github.milkdrinkers.settlers.api.enums.DoorType;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.block.Block;

public class SettlerOpenEvent extends AbstractCancellableSettlerEvent {
    public final Block block;
    private final DoorType doorType;

    public SettlerOpenEvent(Settler settler, Block block, DoorType doorType) {
        super(settler);
        this.block = block;
        this.doorType = doorType;
    }

    public Block getBlock() {
        return block;
    }

    public DoorType getDoorType() {
        return doorType;
    }
}

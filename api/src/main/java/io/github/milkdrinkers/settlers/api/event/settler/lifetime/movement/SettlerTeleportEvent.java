package io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.Location;

public class SettlerTeleportEvent extends AbstractCancellableSettlerEvent {
    private final Location from;
    private final Location to;

    public SettlerTeleportEvent(AbstractSettler settler, Location from, Location to) {
        super(settler);
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }
}

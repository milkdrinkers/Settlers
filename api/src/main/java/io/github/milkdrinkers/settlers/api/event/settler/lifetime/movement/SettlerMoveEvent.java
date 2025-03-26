package io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.event.NPCMoveEvent;
import org.bukkit.Location;

public class SettlerMoveEvent extends AbstractCancellableSettlerEvent {
    private final NPCMoveEvent e;

    public SettlerMoveEvent(AbstractSettler settler, NPCMoveEvent e) {
        super(settler);
        this.e = e;
    }

    public Location getFrom() {
        return e.getFrom();
    }

    public Location getTo() {
        return e.getTo();
    }

    public void setFrom(Location from) {
        e.setFrom(from);
    }

    public void setTo(Location to) {
        e.setTo(to);
    }
}

package io.github.milkdrinkers.settlers.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.enums.RemoveReason;
import io.github.milkdrinkers.settlers.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.event.Cancellable;

public class SettlerRemoveEvent extends AbstractSettlerEvent {
    private final RemoveReason reason;

    public SettlerRemoveEvent(Settler settler, RemoveReason reason) { // TODO remove the settler from the registry
        super(settler);
        this.reason = reason;
    }

    public RemoveReason getReason() {
        return reason;
    }
}

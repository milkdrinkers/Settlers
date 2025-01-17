package io.github.milkdrinkers.settlers.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.enums.RemoveReason;
import io.github.milkdrinkers.settlers.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.event.Cancellable;

public class SettlerRemoveEvent extends AbstractSettlerEvent implements Cancellable {
    private final RemoveReason reason;
    private boolean cancelled;

    protected SettlerRemoveEvent(Settler settler, RemoveReason reason) {
        super(settler);
        this.reason = reason;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public RemoveReason getReason() {
        return reason;
    }
}

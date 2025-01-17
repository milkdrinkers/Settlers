package io.github.milkdrinkers.settlers.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.enums.CreateReason;
import io.github.milkdrinkers.settlers.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.event.Cancellable;

public class PlayerCloneSettlerEvent extends AbstractSettlerEvent implements Cancellable {
    private final CreateReason reason;
    private boolean cancelled;

    protected PlayerCloneSettlerEvent(Settler settler, CreateReason reason) {
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

    public CreateReason getReason() {
        return reason;
    }
}

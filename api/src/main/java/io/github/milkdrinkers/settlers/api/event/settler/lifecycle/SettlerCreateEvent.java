package io.github.milkdrinkers.settlers.api.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.api.enums.CreateReason;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.event.Cancellable;

public class SettlerCreateEvent extends AbstractSettlerEvent implements Cancellable {
    private final CreateReason reason;
    private boolean cancelled;

    public SettlerCreateEvent(Settler settler, CreateReason reason) { // TODO call this event when a settler is created
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

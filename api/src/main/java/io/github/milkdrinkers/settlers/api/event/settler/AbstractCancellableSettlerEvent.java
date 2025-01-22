package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.event.Cancellable;

public abstract class AbstractCancellableSettlerEvent extends AbstractSettlerEvent implements Cancellable {
    private boolean cancelled;

    public AbstractCancellableSettlerEvent(Settler settler) {
        super(settler);
    }

    public AbstractCancellableSettlerEvent(Settler settler, boolean async) {
        super(settler, async);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

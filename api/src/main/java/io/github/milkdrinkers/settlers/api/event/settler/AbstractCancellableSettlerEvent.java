package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.event.Cancellable;

public abstract class AbstractCancellableSettlerEvent extends AbstractSettlerEvent implements Cancellable {
    private boolean cancelled;

    public AbstractCancellableSettlerEvent(AbstractSettler settler) {
        super(settler);
    }

    public AbstractCancellableSettlerEvent(AbstractSettler settler, boolean async) {
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

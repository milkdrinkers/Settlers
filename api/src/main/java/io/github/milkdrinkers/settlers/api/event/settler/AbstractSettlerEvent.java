package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSettlerEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Settler settler;

    public AbstractSettlerEvent(Settler settler) {
        this.settler = settler;
    }

    public AbstractSettlerEvent(Settler settler, boolean async) {
        super(async);
        this.settler = settler;
    }

    public Settler getSettler() {
        return settler;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}

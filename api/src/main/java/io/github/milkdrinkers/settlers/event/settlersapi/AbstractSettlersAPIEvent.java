package io.github.milkdrinkers.settlers.event.settlersapi;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSettlersAPIEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public AbstractSettlersAPIEvent() {
    }

    public AbstractSettlersAPIEvent(boolean async) {
        super(async);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}

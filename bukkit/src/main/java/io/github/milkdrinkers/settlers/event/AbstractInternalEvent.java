package io.github.milkdrinkers.settlers.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
class AbstractInternalEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public AbstractInternalEvent() {
    }

    public AbstractInternalEvent(boolean async) {
        super(async);
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}

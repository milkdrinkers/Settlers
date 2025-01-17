package io.github.milkdrinkers.settlers.event.settler;

import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSettlerEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Settler settler;

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

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}

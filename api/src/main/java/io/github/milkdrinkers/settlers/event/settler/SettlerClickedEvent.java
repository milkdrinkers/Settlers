package io.github.milkdrinkers.settlers.event.settler;

import io.github.milkdrinkers.settlers.enums.ClickType;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SettlerClickedEvent extends SettlerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private ClickType clickType;
    private Player clicker;
    private Settler settler;
    private boolean cancelled;

    public SettlerClickedEvent(ClickType clickType, Player clicker, Settler settler) {
        this.clickType = clickType;
        this.clicker = clicker;
        this.settler = settler;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public void setClickType(ClickType clickType) {
        this.clickType = clickType;
    }

    public Player getClicker() {
        return clicker;
    }

    public Settler getSettler() {
        return settler;
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

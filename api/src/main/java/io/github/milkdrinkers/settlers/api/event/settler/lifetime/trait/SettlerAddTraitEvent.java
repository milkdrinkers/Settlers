package io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait;

import io.github.milkdrinkers.settlers.api.enums.ClickType;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class SettlerAddTraitEvent extends AbstractSettlerEvent implements Cancellable { // TODO Mirror citizens event
    private final ClickType clickType;
    private final Player clicker;
    private boolean cancelled;

    public SettlerAddTraitEvent(Settler settler, ClickType clickType, Player clicker) {
        super(settler);
        this.clickType = clickType;
        this.clicker = clicker;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public Player getClicker() {
        return clicker;
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

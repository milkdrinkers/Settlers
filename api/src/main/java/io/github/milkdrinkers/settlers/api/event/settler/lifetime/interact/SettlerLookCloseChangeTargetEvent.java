package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Player;

public class SettlerLookCloseChangeTargetEvent extends AbstractSettlerEvent {
    private final Player previousTarget;
    private Player newTarget;

    public SettlerLookCloseChangeTargetEvent(AbstractSettler settler, Player previousTarget, Player newTarget) {
        super(settler);
        this.previousTarget = previousTarget;
        this.newTarget = newTarget;
    }

    public Player getNewTarget() {
        return newTarget;
    }

    public void setNewTarget(Player newTarget) {
        this.newTarget = newTarget;
    }

    public Player getPreviousTarget() {
        return previousTarget;
    }
}

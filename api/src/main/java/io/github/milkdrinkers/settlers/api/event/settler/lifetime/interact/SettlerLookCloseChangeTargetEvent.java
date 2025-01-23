package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact;

import io.github.milkdrinkers.settlers.api.enums.DespawnReason;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class SettlerLookCloseChangeTargetEvent extends AbstractSettlerEvent { // TODO Mirror citizens event
    private final Player previousTarget;
    private Player newTarget;

    public SettlerLookCloseChangeTargetEvent(Settler settler, Player previousTarget, Player newTarget) {
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

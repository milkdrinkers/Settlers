package io.github.milkdrinkers.settlers.api.event.settler.lifetime;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;

public class SettlerSeenByPlayerEvent extends AbstractCancellableSettlerEvent {
    private final Player player;

    public SettlerSeenByPlayerEvent(Settler settler, Player player) {
        super(settler);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}

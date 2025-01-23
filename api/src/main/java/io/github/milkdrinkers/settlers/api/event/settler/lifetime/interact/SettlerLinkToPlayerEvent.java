package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;

public class SettlerLinkToPlayerEvent extends AbstractSettlerEvent { // TODO Mirror citizens event
    private final Player player;

    public SettlerLinkToPlayerEvent(Settler settler, Player player, boolean async) {
        super(settler, async);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}

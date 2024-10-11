package io.github.milkdrinkers.settlers.event.settler;

import org.bukkit.event.Event;

public abstract class SettlerEvent extends Event {

    protected SettlerEvent() {

    }

    protected SettlerEvent(boolean async) {
        super(async);
    }

}

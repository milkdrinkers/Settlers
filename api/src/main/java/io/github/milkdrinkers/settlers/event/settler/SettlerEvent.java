package io.github.milkdrinkers.settlers.event.settler;

import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.event.Event;

public abstract class SettlerEvent extends Event {

    private Settler settler;


    protected SettlerEvent(Settler settler) {
        this.settler = settler;
    }

    protected SettlerEvent(Settler settler, boolean async) {
        super(async);
        this.settler = settler;
    }

    public Settler getSettler() {
        return settler;
    }

}

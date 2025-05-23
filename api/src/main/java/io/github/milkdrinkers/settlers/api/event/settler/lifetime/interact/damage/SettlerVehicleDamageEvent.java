package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Entity;
import org.bukkit.event.vehicle.VehicleDamageEvent;

public class SettlerVehicleDamageEvent extends AbstractCancellableSettlerEvent {
    private final VehicleDamageEvent e;

    public SettlerVehicleDamageEvent(AbstractSettler settler, VehicleDamageEvent e) {
        super(settler);
        this.e = e;
    }

    public Entity getDamager() {
        return e.getAttacker();
    }

    public VehicleDamageEvent getEvent() {
        return e;
    }

}

package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Entity;
import org.bukkit.event.vehicle.VehicleDamageEvent;

public class SettlerVehicleDamageEvent extends AbstractCancellableSettlerEvent { // TODO Mirror citizens event
    private VehicleDamageEvent e;

    public SettlerVehicleDamageEvent(Settler settler, VehicleDamageEvent e) {
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

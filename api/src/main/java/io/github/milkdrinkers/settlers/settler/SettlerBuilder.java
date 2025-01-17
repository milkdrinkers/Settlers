package io.github.milkdrinkers.settlers.settler;

import io.github.milkdrinkers.settlers.enums.SettlerType;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;

import java.util.UUID;

public class SettlerBuilder {
    private NPC npc;
    private String name;
    private SettlerType type;
    private UUID uuid;
    private int id;
    private Location location;

    public SettlerBuilder setNpc(NPC npc) {
        this.npc = npc;
        return this;
    }

    public Settler createSettler() {
        return new Settler(npc);
    }



    public Companion createCompanion() {
        return new Companion(npc);
    }

    public Guard createGuard() {
        return new Guard(npc);
    }

    public Townfolk createTownfolk() {
        return new Townfolk(npc);
    }

    public Nationfolk createNationfolk() {
        return new Nationfolk(npc);
    }
}
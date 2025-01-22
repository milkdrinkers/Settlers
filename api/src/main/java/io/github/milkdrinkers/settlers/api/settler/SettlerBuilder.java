package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import io.github.milkdrinkers.settlers.api.exception.SettlerBuildException;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static org.bukkit.entity.EntityType.PLAYER;

public class SettlerBuilder {
    private NPC npc;
    private SettlerType type;

    // Creation fields
    private String name;
    private Location location;
    private UUID uuid;
    private Integer id;
    private boolean ephemeral = false;

    public SettlerBuilder setNpc(NPC npc) {
        this.npc = npc;
        return this;
    }

    public SettlerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SettlerBuilder setType(SettlerType type) {
        this.type = type;
        return this;
    }

    public SettlerBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public SettlerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public SettlerBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public SettlerBuilder setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        return this;
    }

    private void checkFields() throws SettlerBuildException {
//        throw new SettlerBuildException(Component.text("Failed to build new settler!"));
    }

    private NPC createNPC(NPCRegistry registry, @Nullable String name, @Nullable Location location, @Nullable Integer id, @Nullable UUID uuid) throws SettlerBuildException {
        if (name == null)
            throw new SettlerBuildException(Component.text("Failed to create new settler as name is null!"));

        if (uuid != null && id != null)
            return registry.createNPC(PLAYER, uuid, id, name);

        if (location != null)
            return registry.createNPC(PLAYER, name, location);

        return registry.createNPC(PLAYER, name);
    }

    private void register(SettlerType type) throws SettlerBuildException {
        if (npc != null)
            return;

        if (type == null)
            throw new SettlerBuildException(Component.text("Failed to create new settler, missing field type!"));

        switch (type) {
            case COMPANION -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryCompanion(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralCompanion(), name, location, id, uuid));
                }
            }
            case GUARD -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryGuard(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralGuard(), name, location, id, uuid));
                }
            }
            case NATION -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryTown(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralTown(), name, location, id, uuid));
                }
            }
            case TOWN -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryNation(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralNation(), name, location, id, uuid));
                }
            }
        }
    }

    /*public Settler create() throws SettlerBuildException {
        checkFields();

        return switch (type) {
            case COMPANION -> createCompanion();
            case GUARD -> createGuard();
            case NATION -> createNationfolk();
            case TOWN -> createTownfolk();
        };
    }*/

    public Companion createCompanion() throws SettlerBuildException {
        checkFields();
        register(type);

        return new Companion(npc);
    }

    public Guard createGuard() throws SettlerBuildException {
        checkFields();
        register(type);

        return new Guard(npc);
    }

    public Townfolk createTownfolk() throws SettlerBuildException {
        checkFields();
        register(type);

        return new Townfolk(npc);
    }

    public Nationfolk createNationfolk() throws SettlerBuildException {
        checkFields();
        register(type);

        return new Nationfolk(npc);
    }
}
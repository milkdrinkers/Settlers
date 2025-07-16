package io.github.milkdrinkers.settlers.trait.persistence.bukkit;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

public class WorldTypePersister implements Persister<World> {
    @Override
    public World create(DataKey dataKey) {
        final String worldName = dataKey.getString("name", "world");
        final String uuid = dataKey.getString("uuid", "");
        
        if (!uuid.isEmpty())
            return Bukkit.getWorld(UUID.fromString(uuid));
        return Bukkit.getWorld(worldName);
    }

    @Override
    public void save(World world, DataKey dataKey) {
        dataKey.setString("name", world.getName());
        dataKey.setString("uuid", world.getUID().toString());
    }
}
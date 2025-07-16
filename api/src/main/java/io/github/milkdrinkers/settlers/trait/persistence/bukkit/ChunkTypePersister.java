package io.github.milkdrinkers.settlers.trait.persistence.bukkit;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.Objects;

public class ChunkTypePersister implements Persister<Chunk> {
    @Override
    public Chunk create(DataKey dataKey) {
        final String worldName = dataKey.getString("world", "world");
        final int x = dataKey.getInt("x", 0);
        final int z = dataKey.getInt("z", 0);

        final World world = Bukkit.getWorld(worldName);
        if (world == null)
            return null;

        return world.getChunkAt(x, z);
    }

    @Override
    public void save(Chunk chunk, DataKey dataKey) {
        dataKey.setString("world", chunk.getWorld().getName());
        dataKey.setInt("x", chunk.getX());
        dataKey.setInt("z", chunk.getZ());
    }
}
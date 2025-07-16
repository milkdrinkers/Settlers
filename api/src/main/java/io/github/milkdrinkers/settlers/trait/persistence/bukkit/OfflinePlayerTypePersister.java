package io.github.milkdrinkers.settlers.trait.persistence.bukkit;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class OfflinePlayerTypePersister implements Persister<OfflinePlayer> {
    @Override
    public OfflinePlayer create(DataKey dataKey) {
        final String uuidString = dataKey.getString("uuid", "");

        if (!uuidString.isEmpty())
            return Bukkit.getOfflinePlayer(UUID.fromString(uuidString));

        return null;
    }

    @Override
    public void save(OfflinePlayer offlinePlayer, DataKey dataKey) {
        dataKey.setString("uuid", offlinePlayer.getUniqueId().toString());
    }
}
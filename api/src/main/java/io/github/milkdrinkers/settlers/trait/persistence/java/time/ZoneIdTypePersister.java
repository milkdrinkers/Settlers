package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.ZoneId;

public class ZoneIdTypePersister implements Persister<ZoneId> {
    @Override
    public ZoneId create(DataKey dataKey) {
        return ZoneId.of(dataKey.getString("", "UTC"));
    }

    @Override
    public void save(ZoneId zoneId, DataKey dataKey) {
        dataKey.setString("", zoneId.getId());
    }
}
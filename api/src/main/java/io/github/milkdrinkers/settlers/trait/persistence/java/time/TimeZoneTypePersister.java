package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.util.TimeZone;

public class TimeZoneTypePersister implements Persister<TimeZone> {
    @Override
    public TimeZone create(DataKey dataKey) {
        return TimeZone.getTimeZone(dataKey.getString("", "UTC"));
    }

    @Override
    public void save(TimeZone timeZone, DataKey dataKey) {
        dataKey.setString("", timeZone.getID());
    }
}
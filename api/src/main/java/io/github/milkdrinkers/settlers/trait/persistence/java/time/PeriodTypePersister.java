package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.Period;

public class PeriodTypePersister implements Persister<Period> {
    @Override
    public Period create(DataKey dataKey) {
        final int years = dataKey.getInt("years", 0);
        final int months = dataKey.getInt("months", 0);
        final int days = dataKey.getInt("days", 0);
        return Period.of(years, months, days);
    }

    @Override
    public void save(Period period, DataKey dataKey) {
        dataKey.setInt("years", period.getYears());
        dataKey.setInt("months", period.getMonths());
        dataKey.setInt("days", period.getDays());
    }
}
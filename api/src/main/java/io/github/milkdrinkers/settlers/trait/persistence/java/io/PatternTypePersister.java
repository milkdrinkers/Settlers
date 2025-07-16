package io.github.milkdrinkers.settlers.trait.persistence.java.io;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.util.regex.Pattern;

public class PatternTypePersister implements Persister<Pattern> {
    @Override
    public Pattern create(DataKey dataKey) {
        final String pattern = dataKey.getString("pattern", "");
        final int flags = dataKey.getInt("flags", 0);
        //noinspection MagicConstant
        return Pattern.compile(pattern, flags);
    }

    @Override
    public void save(Pattern pattern, DataKey dataKey) {
        dataKey.setString("pattern", pattern.pattern());
        dataKey.setInt("flags", pattern.flags());
    }
}
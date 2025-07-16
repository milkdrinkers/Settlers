package io.github.milkdrinkers.settlers.trait.persistence.java.io;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.util.Locale;

public class LocaleTypePersister implements Persister<Locale> {
    @Override
    public Locale create(DataKey dataKey) {
        String language = dataKey.getString("language", "en");
        String country = dataKey.getString("country", "");
        String variant = dataKey.getString("variant", "");
        
        if (variant.isEmpty() && country.isEmpty()) {
            return new Locale(language);
        } else if (variant.isEmpty()) {
            return new Locale(language, country);
        } else {
            return new Locale(language, country, variant);
        }
    }

    @Override
    public void save(Locale locale, DataKey dataKey) {
        dataKey.setString("language", locale.getLanguage());
        dataKey.setString("country", locale.getCountry());
        dataKey.setString("variant", locale.getVariant());
    }
}
package io.github.milkdrinkers.settlers.trait.persistence;

import io.github.milkdrinkers.settlers.trait.persistence.adventure.ComponentTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.adventure.KeyTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.bukkit.ChunkTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.bukkit.MaterialTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.bukkit.OfflinePlayerTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.bukkit.WorldTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.io.*;
import io.github.milkdrinkers.settlers.trait.persistence.java.number.BigDecimalTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.number.BigIntegerTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.DurationTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.InstantTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.LocalDateTimeTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.LocalDateTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.LocalTimeTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.OffsetDateTimeTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.PeriodTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.TimeZoneTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.ZonedDateTimeTypePersister;
import io.github.milkdrinkers.settlers.trait.persistence.java.time.ZoneIdTypePersister;
import net.citizensnpcs.api.persistence.PersistenceLoader;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.*;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

// Adventure imports
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;

// Bukkit imports
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

/**
 * Registers type persisters with the citizens api.
 */
public class PersistenceHandler {
    static {
        // Adventure persisters
        PersistenceLoader.registerPersistDelegate(Component.class, ComponentTypePersister.class);
        PersistenceLoader.registerPersistDelegate(Key.class, KeyTypePersister.class);

        // Bukkit persisters
        PersistenceLoader.registerPersistDelegate(Chunk.class, ChunkTypePersister.class);
        PersistenceLoader.registerPersistDelegate(Material.class, MaterialTypePersister.class);
        PersistenceLoader.registerPersistDelegate(OfflinePlayer.class, OfflinePlayerTypePersister.class);
        PersistenceLoader.registerPersistDelegate(World.class, WorldTypePersister.class);

        // Java IO persisters
        PersistenceLoader.registerPersistDelegate(File.class, FileTypePersister.class);

        // Java Locale persisters
        PersistenceLoader.registerPersistDelegate(Locale.class, LocaleTypePersister.class);

        // Java NIO persisters
        PersistenceLoader.registerPersistDelegate(Path.class, PathTypePersister.class);

        // Java Util persisters
        PersistenceLoader.registerPersistDelegate(Pattern.class, PatternTypePersister.class);
        PersistenceLoader.registerPersistDelegate(URI.class, URITypePersister.class);
        PersistenceLoader.registerPersistDelegate(URL.class, URLTypePersister.class);

        // Java Math persisters
        PersistenceLoader.registerPersistDelegate(BigDecimal.class, BigDecimalTypePersister.class);
        PersistenceLoader.registerPersistDelegate(BigInteger.class, BigIntegerTypePersister.class);

        // Java Time persisters
        PersistenceLoader.registerPersistDelegate(Duration.class, DurationTypePersister.class);
        PersistenceLoader.registerPersistDelegate(Instant.class, InstantTypePersister.class);
        PersistenceLoader.registerPersistDelegate(LocalDateTime.class, LocalDateTimeTypePersister.class);
        PersistenceLoader.registerPersistDelegate(LocalDate.class, LocalDateTypePersister.class);
        PersistenceLoader.registerPersistDelegate(LocalTime.class, LocalTimeTypePersister.class);
        PersistenceLoader.registerPersistDelegate(OffsetDateTime.class, OffsetDateTimeTypePersister.class);
        PersistenceLoader.registerPersistDelegate(Period.class, PeriodTypePersister.class);
        PersistenceLoader.registerPersistDelegate(TimeZone.class, TimeZoneTypePersister.class);
        PersistenceLoader.registerPersistDelegate(ZonedDateTime.class, ZonedDateTimeTypePersister.class);
        PersistenceLoader.registerPersistDelegate(ZoneId.class, ZoneIdTypePersister.class);
    }
}

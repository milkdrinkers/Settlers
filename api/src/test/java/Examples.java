import io.github.milkdrinkers.settlers.api.settler.Companion;
import io.github.milkdrinkers.settlers.api.settler.SettlerBuilder;

public class Examples {
    Examples() {
        Companion companion = new SettlerBuilder()
            .setName("Herbert")
            .setEphemeral(true)
            .createCompanion();

    }
}

package h01;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import static h01.H2_Test.*;

public final class ListProviders {

    private static final Function<Random, ListItem<Double>> SUBLIST_GENERATOR = random -> ListItemUtils.of(
        random.doubles(MAX_SUBLIST_SIZE, 0d, DOUBLE_LIMIT)
            .boxed()
            .map(d -> (double) d.intValue())
            .toArray(Double[]::new)
    );

    private ListProviders() {}

    /**
     * Provides {@code null} as a parameter.
     */
    public static final class EmptyMainListProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(ListItemUtils.of()));
        }
    }

    /**
     * Provides a main list with a single empty sublist.
     */
    public static final class EmptySingleSubListProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(ListItemUtils.of((Object) null)));
        }
    }

    /**
     * Provides a main list with multiple (3) empty sublists.
     */
    public static final class EmptyMultipleSubListProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(ListItemUtils.of(null, null, null)));
        }
    }

    /**
     * Provides main lists with a single populated sublist.
     */
    public static final class SingleSubListProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Random random = new Random(SEED);
            return Stream.generate(() -> ListItemUtils.of(SUBLIST_GENERATOR.apply(random)))
                .map(Arguments::of)
                .limit(METHOD_CALLS);
        }
    }

    /**
     * Provides main lists with multiple populated sublists.
     */
    public static final class MultipleSubListProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Random random = new Random(SEED);
            return Stream.generate(() -> Stream.generate(() -> ListItemUtils.of(SUBLIST_GENERATOR.apply(random)))
                    .limit(random.nextInt(1, MAX_MAIN_LIST_SIZE))
                    .reduce(ListItemUtils::append)
                    .orElseThrow())
                .map(Arguments::of)
                .limit(METHOD_CALLS);
        }
    }
}

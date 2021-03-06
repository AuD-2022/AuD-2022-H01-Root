package h01.h3_2;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.function.Supplier;

import static h01.H2_Test.SIMPLE_STRING_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestForSubmission("h01")
public class ReaderTests {

    @BeforeEach
    public void resetInvocations() {
        MethodInterceptor.reset();
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMainListProvider.class)
    public void testWithEmptyMainList(ListItem<ListItem<Double>> listOfLists) throws IOException {
        testLists(listOfLists);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMultipleSubListProvider.class)
    public void testWithMultipleEmptySublists(ListItem<ListItem<Double>> listOfLists) throws IOException {
        testLists(listOfLists);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.SingleSubListProvider.class)
    public void testWithSingleSublist(ListItem<ListItem<Double>> listOfLists) throws IOException {
        testLists(listOfLists);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.MultipleSubListProvider.class)
    public void testWithMultipleSublist(ListItem<ListItem<Double>> listOfLists) throws IOException {
        testLists(listOfLists);
    }

    private static void testLists(ListItem<ListItem<Double>> listOfLists) throws IOException {
        StringWriter stringWriterExpected = new StringWriter();
        Tutor_Processor.write(stringWriterExpected, ListItemUtils.deepCopy(listOfLists));
        String stringExcepted = stringWriterExpected.toString();
        Supplier<BufferedReader> readerSupplier = () -> new BufferedReader(new StringReader(stringExcepted));
        ListItem<ListItem<Double>> expected = Tutor_Processor.read(readerSupplier.get());
        ListItem<ListItem<Double>> actual = DoubleListOfListsProcessor.read(readerSupplier.get());

        assertFalse(ListItemUtils.isCyclic(actual), "Returned list is cyclic but shouldn't be");
        try {
            assertEquals(
                SIMPLE_STRING_FORMAT
                    ? ListItemUtils.toSimpleString(expected)
                    : ListItemUtils.toString(expected),
                SIMPLE_STRING_FORMAT
                    ? ListItemUtils.toSimpleString(actual)
                    : ListItemUtils.toString(actual)
            );
        } catch (RuntimeException e) {
            if (e.getMessage().matches("^H3\\.\\d - not implemented$")) {
                throw new AssertionFailedError(e.getMessage().replace('-', '|'));
            } else {
                throw e;
            }
        }
    }
}

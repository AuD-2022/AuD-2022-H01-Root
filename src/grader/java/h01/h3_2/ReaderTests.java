package h01.h3_2;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.launcher.env.Jagr;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.function.Supplier;

import static h01.H2_Test.SIMPLE_STRING_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestForSubmission("h01")
public class ReaderTests {

    @BeforeEach
    public void resetInvocations() {
        MethodInterceptor.reset();
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.SingleSubListProvider.class)
    public void testWithSingleSublist(ListItem<ListItem<Double>> listOfLists) {
        testLists(listOfLists);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.MultipleSubListProvider.class)
    public void testWithMultipleSublist(ListItem<ListItem<Double>> listOfLists) {
        testLists(listOfLists);
    }

    private static void testLists(ListItem<ListItem<Double>> listOfLists) {
        StringWriter stringWriterExpected = new StringWriter();
        Tutor_DoubleListOfListsProcessor.write(stringWriterExpected, ListItemUtils.deepCopy(listOfLists));
        String stringExcepted = stringWriterExpected.toString();
        Supplier<BufferedReader> readerSupplier = () -> new BufferedReader(new StringReader(stringExcepted));

        assertEquals(
            SIMPLE_STRING_FORMAT
                ? ListItemUtils.toSimpleString(Tutor_DoubleListOfListsProcessor.read(readerSupplier.get()))
                : ListItemUtils.toString(Tutor_DoubleListOfListsProcessor.read(readerSupplier.get())),
            SIMPLE_STRING_FORMAT
                ? ListItemUtils.toSimpleString(DoubleListOfListsProcessor.read(readerSupplier.get()))
                : ListItemUtils.toString(DoubleListOfListsProcessor.read(readerSupplier.get()))
        );
    }
}

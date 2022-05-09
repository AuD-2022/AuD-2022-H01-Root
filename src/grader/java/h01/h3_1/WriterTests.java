package h01.h3_1;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterTests {

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
        StringWriter stringWriterActual = new StringWriter();
        Tutor_DoubleListOfListsProcessor.write(stringWriterExpected, ListItemUtils.deepCopy(listOfLists));
        DoubleListOfListsProcessor.write(stringWriterActual, ListItemUtils.deepCopy(listOfLists));
        String stringExcepted = stringWriterExpected.toString();
        String stringActual = stringWriterActual.toString();
        int expectedNumberOfLinebreaks = (int) stringExcepted.chars().filter(i -> i == '\n').count();
        String[] linesExpected = stringExcepted.split("\n");
        String[] linesActual = stringActual.split("\n");

        assertEquals(expectedNumberOfLinebreaks, stringActual.chars().filter(i -> i == '\n').count(),
            "Amount of linebreaks do not match in the string that was written out to the writer");
        for (int i = 0; i < linesExpected.length; i++) {
            assertEquals(linesExpected[i], linesActual[i], "Mismatch for line %d in output string".formatted(i));
        }
    }
}

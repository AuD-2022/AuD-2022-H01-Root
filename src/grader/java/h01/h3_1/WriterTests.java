package h01.h3_1;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestForSubmission("h01")
public class WriterTests {

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
        StringWriter stringWriterActual = new StringWriter();
        Tutor_Processor.write(stringWriterExpected, ListItemUtils.deepCopy(listOfLists));
        try {
            DoubleListOfListsProcessor.write(stringWriterActual, ListItemUtils.deepCopy(listOfLists));
        } catch (RuntimeException e) {
            if (e.getMessage().matches("^H3\\.\\d - not implemented$")) {
                throw new AssertionFailedError(e.getMessage().replace('-', '|'));
            } else {
                throw e;
            }
        }
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

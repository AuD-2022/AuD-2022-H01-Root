package h01.h3_2;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertTrue(ListItemUtils.deepEquals(listOfLists,
            DoubleListOfListsProcessor.read(new BufferedReader(new StringReader(stringExcepted)))));
    }
}

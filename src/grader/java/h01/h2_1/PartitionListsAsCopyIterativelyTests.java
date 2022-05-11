package h01.h2_1;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h01.H2_Test.PARTITION_LIMIT;
import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h01")
public class PartitionListsAsCopyIterativelyTests {

    private static final String FULLY_QUALIFIED_METHOD_NAME = "h01.DoubleListOfListsProcessor#"
        + "partitionListsAsCopyIteratively(ListItem, double)";

    @BeforeEach
    public void resetInvocations() {
        MethodInterceptor.reset();
    }

    @AfterEach
    public void checkIllegalCalls() {
        boolean recursiveInvocationMatch = MethodInterceptor.getInvocations()
            .stream()
            .anyMatch(invocation -> invocation.signature()
                .equals("h01/DoubleListOfListsProcessor partitionListsAsCopyIteratively(Lh01/ListItem;D)Lh01/ListItem;"));
        String illegalMethod = MethodInterceptor.getInvocations()
            .stream()
            .map(MethodInterceptor.Invocation::signature)
            .filter(signature -> !signature.matches("h01/.*"))
            .findAny()
            .orElse(null);

        assertFalse(recursiveInvocationMatch, "Method called itself recursively");
        assertTrue(illegalMethod == null || illegalMethod.equals("org/sourcegrade/jagr/core/executor/TimeoutHandler checkTimeout()V"),
            "Method called an illegal method: " + illegalMethod);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMainListProvider.class)
    public void testWithEmptyMainList(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyIteratively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptySingleSubListProvider.class)
    public void testWithEmptySingleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyIteratively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMultipleSubListProvider.class)
    public void testWithEmptyMultipleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyIteratively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.SingleSubListProvider.class)
    public void testWithSingleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyIteratively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.MultipleSubListProvider.class)
    public void testWithMultipleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyIteratively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.InvalidListProvider.class)
    public void testInvalidList(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.testException(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyIteratively);
    }
}

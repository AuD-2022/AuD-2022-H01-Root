package h01;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;

import static h01.ListTest.assertListsEquals;
import static h01.ListTest.toList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublicTests {

    private final double limit = 6.0;
    private final ListItem<ListItem<Double>> expectedRepartitionedList =
        toList(
            toList(1.0, 3.0),
            toList(5.0),
            toList(1.0, 2.0, 3.0),
            toList(4.0),
            toList(5.0));
    private final ListItem<ListItem<Double>> listForReadWrite = toList(
        toList(1.0, 2.0),
        toList(1.0, 2.0, 3.0));
    private final String contentForReadWrite = "1.0#2.0\n1.0#2.0#3.0";
    private ListItem<ListItem<Double>> listToPartition;

    @BeforeEach
    void setup() {
        listToPartition = toList(toList(1.0, 3.0, 5.0), toList(1.0, 2.0, 3.0, 4.0, 5.0));
    }

    @Test
    void testPartitionListsAsCopyIteratively() {
        var actual = DoubleListOfListsProcessor.partitionListsAsCopyIteratively(listToPartition, limit);
        assertListsEquals(expectedRepartitionedList, actual);
    }

    @Test
    void testPartitionListsInPlaceIteratively() {
        var actual = DoubleListOfListsProcessor.partitionListsInPlaceIteratively(listToPartition, limit);
        assertListsEquals(expectedRepartitionedList, actual);
    }

    @Test
    void testPartitionListsAsCopyRecursively() {
        var actual = DoubleListOfListsProcessor.partitionListsAsCopyRecursively(listToPartition, limit);
        assertListsEquals(expectedRepartitionedList, actual);
    }

    @Test
    void testPartitionListsInPlaceRecursively() {
        var actual = DoubleListOfListsProcessor.partitionListsInPlaceRecursively(listToPartition, limit);
        assertListsEquals(expectedRepartitionedList, actual);
    }

    @Test
    void testWrite() {
        var writer = new StringWriter();
        DoubleListOfListsProcessor.write(writer, listForReadWrite);
        var actual = writer.toString();
        assertEquals(contentForReadWrite.replace("\n", "\\n"), actual.replace("\n", "\\n"));
    }

    @Test
    void testRead() {
        var reader = new BufferedReader(new StringReader(contentForReadWrite));
        var actual = DoubleListOfListsProcessor.read(reader);
        assertListsEquals(listForReadWrite, actual);
    }
}

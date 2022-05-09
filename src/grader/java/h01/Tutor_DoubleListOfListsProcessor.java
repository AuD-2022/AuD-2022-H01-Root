package h01;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

public class Tutor_DoubleListOfListsProcessor {

    /**
     * Writes out {@code listOfLists} (well, the sub-lists) to {@code writer}.
     *
     * @param writer      the writer to write to
     * @param listOfLists the list of lists to write out
     */
    public static void write(Writer writer, @Nullable ListItem<@Nullable ListItem<Double>> listOfLists) {
        try {
            for (var currOuter = listOfLists; currOuter != null; currOuter = currOuter.next) {
                if (currOuter.key == null) {
                    writer.write("#");
                }
                for (var currInner = currOuter.key; currInner != null; currInner = currInner.next) {
                    writer.write(currInner.key + (currInner.next != null ? "#" : ""));
                }
                if (currOuter.next != null) {
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a list of lists from {@code reader} and returns it.
     *
     * @param reader the reader to read from
     * @return a list of lists of double
     */
    public static @Nullable ListItem<@Nullable ListItem<Double>> read(BufferedReader reader) {
        var outerHead = (ListItem<ListItem<Double>>) null;
        var outerTail = (ListItem<ListItem<Double>>) null;
        try {
            var br = new BufferedReader(reader);
            var str = (String) null;
            while ((str = br.readLine()) != null) {
                var innerHead = (ListItem<Double>) null;
                var innerTail = (ListItem<Double>) null;
                for (String d : str.split("#")) {
                    if (d.isEmpty())
                        continue;
                    if (innerHead == null) {
                        innerHead = innerTail = new ListItem<>();
                    } else {
                        innerTail = innerTail.next = new ListItem<>();
                    }
                    innerTail.key = Double.valueOf(d);
                }
                if (outerHead == null) {
                    outerHead = outerTail = new ListItem<>();
                } else {
                    outerTail = outerTail.next = new ListItem<>();
                }
                outerTail.key = innerHead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outerHead;
    }
}

package h01;

import h01.h2_1.PartitionListsAsCopyIterativelyTests;
import h01.h2_2.PartitionListsAsCopyRecursivelyTests;
import h01.h2_3.PartitionListsInPlaceIterativelyTests;
import h01.h2_4.PartitionListsInPlaceRecursivelyTests;
import h01.h3_1.WriterTests;
import h01.h3_2.ReaderTests;
import h01.utils.BytecodeTransformations;
import org.sourcegrade.jagr.api.rubric.*;
import org.sourcegrade.jagr.api.testing.RubricConfiguration;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

@RubricForSubmission("h01")
public class H01_RubricProvider implements RubricProvider {

    private static final BiFunction<String, Callable<Method>, Criterion> DEFAULT_CRITERION = (s, methodCallable) ->
        Criterion.builder()
            .shortDescription(s)
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(methodCallable))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
            .build();
    private static final BiFunction<String, Callable<Method>, Criterion> TWO_POINTS_CRITERION = (s, methodCallable) ->
        Criterion.builder()
            .shortDescription(s)
            .maxPoints(2)
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(methodCallable))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
            .build();

    private static final Criterion CRITERION_H2_1 = Criterion.builder()
        .shortDescription("H2.1 - Einzellisten as-copy iterativ zerlegen")
        .addChildCriteria(
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit leerer Hauptliste als Eingabe",
                () -> PartitionListsAsCopyIterativelyTests.class.getDeclaredMethod("testWithEmptyMainList",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer leeren Einzelliste",
                () -> PartitionListsAsCopyIterativelyTests.class.getDeclaredMethod("testWithEmptySingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren leeren Einzellisten",
                () -> PartitionListsAsCopyIterativelyTests.class.getDeclaredMethod("testWithEmptyMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer normalen Einzelliste",
                () -> PartitionListsAsCopyIterativelyTests.class.getDeclaredMethod("testWithSingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren normalen Einzellisten",
                () -> PartitionListsAsCopyIterativelyTests.class.getDeclaredMethod("testWithMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode wirft die korrekte Exception wenn eine Zahl größer als das Limit ist",
                () -> PartitionListsAsCopyIterativelyTests.class.getDeclaredMethod("testInvalidList", ListItem.class))
        ).build();
    private static final Criterion CRITERION_H2_2 = Criterion.builder()
        .shortDescription("H2.2 - Einzellisten as-copy rekursiv zerlegen")
        .addChildCriteria(
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit leerer Hauptliste als Eingabe",
                () -> PartitionListsAsCopyRecursivelyTests.class.getDeclaredMethod("testWithEmptyMainList",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer leeren Einzelliste",
                () -> PartitionListsAsCopyRecursivelyTests.class.getDeclaredMethod("testWithEmptySingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren leeren Einzellisten",
                () -> PartitionListsAsCopyRecursivelyTests.class.getDeclaredMethod("testWithEmptyMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer normalen Einzelliste",
                () -> PartitionListsAsCopyRecursivelyTests.class.getDeclaredMethod("testWithSingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren normalen Einzellisten",
                () -> PartitionListsAsCopyRecursivelyTests.class.getDeclaredMethod("testWithMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode wirft die korrekte Exception wenn eine Zahl größer als das Limit ist",
                () -> PartitionListsAsCopyRecursivelyTests.class.getDeclaredMethod("testInvalidList", ListItem.class))
        ).build();
    private static final Criterion CRITERION_H2_3 = Criterion.builder()
        .shortDescription("H2.3 - Einzellisten in-place iterativ zerlegen")
        .addChildCriteria(
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit leerer Hauptliste als Eingabe",
                () -> PartitionListsInPlaceIterativelyTests.class.getDeclaredMethod("testWithEmptyMainList",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer leeren Einzelliste",
                () -> PartitionListsInPlaceIterativelyTests.class.getDeclaredMethod("testWithEmptySingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren leeren Einzellisten",
                () -> PartitionListsInPlaceIterativelyTests.class.getDeclaredMethod("testWithEmptyMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer normalen Einzelliste",
                () -> PartitionListsInPlaceIterativelyTests.class.getDeclaredMethod("testWithSingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren normalen Einzellisten",
                () -> PartitionListsInPlaceIterativelyTests.class.getDeclaredMethod("testWithMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode wirft die korrekte Exception wenn eine Zahl größer als das Limit ist",
                () -> PartitionListsInPlaceIterativelyTests.class.getDeclaredMethod("testInvalidList", ListItem.class))
        ).build();
    private static final Criterion CRITERION_H2_4 = Criterion.builder()
        .shortDescription("H2.4 - Einzellisten in-place rekursiv zerlegen")
        .addChildCriteria(
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit leerer Hauptliste als Eingabe",
                () -> PartitionListsInPlaceRecursivelyTests.class.getDeclaredMethod("testWithEmptyMainList",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer leeren Einzelliste",
                () -> PartitionListsInPlaceRecursivelyTests.class.getDeclaredMethod("testWithEmptySingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren leeren Einzellisten",
                () -> PartitionListsInPlaceRecursivelyTests.class.getDeclaredMethod("testWithEmptyMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit einer normalen Einzelliste",
                () -> PartitionListsInPlaceRecursivelyTests.class.getDeclaredMethod("testWithSingleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode funktioniert korrekt mit mehreren normalen Einzellisten",
                () -> PartitionListsInPlaceRecursivelyTests.class.getDeclaredMethod("testWithMultipleSubListProvider",
                    ListItem.class)),
            DEFAULT_CRITERION.apply("Methode wirft die korrekte Exception wenn eine Zahl größer als das Limit ist",
                () -> PartitionListsInPlaceRecursivelyTests.class.getDeclaredMethod("testInvalidList", ListItem.class))
        ).build();

    private static final Criterion CRITERION_H3_1 = Criterion.builder()
        .shortDescription("H3.1 - Liste von Listen auf Textdatensenke hinausschreiben")
        .addChildCriteria(
            TWO_POINTS_CRITERION.apply("Methode funktioniert korrekt mit einer normalen Einzelliste",
                () -> WriterTests.class.getDeclaredMethod("testWithSingleSublist", ListItem.class)),
            TWO_POINTS_CRITERION.apply("Methode funktioniert korrekt mit mehreren normalen Einzellisten",
                () -> WriterTests.class.getDeclaredMethod("testWithMultipleSublist", ListItem.class))
        )
        .build();

    private static final Criterion CRITERION_H3_2 = Criterion.builder()
        .shortDescription("H3.2 - Liste von Listen aus Datei einlesen")
        .addChildCriteria(
            TWO_POINTS_CRITERION.apply("Methode funktioniert korrekt mit einer normalen Einzelliste",
                () -> ReaderTests.class.getDeclaredMethod("testWithSingleSublist", ListItem.class)),
            TWO_POINTS_CRITERION.apply("Methode funktioniert korrekt mit mehreren normalen Einzellisten",
                () -> ReaderTests.class.getDeclaredMethod("testWithMultipleSublist", ListItem.class))
        )
        .build();

    private static final Criterion CRITERION_H2 = Criterion.builder()
        .shortDescription("H2 - Zerlegen von Einzellisten")
        .addChildCriteria(CRITERION_H2_1, CRITERION_H2_2, CRITERION_H2_3, CRITERION_H2_4)
        .build();

    private static final Criterion CRITERION_H3 = Criterion.builder()
        .shortDescription("H3 - IO-Operationen")
        .addChildCriteria(CRITERION_H3_1, CRITERION_H3_2)
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H01")
        .addChildCriteria(CRITERION_H2, CRITERION_H3)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

    @Override
    public void configure(RubricConfiguration configuration) {
        configuration.addTransformer(new BytecodeTransformations());
    }
}

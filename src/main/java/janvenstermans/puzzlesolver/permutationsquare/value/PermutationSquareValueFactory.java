package janvenstermans.puzzlesolver.permutationsquare.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Factory methods for some well used {@link PermutationSquareValue}.
 * @author Jan Venstermans
 */
public class PermutationSquareValueFactory {

    public static final List<IntegerPermutationSquareValue> createIntegerListForDimension(int dimension) {
        if (dimension < 1) {
            throw new IllegalArgumentException("dimension must be larger than 0");
        }
        List<IntegerPermutationSquareValue> permutationSquareValueList = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            permutationSquareValueList.add(createIntegerPermutationSquareValue(i + 1));
        }
        return createIntegerListForAllButIntegers(dimension);
    }

    public static final List<IntegerPermutationSquareValue> createIntegerListForAllButIntegers(int dimension, int ... values) {
        if (dimension < 1) {
            throw new IllegalArgumentException("dimension must be larger than 0");
        }
        List<Integer> possibleValues = new ArrayList<>();
        for (int i = 1; i <= dimension; i++) {
            possibleValues.add(i);
        }
        if (values != null) {
            possibleValues.removeAll(Arrays.asList(values));
        }
        List<IntegerPermutationSquareValue> permutationSquareValueList = new ArrayList<>();
        for (int value : possibleValues) {
            permutationSquareValueList.add(createIntegerPermutationSquareValue(value));
        }
        return permutationSquareValueList;
    }

    public static final IntegerPermutationSquareValue createIntegerPermutationSquareValue(int integer) {
        return new IntegerPermutationSquareValue(integer);
    }

    public static List<IntegerPermutationSquareValue> createIntegerValueList(List<Integer> integerList) {
        List<IntegerPermutationSquareValue> valueList = new ArrayList<>();
        for (Integer integer : integerList) {
            valueList.add(PermutationSquareValueFactory.createIntegerPermutationSquareValue(integer));
        }
        return valueList;
    }
}

package janvenstermans.puzzlesolver.permutationsquare.value;

import janvenstermans.puzzlesolver.permutationsquare.PermutationSquareCellInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory methods for some well used {@link PermutationSquareValue}.
 * @author Jan Venstermans
 */
public class PermutationSquareValueFactory {

    public static final List<IntegerPermutationSquareValue> createIntegerList(int dimension) {
        if (dimension < 1) {
            throw new IllegalArgumentException("dimension must be larger than 0");
        }
        List<IntegerPermutationSquareValue> permutationSquareValueList = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            permutationSquareValueList.add(createIntegerPermutationSquareValue(i));
        }
        return permutationSquareValueList;
    }

    public static final IntegerPermutationSquareValue createIntegerPermutationSquareValue(int integer) {
        return new IntegerPermutationSquareValue(integer);
    }
}

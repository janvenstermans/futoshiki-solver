package janvenstermans.puzzlesolver.permutationsquare.value;

/**
 * Integer implementation of
 * @author Jan Venstermans
 */
public class IntegerPermutationSquareValue implements PermutationSquareValue {

    private final int value;

    public IntegerPermutationSquareValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerPermutationSquareValue that = (IntegerPermutationSquareValue) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}

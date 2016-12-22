package janvenstermans.puzzlesolver.permutationsquare;

/**
 * Contains a {@link PermutationSquareValue}. Object representing a cell in the grid.
 * This object is also shared by the
 *
 * @author Jan Venstermans
 */
public class PermutationSquareValueContainer<PermutationSquareValue> {

    private final PermutationSquareValue value;

    public PermutationSquareValueContainer(PermutationSquareValue value) {
        this.value = value;
    }

    public PermutationSquareValue getValue() {
        return value;
    }

    public void checkValue(PermutationSquareValue value) {
        if (!this.value.equals(value)) {
            throw new IllegalArgumentException(String.format("Current value %s cannot be set to %s", this.value, value));
        }
    }
}

package janvenstermans.puzzlesolver.permutationsquare;

/**
 * Contains info of a cell in the {@link PermutationSquare}.
 * @author Jan Venstermans
 */
public class PermutationSquareCellInfo<PermutationSquareValue>  {

    private final int columnIndex;
    private final int rowIndex;
    private PermutationSquareValue value;

    public PermutationSquareCellInfo(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    /**
     *
     * @param value
     * @return has a new value been set?
     */
    public boolean setValue(PermutationSquareValue value) {
        if (this.value == null) {
            this.value = value;
            return true;
        } else if (this.value.equals(value)) {
            throw new IllegalArgumentException(String.format("Value %s cannot be overwritten to %s", this.value, value));
        }
        return false;
    }

    public PermutationSquareValue getValue() {
        return value;
    }
}

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
        this(columnIndex, rowIndex, null);
    }
    public PermutationSquareCellInfo(int columnIndex, int rowIndex, PermutationSquareValue value) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        setValue(value);
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
        boolean isValue = value != null;
        if (this.value == null) {
            if (!isValue) {
                return false;
            } else {
                this.value = value;
                return true;
            }
        }
        if (!isValue) {
            throw new IllegalArgumentException(String.format("Value %s cannot set to null.", this.value, value));
        }
        if (!this.value.equals(value)) {
            throw new IllegalArgumentException(String.format("Value %s cannot be overwritten to %s", this.value, value));
        }
        return false;
    }

    public PermutationSquareValue getValue() {
        return value;
    }
}

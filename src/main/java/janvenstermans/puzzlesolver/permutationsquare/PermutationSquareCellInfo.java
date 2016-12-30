package janvenstermans.puzzlesolver.permutationsquare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains info of a cell in the {@link PermutationSquare}.
 * @author Jan Venstermans
 */
public class PermutationSquareCellInfo<PermutationSquareValue>  {

    private final int columnIndex;
    private final int rowIndex;
    private PermutationSquareValue value;
    private List<PermutationSquareValue> possibleValueList = new ArrayList<PermutationSquareValue>();

    public PermutationSquareCellInfo(int columnIndex, int rowIndex, List<PermutationSquareValue> possibleValueList) {
        this(columnIndex, rowIndex, possibleValueList, null);
    }

    public PermutationSquareCellInfo(int columnIndex, int rowIndex, List<PermutationSquareValue> possibleValueList, PermutationSquareValue value) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.possibleValueList.addAll(possibleValueList);
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
            } else if (possibleValueList.contains(value)) {
                this.value = value;
                possibleValueList.clear();
                possibleValueList.add(this.value);
                return true;
            } else {
                throw new IllegalArgumentException(String.format("Value %s cannot be set, is not in possibleValueList %s.", value, possibleValueList));
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

    public List<PermutationSquareValue> getPossibleValues() {
        return Collections.unmodifiableList(possibleValueList);
    }
}

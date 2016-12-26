package janvenstermans.puzzlesolver.permutationsquare;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Venstermans
 */
public class PermutationSquareImpl<PermutationSquareValue> implements PermutationSquare<PermutationSquareValue>  {

    private final List<PermutationSquareValue> possibleValueList;

    private final PermutationSquareCellInfo<PermutationSquareValue>[][] valueContainersArray;
    private final PermutationSquareLineInfo[] columnArray;
    private final PermutationSquareLineInfo[] rowArray;

    private List<PermutationSquareValueChangedListener<PermutationSquareValue>> valueChangedListenerList = new ArrayList<>();

    public PermutationSquareImpl(List<PermutationSquareValue> possibleValueList) {
        if (possibleValueList == null || possibleValueList.size() < 1) {
            throw new IllegalArgumentException("possibleValueList must be non empty string");
        }
        this.possibleValueList = possibleValueList;
        valueContainersArray = new PermutationSquareCellInfo[getPossibleValueCount()][];
        for (int i = 0; i < getPossibleValueCount(); i++) {
            valueContainersArray[i] = new PermutationSquareCellInfo[getPossibleValueCount()];
            for (int j = 0; j < getPossibleValueCount(); j++) {
                valueContainersArray[i][j] = new PermutationSquareCellInfo<PermutationSquareValue>(i, j);
            }
        }

        // columns
        columnArray = new PermutationSquareLineInfo[getPossibleValueCount()];
        for (int i = 0; i < getPossibleValueCount(); i++) {
            columnArray[i] = new PermutationSquareLineInfo(i, LineType.COLUMN, valueContainersArray[i], possibleValueList);
        }
        // rows
        rowArray = new PermutationSquareLineInfo[getPossibleValueCount()];
        for (int i = 0; i < getPossibleValueCount(); i++) {
            rowArray[i] = new PermutationSquareLineInfo(i, LineType.ROW, createRow(i), possibleValueList);
        }

        // register column and rows
        for (PermutationSquareLineInfo<PermutationSquareValue> column : columnArray) {
            valueChangedListenerList.add(column);
        }
        for (PermutationSquareLineInfo row : rowArray) {
            valueChangedListenerList.add(row);
        }
    }

    @Override
    public void changeValues(List<PermutationSquareCellInfo<PermutationSquareValue>> changeInfoList) {
        // check the infoList, contain the ones that are not set yet
        List<PermutationSquareCellInfo<PermutationSquareValue>> infoToChangeList = new ArrayList<>();
        for (PermutationSquareCellInfo changeInfo : changeInfoList) {
            if (isNewValue(changeInfo)) {
                infoToChangeList.add(changeInfo);
            }
        }
        if (infoToChangeList.size() > 0) {
            fireChangeToListeners(infoToChangeList);
        }
    }

    @Override
    public PermutationSquareValue getValue(int columnIndex, int rowIndex) {
        PermutationSquareCellInfo<PermutationSquareValue> cellInfo = valueContainersArray[columnIndex][rowIndex];
        return cellInfo.getValue();
    }

    @Override
    public void registerValueChangedListener(PermutationSquareValueChangedListener<PermutationSquareValue> valueChangedListener) {
        valueChangedListenerList.add(valueChangedListener);
    }

    // helper methods

    private int getPossibleValueCount() {
        return possibleValueList.size();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getPossibleValueCount()) {
            throw new IllegalArgumentException(String.format("index must be between 0 (including) and %d (excluded)", getPossibleValueCount()));
        }
    }

    private void checkValue(PermutationSquareValue value) {
        if (!possibleValueList.contains(value)) {
            throw new IllegalArgumentException(String.format("value %s not possible", value));
        }
    }

    private void fireChangeToListeners(List<PermutationSquareCellInfo<PermutationSquareValue>> infoToChangeList) {
        List<PermutationSquareCellInfo<PermutationSquareValue>> newChanges = new ArrayList<>();
        for (PermutationSquareValueChangedListener<PermutationSquareValue> listener : valueChangedListenerList) {
            newChanges.addAll(listener.applyChange(infoToChangeList));
        }
        // TODO: what to do
    }

    /**
     * Checks validity of {@link PermutationSquareCellInfo} content first, then looks zherer value has allready been set.
     * @param valueChangeInfo
     * @return
     */
    private boolean isNewValue(PermutationSquareCellInfo<PermutationSquareValue> valueChangeInfo) {
        checkValue(valueChangeInfo.getValue());
        checkIndex(valueChangeInfo.getColumnIndex());
        checkIndex(valueChangeInfo.getRowIndex());
        PermutationSquareCellInfo cellInfo = valueContainersArray[valueChangeInfo.getColumnIndex()][valueChangeInfo.getRowIndex()];
        return cellInfo.setValue(valueChangeInfo.getValue());
    }

    private PermutationSquareCellInfo[] createRow(int rowIndex) {
        PermutationSquareCellInfo[] rowArray = new PermutationSquareCellInfo[getPossibleValueCount()];
        for (int i = 0; i < getPossibleValueCount(); i++) {
            rowArray[i] = valueContainersArray[i][rowIndex];
        }
        return rowArray;
    }
}

package janvenstermans.puzzlesolver.permutationsquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Info of a line, i.e. row or column.
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfo<PermutationSquareValue> implements PermutationSquareValueChangedListener<PermutationSquareValue>  {

    private final int index;

    private final LineType lineType;

    private final PermutationSquareCellInfo<PermutationSquareValue>[] cellArray;

    /**
     * Possible values.
     */
    private Map<PermutationSquareValue, List<Integer>> possibleValueMap = new HashMap<>();

    public PermutationSquareLineInfo(int index, LineType lineType,
                                     PermutationSquareCellInfo<PermutationSquareValue>[] cellArray,
                                     List<PermutationSquareValue> possibleValueList) {
        this.index = index;
        this.lineType = lineType;
        this.cellArray = cellArray;
        for (PermutationSquareValue permutationSquareValue : possibleValueList) {
            possibleValueMap.put(permutationSquareValue, createIndexList(possibleValueList.size()));
        }
    }

    @Override
    public List<PermutationSquareCellInfo<PermutationSquareValue>> applyChange(List<PermutationSquareCellInfo<PermutationSquareValue>> infoToChangeList) {
        List<PermutationSquareCellInfo<PermutationSquareValue>> impactChanges = new ArrayList<>();
        for (PermutationSquareCellInfo<PermutationSquareValue> cellInfo : infoToChangeList) {
            if (isInLine(cellInfo)) {
                impactChanges.add(cellInfo);
            }
        }
        List<PermutationSquareCellInfo<PermutationSquareValue>> newChanges = new ArrayList<>();
        if (!impactChanges.isEmpty()) {

        }
        return newChanges;
    }

    private List<Integer> createIndexList(int count) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            integerList.add(i);
        }
        return integerList;
    }

    private boolean isInLine(PermutationSquareCellInfo<PermutationSquareValue> cellInfo) {
        switch (lineType) {
            case COLUMN:
                return cellInfo.getColumnIndex() == index;
            case ROW:
                return cellInfo.getRowIndex() == index;
        }
        return false;
    }
}

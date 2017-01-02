package janvenstermans.puzzlesolver.permutationsquare;

import java.util.*;

/**
 * Info of a line, i.e. row or column.
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfo<PermutationSquareValue> implements PermutationSquareValueChangedListener<PermutationSquareValue>  {

    private final int index;

    private final LineType lineType;

    private final PermutationSquareCellInfo<PermutationSquareValue>[] cellArray;

    private final List<PermutationSquareValue> possibleValueList;

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
        this.possibleValueList = possibleValueList;
        for (PermutationSquareValue permutationSquareValue : possibleValueList) {
            setIndicesOfValue(permutationSquareValue, createIndexList(possibleValueList.size()));
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
        if (impactChanges.isEmpty()) {
            return Collections.<PermutationSquareCellInfo<PermutationSquareValue>>emptyList();
        }
        for (PermutationSquareCellInfo<PermutationSquareValue> impactChange : impactChanges) {
            possibleValueMap.remove(impactChange.getValue());
        }
        List<Integer> filledIndices = getFilledIndices();
        for (Map.Entry<PermutationSquareValue, List<Integer>> entry : possibleValueMap.entrySet()) {
            entry.getValue().removeAll(filledIndices);
        }
        int extraSolvedCount = 0;
        Map<PermutationSquareValue, Integer> indicesMap = getSolvedIndicesMap();
        Set<Integer> solvedIndexList = new HashSet<>();
        while (extraSolvedCount < indicesMap.size()) {
            extraSolvedCount = indicesMap.size();
            for (Integer index : indicesMap.values()) {
                solvedIndexList.add(index);
            }
            for (Map.Entry<PermutationSquareValue, List<Integer>> entry : possibleValueMap.entrySet()) {
                if (!indicesMap.containsKey(entry.getKey())) {
                    entry.getValue().removeAll(solvedIndexList);
                }
            }
        }
        return extractNewChanges();
    }

    public void setIndicesOfValue(PermutationSquareValue value, List<Integer> indexList) {
        possibleValueMap.remove(value);
        possibleValueMap.put(value, new ArrayList<>(indexList));
    }

    public List<Integer> getIndicesOfValue(PermutationSquareValue value) {
        if (possibleValueMap.containsKey(value)) {
            return Collections.unmodifiableList(possibleValueMap.get(value));
        }
        for (PermutationSquareCellInfo<PermutationSquareValue> cellInfo : cellArray) {
            if (value.equals(cellInfo.getValue())) {
                return Collections.singletonList(getCellIndex(cellInfo));
            }
        }
        return null;
    }

    // helper methods

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

    private List<Integer> getFilledIndices() {
        List<Integer> filledIndices = new ArrayList<>();
        for (PermutationSquareCellInfo<PermutationSquareValue> cellInfo : cellArray) {
            if (cellInfo.getValue() != null) {
                filledIndices.add(getCellIndex(cellInfo));
            }
        }
        return filledIndices;
    }

    private Map<PermutationSquareValue, Integer> getSolvedIndicesMap() {
        Map<PermutationSquareValue, Integer> solvedIndeces = new HashMap<>();
        for (Map.Entry<PermutationSquareValue, List<Integer>> entry : possibleValueMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                solvedIndeces.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        return solvedIndeces;
    }

    private List<PermutationSquareCellInfo<PermutationSquareValue>> extractNewChanges() {
        List<PermutationSquareCellInfo<PermutationSquareValue>> newChanges = new ArrayList<>();
        // single values
        for (Map.Entry<PermutationSquareValue, List<Integer>> entry : possibleValueMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                PermutationSquareCellInfo<PermutationSquareValue> cellInfo
                        = createPermutationSquareCellInfo(entry.getValue().get(0));
                cellInfo.setValue(entry.getKey());
                newChanges.add(cellInfo);
            }
        }
        return newChanges;
    }

    private PermutationSquareCellInfo<PermutationSquareValue> createPermutationSquareCellInfo(int cellIndex) {
        switch (lineType) {
            case ROW:
                return new PermutationSquareCellInfo<PermutationSquareValue>(cellIndex, index, possibleValueList);
            case COLUMN:
                return new PermutationSquareCellInfo<PermutationSquareValue>(index, cellIndex, possibleValueList);
        }
        return null;
    }

    private PermutationSquareCellInfo<PermutationSquareValue> getCellOfIndex(int index) {
        return cellArray[index];
    }

    private int getCellIndex(PermutationSquareCellInfo<PermutationSquareValue> cellInfo) {
        switch (lineType) {
            case ROW:
                return cellInfo.getColumnIndex();
            case COLUMN:
                return cellInfo.getRowIndex();
        }
        return -1;
    }
}

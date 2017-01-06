package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util for PermutationSquareLineInfoTest.
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfoTestUtil {

    public static void copyChangeInfoToCellArray(List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList,
                                                  PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray) {
        for (PermutationSquareCellInfo<IntegerPermutationSquareValue> changeInfo : changeInfoList) {
            for (PermutationSquareCellInfo<IntegerPermutationSquareValue> cell : cellArray) {
                if (cellsEqual(changeInfo, cell)) {
                    cell.setValue(changeInfo.getValue());
                }
            }
        }
    }

    public static void assertCellInfoList(PermutationSquare<IntegerPermutationSquareValue> permutationSquare, List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> solvedList) {
        for (PermutationSquareCellInfo<IntegerPermutationSquareValue> solvedValue : solvedList) {
            PermutationSquareCellInfo cellInfo = permutationSquare.getCellInfo(solvedValue.getColumnIndex(), solvedValue.getRowIndex());
            Assert.assertEquals(cellInfo.getValue(), solvedValue.getValue());
        }
    }

    public static void assertCellListUnsolved(PermutationSquare<IntegerPermutationSquareValue> permutationSquare,
                                              List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> unsolvedList) {
        for (PermutationSquareCellInfo<IntegerPermutationSquareValue> unsolvedValue : unsolvedList) {
            PermutationSquareCellInfo cellInfo = permutationSquare.getCellInfo(unsolvedValue.getColumnIndex(), unsolvedValue.getRowIndex());
            Assert.assertTrue(cellInfo.getPossibleValues().containsAll(unsolvedValue.getPossibleValues()));
        }
    }


    public static void assertCellInfo(PermutationSquareCellInfo resultItem, int columnIndex, int rowIndex, PermutationSquareValue value) {
        Assert.assertEquals(columnIndex, resultItem.getColumnIndex());
        Assert.assertEquals(rowIndex, resultItem.getRowIndex());
        Assert.assertEquals(value, resultItem.getValue());
    }

    public static boolean cellsEqual(PermutationSquareCellInfo cell1, PermutationSquareCellInfo cell2) {
        return cell1.getColumnIndex() == cell2.getColumnIndex() && cell1.getRowIndex() == cell2.getRowIndex();
    }

    public static PermutationSquareCellInfo<IntegerPermutationSquareValue>[] createCellArrayForRow(int dimension, int rowIndex) {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray = new PermutationSquareCellInfo[dimension];
        for (int i = 0; i < dimension; i++) {
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex, PermutationSquareValueFactory.createIntegerListForDimension(dimension));
        }
        return cellArray;
    }

    /**
     *
     * @param lineInfo
     * @param cellArray
     * @param indexValueArray of form { {index, intValue} , ...}
     */
    public static void setCellIntegerValuesForRow(PermutationSquareLineInfo lineInfo, PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray,
                                                  int[][] indexValueArray) {
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(cellArray.length, indexValueArray);
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);
        lineInfo.applyChange(changeInfoList);
    }

    /**
     * @param indexValueArray of form { {columnIndex, rowIndex, intValue} , ...}
     */
    public static List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> createChangeInfoList(int dimension, int[][] indexValueArray) {
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        for (int i = 0; i < indexValueArray.length; i++) {
            int[] values = indexValueArray[i];
            changeInfoList.add(new PermutationSquareCellInfo(values[0], values[1],
                    PermutationSquareValueFactory.createIntegerListForDimension(dimension),
                    PermutationSquareValueFactory.createIntegerPermutationSquareValue(values[2])));
        }
        return changeInfoList;
    }

    public static void assertCellContainsValuesMap(PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray,
                                                   Map<Integer, List<Integer>> cellArrayPossibleValueMap) {
        for (Map.Entry<Integer, List<Integer>> entry : cellArrayPossibleValueMap.entrySet()) {
            assertCellContainsValues(cellArray[entry.getKey()], PermutationSquareValueFactory.createIntegerValueList(entry.getValue()));
        }
    }

    public static void assertLineIndexValuesMap(PermutationSquareLineInfo lineInfo,
                                                   Map<IntegerPermutationSquareValue, List<Integer>> cellArrayPossibleValueMap) {
        for (Map.Entry<IntegerPermutationSquareValue, List<Integer>> entry : cellArrayPossibleValueMap.entrySet()) {
           Assert.assertEquals(true, lineInfo.getIndicesOfValue(entry.getKey()).containsAll(entry.getValue()));
        }
    }

    public static void assertCellContainsValues(PermutationSquareCellInfo<IntegerPermutationSquareValue> cellArray,
                                                 List<IntegerPermutationSquareValue> possibleValues) {
        Assert.assertEquals(true, cellArray.getPossibleValues().containsAll(possibleValues));
    }

    public static Map<PermutationSquareCellInfo<IntegerPermutationSquareValue>, List<IntegerPermutationSquareValue>> createExpectedPossibleValues(
            PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray, Map<Integer, List<Integer>> possibleValueMap) {
        Map<PermutationSquareCellInfo<IntegerPermutationSquareValue>, List<IntegerPermutationSquareValue>> expectedPossibleValues = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : possibleValueMap.entrySet()) {
            expectedPossibleValues.put(cellArray[entry.getKey()], PermutationSquareValueFactory.createIntegerValueList(entry.getValue()));
        }
        return expectedPossibleValues;
    }

    public static void setCellPossibleValueIndicesMap(PermutationSquareLineInfo lineInfo,
                                                      Map<Integer, List<Integer>> cellArrayPossibleValueMap) {
        for (Map.Entry<Integer, List<Integer>> entry : cellArrayPossibleValueMap.entrySet()) {
            setCellPossibleValueIndices(lineInfo, PermutationSquareValueFactory.createIntegerPermutationSquareValue(entry.getKey()), entry.getValue());
        }
    }

    public static void setCellPossibleValueIndices(PermutationSquareLineInfo lineInfo,
                                                   IntegerPermutationSquareValue integerPermutationSquareValue,
                                                   List<Integer> possibleIndices) {
        lineInfo.setIndicesOfValue(integerPermutationSquareValue, possibleIndices);
    }
}

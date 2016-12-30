package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex);
        }
        return cellArray;
    }

    /**
     *
     * @param cellArray
     * @param indexValueArray of form { {index, intValue} , ...}
     */
    public static void setCellIntegerValues(PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray, int[][] indexValueArray) {
        for (int i = 0; i < indexValueArray.length; i++) {
            cellArray[indexValueArray[i][0]].setValue(PermutationSquareValueFactory.createIntegerPermutationSquareValue(indexValueArray[i][1]));
        }
    }

    /**
     * @param indexValueArray of form { {columnIndex, rowIndex, intValue} , ...}
     */
    public static List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> createChangeInfoList(int[][] indexValueArray) {
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        for (int i = 0; i < indexValueArray.length; i++) {
            int[] values = indexValueArray[i];
            changeInfoList.add(new PermutationSquareCellInfo(values[0], values[1], PermutationSquareValueFactory.createIntegerPermutationSquareValue(values[2])));
        }
        return changeInfoList;
    }
}

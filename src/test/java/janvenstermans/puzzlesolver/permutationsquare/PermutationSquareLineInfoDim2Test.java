package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfoDim2Test {

    @Test
    public void testApplyChangeForEmptyLineWithEmptyArgument() throws Exception {
        int dimension = 2;
        int rowIndex = 0;
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray = new PermutationSquareCellInfo[dimension];
        for (int i = 0; i < dimension; i++) {
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex);
        }
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                rowIndex, LineType.ROW, cellArray, PermutationSquareValueFactory.createIntegerList(dimension));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineWithNoResultExpected() throws Exception {
        int dimension = 2;
        int rowIndex = 0;
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray = new PermutationSquareCellInfo[dimension];
        for (int i = 0; i < dimension; i++) {
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex);
        }
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                rowIndex, LineType.ROW, cellArray, PermutationSquareValueFactory.createIntegerList(dimension));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        changeInfoList.add(new PermutationSquareCellInfo(0, rowIndex + 1, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1)));
        copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineWithOneResultExpected() throws Exception {
        int dimension = 2;
        int rowIndex = 0;
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray = new PermutationSquareCellInfo[dimension];
        for (int i = 0; i < dimension; i++) {
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex);
        }
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                rowIndex, LineType.ROW, cellArray, PermutationSquareValueFactory.createIntegerList(dimension));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        changeInfoList.add(new PermutationSquareCellInfo(0, rowIndex, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1)));
        copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(1, resultList.size());
        assertCellInfo(resultList.get(0), 1, rowIndex, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2));
    }

    @Test
    public void testApplyChangeForFilledLineWithKnownValueNoResultExpected() throws Exception {
        int dimension = 2;
        int rowIndex = 0;
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray = new PermutationSquareCellInfo[dimension];
        for (int i = 0; i < dimension; i++) {
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex);
        }
        cellArray[0].setValue(PermutationSquareValueFactory.createIntegerPermutationSquareValue(1));
        cellArray[1].setValue(PermutationSquareValueFactory.createIntegerPermutationSquareValue(2));
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                rowIndex, LineType.ROW, cellArray, PermutationSquareValueFactory.createIntegerList(dimension));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        changeInfoList.add(new PermutationSquareCellInfo(0, rowIndex, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1)));
        copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test(expected = Exception.class)
    public void testApplyChangeForFilledLineWithKnownValueNExpectException() throws Exception {
        int dimension = 2;
        int rowIndex = 0;
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray = new PermutationSquareCellInfo[dimension];
        for (int i = 0; i < dimension; i++) {
            cellArray[i] = new PermutationSquareCellInfo<IntegerPermutationSquareValue>(i, rowIndex);
        }
        cellArray[0].setValue(PermutationSquareValueFactory.createIntegerPermutationSquareValue(1));
        cellArray[1].setValue(PermutationSquareValueFactory.createIntegerPermutationSquareValue(2));
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                rowIndex, LineType.ROW, cellArray, PermutationSquareValueFactory.createIntegerList(dimension));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        changeInfoList.add(new PermutationSquareCellInfo(0, rowIndex, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2)));
        copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);
    }

    // static helper methods

    private static void copyChangeInfoToCellArray(List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList,
                                                  PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray) {
        for (PermutationSquareCellInfo<IntegerPermutationSquareValue> changeInfo : changeInfoList) {
            for (PermutationSquareCellInfo<IntegerPermutationSquareValue> cell : cellArray) {
                if (cellsEqual(changeInfo, cell)) {
                    cell.setValue(changeInfo.getValue());
                }
            }
        }
    }

    private static void assertCellInfo(PermutationSquareCellInfo resultItem, int columnIndex, int rowIndex, PermutationSquareValue value) {
        Assert.assertEquals(columnIndex, resultItem.getColumnIndex());
        Assert.assertEquals(rowIndex, resultItem.getRowIndex());
        Assert.assertEquals(value, resultItem.getValue());
    }

    private static boolean cellsEqual(PermutationSquareCellInfo cell1, PermutationSquareCellInfo cell2) {
        return cell1.getColumnIndex() == cell2.getColumnIndex() && cell1.getRowIndex() == cell2.getRowIndex();
    }
}

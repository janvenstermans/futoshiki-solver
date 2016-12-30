package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test line for dim 2.
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfoDim2Test {

    private final static int DIMENSION = 2;

    // general test values
    private final static int ROW_INDEX = 0;
    private final static LineType LINE_TYPE = LineType.ROW;

    //--------------------------------
    // method applyChange
    //--------------------------------

    @Test
    public void testApplyChangeForEmptyLineWithEmptyArgument() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerList(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(new int[][] {});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineWithNoResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerList(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(new int[][] {{0, ROW_INDEX + 1, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineWithOneResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerList(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(new int[][] {{0, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(1, resultList.size());
        PermutationSquareLineInfoTestUtil.assertCellInfo(resultList.get(0), 1, ROW_INDEX, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2));
    }

    @Test
    public void testApplyChangeForFilledLineWithKnownValueNoResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfoTestUtil.setCellIntegerValues(cellArray, new int[][] {{0,1},{1,2}});
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerList(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(new int[][] {{0, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test(expected = Exception.class)
    public void testApplyChangeForFilledLineWithKnownValueNExpectException() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfoTestUtil.setCellIntegerValues(cellArray, new int[][] {{0,1},{1,2}});
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerList(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(new int[][] {{0, ROW_INDEX, 2}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);
    }
}

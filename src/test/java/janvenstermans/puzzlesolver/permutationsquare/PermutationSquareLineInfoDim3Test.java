package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test line for dim 3.
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfoDim3Test {

    private final static int DIMENSION = 3;

    // general test values
    private final static int ROW_INDEX = 0;
    private final static LineType LINE_TYPE = LineType.ROW;

    //--------------------------------
    // method applyChange for EMPTY
    //--------------------------------

    @Test
    public void testApplyChangeForEmptyLineWithEmptyArgument() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineWithChangeListNotInLineNoResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX + 1, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineChangeListOneThenNoResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testApplyChangeForEmptyLineChangeListTwoThenOneResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 3},{1, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(1, resultList.size());
        PermutationSquareLineInfoTestUtil.assertCellInfo(resultList.get(0), 2, ROW_INDEX, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2));
    }

    //--------------------------------
    // method applyChange for LIMITED
    //--------------------------------

    //--------------------------------
    // method applyChange for FILLED
    //--------------------------------

    @Test
    public void testApplyChangeForFilledLineWithKnownValueNoResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        PermutationSquareLineInfoTestUtil.setCellIntegerValuesForRow(lineInfo, cellArray, new int[][] {{0,ROW_INDEX,1},{1,ROW_INDEX,2}});
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
    }

    @Test(expected = Exception.class)
    public void testApplyChangeForFilledLineWithKnownValueNExpectException() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        PermutationSquareLineInfoTestUtil.setCellIntegerValuesForRow(lineInfo, cellArray, new int[][] {{0,ROW_INDEX,1},{1,ROW_INDEX,2}});
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 2}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);
    }
}

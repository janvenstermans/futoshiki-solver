package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);
        Map<Integer, List<Integer>> possibleValueMap = new HashMap<>();
        possibleValueMap.put(0, Arrays.asList(1, 2));
        possibleValueMap.put(1, Arrays.asList(1, 2));
        Map<IntegerPermutationSquareValue, List<Integer>> indexPerValueMap = new HashMap<>();
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(1), Arrays.asList(0,1));
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(2), Arrays.asList(0,1));

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
        PermutationSquareLineInfoTestUtil.assertCellContainsValuesMap(cellArray, possibleValueMap);
        PermutationSquareLineInfoTestUtil.assertLineIndexValuesMap(lineInfo, indexPerValueMap);
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
        Map<Integer, List<Integer>> possibleValueMap = new HashMap<>();
        possibleValueMap.put(0, Arrays.asList(1,2));
        possibleValueMap.put(1, Arrays.asList(1,2));
        Map<IntegerPermutationSquareValue, List<Integer>> indexPerValueMap = new HashMap<>();
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(1), Arrays.asList(0,1));
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(2), Arrays.asList(0,1));

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
        PermutationSquareLineInfoTestUtil.assertCellContainsValuesMap(cellArray, possibleValueMap);
        PermutationSquareLineInfoTestUtil.assertLineIndexValuesMap(lineInfo, indexPerValueMap);
    }

    @Test
    public void testApplyChangeForEmptyLineWithOneResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);
        Map<Integer, List<Integer>> possibleValueMap = new HashMap<>();
        possibleValueMap.put(0, Arrays.asList(1));
        possibleValueMap.put(1, Arrays.asList(2));
        Map<IntegerPermutationSquareValue, List<Integer>> indexPerValueMap = new HashMap<>();
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(1), Arrays.asList(0));
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(2), Arrays.asList(1));

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(1, resultList.size());
        PermutationSquareLineInfoTestUtil.assertCellInfo(resultList.get(0), 1, ROW_INDEX, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2));
        PermutationSquareLineInfoTestUtil.assertCellContainsValuesMap(cellArray, possibleValueMap);
        PermutationSquareLineInfoTestUtil.assertLineIndexValuesMap(lineInfo, indexPerValueMap);
    }

    @Test
    public void testApplyChangeForFilledLineWithKnownValueNoResultExpected() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        PermutationSquareLineInfoTestUtil.setCellIntegerValuesForRow(lineInfo, cellArray, new int[][] {{0,ROW_INDEX, 1},{1,ROW_INDEX, 2}});
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 1}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);
        Map<Integer, List<Integer>> possibleValueMap = new HashMap<>();
        possibleValueMap.put(0, Arrays.asList(1));
        possibleValueMap.put(1, Arrays.asList(2));
        Map<IntegerPermutationSquareValue, List<Integer>> indexPerValueMap = new HashMap<>();
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(1), Arrays.asList(0));
        indexPerValueMap.put(PermutationSquareValueFactory.createIntegerPermutationSquareValue(2), Arrays.asList(1));

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);

        Assert.assertEquals(0, resultList.size());
        PermutationSquareLineInfoTestUtil.assertCellContainsValuesMap(cellArray, possibleValueMap);
        PermutationSquareLineInfoTestUtil.assertLineIndexValuesMap(lineInfo, indexPerValueMap);
    }

    @Test(expected = Exception.class)
    public void testApplyChangeForFilledLineWithKnownValueNExpectException() throws Exception {
        PermutationSquareCellInfo<IntegerPermutationSquareValue>[] cellArray
                = PermutationSquareLineInfoTestUtil.createCellArrayForRow(DIMENSION, ROW_INDEX);
        PermutationSquareLineInfo lineInfo = new PermutationSquareLineInfo<IntegerPermutationSquareValue>(
                ROW_INDEX, LINE_TYPE, cellArray, PermutationSquareValueFactory.createIntegerListForDimension(DIMENSION));
        PermutationSquareLineInfoTestUtil.setCellIntegerValuesForRow(lineInfo, cellArray, new int[][]{{0,ROW_INDEX,1},{1,ROW_INDEX,2}});
        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList
                = PermutationSquareLineInfoTestUtil.createChangeInfoList(DIMENSION, new int[][] {{0, ROW_INDEX, 2}});
        PermutationSquareLineInfoTestUtil.copyChangeInfoToCellArray(changeInfoList, cellArray);

        List<PermutationSquareCellInfo<PermutationSquareValue>> resultList = lineInfo.applyChange(changeInfoList);
    }
}

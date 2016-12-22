package janvenstermans.puzzlesolver.permutationsquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Info of a line, i.e. row or column.
 * @author Jan Venstermans
 */
public class PermutationSquareLineInfo<PermutationSquareValue>  {

    private final int index;

    private final LineType lineType;

    private final PermutationSquareValue[] cellArray;

    /**
     * Possible values.
     */
    private Map<PermutationSquareValue, List<Integer>> possibleValueMap = new HashMap<>();

    public PermutationSquareLineInfo(int index, LineType lineType, PermutationSquareValue[] cellArray, List<PermutationSquareValue> possibleValueList) {
        this.index = index;
        this.lineType = lineType;
        this.cellArray = cellArray;
        for (PermutationSquareValue permutationSquareValue : possibleValueList) {
            possibleValueMap.put(permutationSquareValue, createIndexList(possibleValueList.size()));
        }
    }

    private List<Integer> createIndexList(int count) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            integerList.add(i);
        }
        return integerList;
    }
}

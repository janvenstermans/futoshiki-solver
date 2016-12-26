package janvenstermans.puzzlesolver.permutationsquare;

import java.util.List;

/**
 * @author Jan Venstermans
 */
public interface PermutationSquareValueChangedListener<PermutationSquareValue>  {

    /**
     *
     * @param infoToChangeList
     * @return list of values that are changed as a result the input.
     */
    List<PermutationSquareCellInfo<PermutationSquareValue>> applyChange(List<PermutationSquareCellInfo<PermutationSquareValue>> infoToChangeList);
}

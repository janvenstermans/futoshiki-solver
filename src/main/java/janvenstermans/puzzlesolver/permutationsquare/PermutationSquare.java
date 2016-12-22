package janvenstermans.puzzlesolver.permutationsquare;

import java.util.List;

/**
 * @author Jan Venstermans
 */
public interface PermutationSquare<PermutationSquareValue>  {

    void changeValues(List<PermutationSquareCellInfo> changeInfoList);

    PermutationSquareValue getValue(int columnIndex, int rowIndex);
}

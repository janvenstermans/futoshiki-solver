package janvenstermans.puzzlesolver.permutationsquare;

import java.util.List;

/**
 * @author Jan Venstermans
 */
public interface PermutationSquare<PermutationSquareValue>  {

    void changeValues(List<PermutationSquareCellInfo<PermutationSquareValue>> changeInfoList);

    PermutationSquareValue getValue(int columnIndex, int rowIndex);

    void registerValueChangedListener(PermutationSquareValueChangedListener<PermutationSquareValue> valueChangedListener);
}

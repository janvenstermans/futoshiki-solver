package janvenstermans.puzzlesolver.permutationsquare;

import janvenstermans.puzzlesolver.permutationsquare.value.IntegerPermutationSquareValue;
import janvenstermans.puzzlesolver.permutationsquare.value.PermutationSquareValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Venstermans
 */
public class PermutationSquareImplDim2Test {

    @Test
    public void test2Dim() throws Exception {
        int dimension = 2;
        PermutationSquare<IntegerPermutationSquareValue> permutationSquare
                = new PermutationSquareImpl<IntegerPermutationSquareValue>(PermutationSquareValueFactory.createIntegerListForDimension(dimension));

        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        changeInfoList.add(new PermutationSquareCellInfo(0,0, PermutationSquareValueFactory.createIntegerListForDimension(dimension),
                PermutationSquareValueFactory.createIntegerPermutationSquareValue(1)));

        permutationSquare.changeValues(changeInfoList);

        // all the other values should be filled in
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1).equals(permutationSquare.getValue(0,0)));
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2).equals(permutationSquare.getValue(1,0)));
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2).equals(permutationSquare.getValue(0,1)));
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1).equals(permutationSquare.getValue(1,1)));
    }
}

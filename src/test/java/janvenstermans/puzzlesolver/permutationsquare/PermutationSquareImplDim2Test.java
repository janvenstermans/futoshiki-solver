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
    public void testName() throws Exception {
        PermutationSquare<IntegerPermutationSquareValue> permutationSquare
                = new PermutationSquareImpl<IntegerPermutationSquareValue>(PermutationSquareValueFactory.createIntegerList(2));

        List<PermutationSquareCellInfo<IntegerPermutationSquareValue>> changeInfoList = new ArrayList<>();
        changeInfoList.add(new PermutationSquareCellInfo(0,0, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1)));

        permutationSquare.changeValues(changeInfoList);

        // all the other values should be filled in
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1).equals(permutationSquare.getValue(0,0)));
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2).equals(permutationSquare.getValue(1,0)));
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(2).equals(permutationSquare.getValue(0,1)));
        Assert.assertEquals(true, PermutationSquareValueFactory.createIntegerPermutationSquareValue(1).equals(permutationSquare.getValue(1,1)));
    }
}

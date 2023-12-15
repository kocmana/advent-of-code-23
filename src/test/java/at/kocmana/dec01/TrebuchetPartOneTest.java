package at.kocmana.dec01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrebuchetPartOneTest {

    private final TrebuchetPartOne underTest = new TrebuchetPartOne();

    @ParameterizedTest
    @CsvSource({
            "1abc2,12",
            "pqr3stu8vwx,38",
            "a1b2c3d4e5f,15",
            "treb7uchet,77"

    })
    void calibrationValuesAreCorrect(String input, Integer calibrationOutput) {
        //when
        var actualOutput = underTest.extractCalibrationOutput(input);

        //then
        assertThat(actualOutput).isEqualTo(calibrationOutput);
    }

    @Test
    void puzzleOutputIsCorrect(){
        //given
        var input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;

        //when
        var actualOutput = underTest.calculatePuzzleOutput(input);

        //then
        assertThat(actualOutput).isEqualTo(142);
    }


}
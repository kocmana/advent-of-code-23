package at.kocmana.dec01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrebuchetPartTwoTest {

    private final TrebuchetPartTwo underTest = new TrebuchetPartTwo();

    @ParameterizedTest
    @CsvSource({
            "two1nine,29",
            "eightwothree,83",
            "abcone2threexyz,13",
            "xtwone3four,24",
            "4nineeightseven2,42",
            "zoneight234,14",
            "7pqrstsixteen,76"

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
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;

        //when
        var actualOutput = underTest.calculatePuzzleOutput(input);

        //then
        assertThat(actualOutput).isEqualTo(281);
    }


}
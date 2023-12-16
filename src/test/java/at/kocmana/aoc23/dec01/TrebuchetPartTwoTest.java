package at.kocmana.aoc23.dec01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TrebuchetPartTwoTest {

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
    var actualOutput = TrebuchetPartTwo.extractCalibrationOutput(input);

    //then
    assertThat(actualOutput).isEqualTo(calibrationOutput);
  }

  @Test
  void puzzleOutputIsCorrect() {
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
    var actualOutput = TrebuchetPartTwo.calculatePuzzleOutput(input);

    //then
    assertThat(actualOutput).isEqualTo(281);
  }


}
package at.kocmana.aoc23.dec02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CubeConundrumPartTwoTest {

  @ParameterizedTest
  @CsvSource({
      "'Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green',4,2,6",
      "'Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue',1,3,4",
      "'Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red',20,13,6",
      "'Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red',14,3,15",
      "'Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green',6,3,2"
  })
  void canDetermineMinimum(String gameInput, int redMin, int greenMin, int blueMin) {
    //given
    var game = CubeConundrumPartTwo.parseGameFromLine(gameInput);

    //when
    var actualResult = CubeConundrumPartTwo.analyzeGame(game);

    //then
    assertThat(actualResult).isNotNull()
        .returns(redMin, from(CubeConundrumPartTwo.GameStatistics::minNumberOfRedCubesDrawn))
        .returns(greenMin, from(CubeConundrumPartTwo.GameStatistics::minNumberOfGreenCubesDrawn))
        .returns(blueMin, from(CubeConundrumPartTwo.GameStatistics::minNumberOfBlueCubesDrawn));
  }

  @ParameterizedTest
  @CsvSource({
      "'Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green',48",
      "'Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue',12",
      "'Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red',1560",
      "'Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red',630",
      "'Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green',36"
  })
  void canCalculatePower(String gameInput, int power) {
    //given
    var game = CubeConundrumPartTwo.parseGameFromLine(gameInput);
    var gameStatistics = CubeConundrumPartTwo.analyzeGame(game);

    //when
    var actualResult = CubeConundrumPartTwo.calculatePower(gameStatistics);

    //then
    assertThat(actualResult).isEqualTo(power);
  }
}
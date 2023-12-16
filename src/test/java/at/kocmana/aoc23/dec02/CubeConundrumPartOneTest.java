package at.kocmana.aoc23.dec02;

import static org.assertj.core.api.Assertions.from;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CubeConundrumPartOneTest {

  @Test
  void canBeParsed() {
    //given
    var input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";

    //when
    var actualResult = CubeConundrumPartOne.parseGameFromLine(input);

    //then
    assertThat(actualResult).isNotNull()
        .returns(3, from(CubeConundrumPartOne.Game::gameId))
        .extracting(CubeConundrumPartOne.Game::drawResults)
        .asList()
        .hasSize(8)
        .contains(new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.GREEN, 13));
  }

  @Test
  void canBeAnalyzed() {
    //given
    var game = new CubeConundrumPartOne.Game(1, List.of(
        new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.RED, 10),
        new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.RED, 0),
        new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.GREEN, 30),
        new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.GREEN, 20),
        new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.BLUE, 10),
        new CubeConundrumPartOne.DrawResult(CubeConundrumPartOne.Color.BLUE, 20)
    ));

    //when
    var actualStatistics = CubeConundrumPartOne.analyzeGame(game);

    //then
    assertThat(actualStatistics).isNotNull()
        .returns(1, from(CubeConundrumPartOne.GameStatistics::gameId))
        .returns(10, from(CubeConundrumPartOne.GameStatistics::maxNumberOfRedCubesDrawn))
        .returns(20, from(CubeConundrumPartOne.GameStatistics::maxNumberOfBlueCubesDrawn))
        .returns(30, from(CubeConundrumPartOne.GameStatistics::maxNumberOfGreenCubesDrawn));
  }

  @Test
  void canBeSummedUp() {
    //given
    var input = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """;

    //when
    var actualResult = CubeConundrumPartOne.getSumOfPossibleGameIndexesForInput(input);

    //then
    assertThat(actualResult).isEqualTo(8);
  }

}
package at.kocmana.aoc23.dec02;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CubeConundrumPartTwo {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec02", "CubeConundrum.txt");
    var result = getSumOfPowers(puzzleInput);
    System.out.printf("Result: %d%n", result);
  }

  static int getSumOfPowers(String input) {
    return parseGamesFromInput(input).stream()
        .map(CubeConundrumPartTwo::analyzeGame)
        .mapToInt(CubeConundrumPartTwo::calculatePower)
        .sum();
  }

  static List<Game> parseGamesFromInput(String input) {
    return input.lines()
        .map(CubeConundrumPartTwo::parseGameFromLine)
        .toList();
  }

  static Game parseGameFromLine(String line) {
    var gameIdAndResults = line.split(":");
    var gameIdAsString = gameIdAndResults[0].split(" ")[1];
    var gameId = Integer.parseInt(gameIdAsString);

    var drawsToParse = gameIdAndResults[1].split("(;|,)");
    var drawResults = Arrays.stream(drawsToParse)
        .map(String::strip)
        .map(CubeConundrumPartTwo::toDrawResult)
        .toList();

    return new Game(gameId, drawResults);
  }

  static GameStatistics analyzeGame(Game game) {
    var maxByColor = game.drawResults.stream()
        .collect(groupingBy(DrawResult::color,
            maxBy(Comparator.comparingInt(DrawResult::count))));

    return new GameStatistics(
        game.gameId(),
        maxByColor.get(Color.GREEN).map(DrawResult::count).orElse(0),
        maxByColor.get(Color.RED).map(DrawResult::count).orElse(0),
        maxByColor.get(Color.BLUE).map(DrawResult::count).orElse(0)
    );
  }

  static int calculatePower(GameStatistics gameStatistics) {
    return gameStatistics.minNumberOfRedCubesDrawn()
        * gameStatistics.minNumberOfGreenCubesDrawn()
        * gameStatistics.minNumberOfBlueCubesDrawn();
  }

  private static DrawResult toDrawResult(String cubeInput) {
    var separatedCubeInput = cubeInput.trim().toUpperCase()
        .split(" ");

    return new DrawResult(
        Color.valueOf(separatedCubeInput[1]),
        Integer.parseInt(separatedCubeInput[0])
    );
  }

  enum Color {
    RED, BLUE, GREEN
  }

  record Game(
      int gameId,
      List<DrawResult> drawResults
  ) {}

  record DrawResult(
      Color color,
      Integer count
  ) {}

  record GameStatistics(
      int gameId,
      int minNumberOfGreenCubesDrawn,
      int minNumberOfRedCubesDrawn,
      int minNumberOfBlueCubesDrawn
  ) {}
}

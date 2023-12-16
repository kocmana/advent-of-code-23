package at.kocmana.aoc23.dec02;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class CubeConundrumPartOne {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec02", "CubeConundrum.txt");
    var result = getSumOfPossibleGameIndexesForInput(puzzleInput);
    System.out.printf("Result: %d%n", result);
  }

  static int getSumOfPossibleGameIndexesForInput(String input) {
    return parseGamesFromInput(input).stream()
        .map(CubeConundrumPartOne::analyzeGame)
        .filter(CubeConundrumPartOne::isPossibleResult)
        .mapToInt(GameStatistics::gameId)
        .sum();
  }

  static List<Game> parseGamesFromInput(String input) {
    return input.lines()
        .map(CubeConundrumPartOne::parseGameFromLine)
        .toList();
  }

  static Game parseGameFromLine(String line) {
    var gameIdAndResults = line.split(":");
    var gameIdAsString = gameIdAndResults[0].split(" ")[1];
    var gameId = Integer.parseInt(gameIdAsString);

    var drawsToParse = gameIdAndResults[1].split(";");
    var drawResults = Arrays.stream(drawsToParse)
        .map(String::strip)
        .map(s -> s.split(","))
        .map(CubeConundrumPartOne::mapToDrawResult)
        .flatMap(Collection::stream)
        .toList();

    return new Game(gameId, drawResults);
  }

  static GameStatistics analyzeGame(Game game) {
    var maxByColor = game.drawResults.stream()
        .collect(groupingBy(DrawResult::color,
            maxBy(Comparator.comparingInt(DrawResult::count))));

    return new GameStatistics(
        game.gameId(),
        maxByColor.get(Color.GREEN).orElse(new DrawResult(Color.GREEN, 0)).count,
        maxByColor.get(Color.RED).orElse(new DrawResult(Color.RED, 0)).count,
        maxByColor.get(Color.BLUE).orElse(new DrawResult(Color.BLUE, 0)).count
    );
  }

  static boolean isPossibleResult(GameStatistics gameStatistics) {
    return gameStatistics.maxNumberOfRedCubesDrawn() <= 12
        && gameStatistics.maxNumberOfGreenCubesDrawn() <= 13
        && gameStatistics.maxNumberOfBlueCubesDrawn() <= 14;
  }

  private static List<DrawResult> mapToDrawResult(String[] input) {
    return Arrays.stream(input)
        .map(CubeConundrumPartOne::toCubeNumber)
        .toList();
  }

  private static DrawResult toCubeNumber(String cubeInput) {
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
      int maxNumberOfGreenCubesDrawn,
      int maxNumberOfRedCubesDrawn,
      int maxNumberOfBlueCubesDrawn
  ) {}
}

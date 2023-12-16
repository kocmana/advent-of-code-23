package at.kocmana.aoc23.dec01;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class TrebuchetPartOne {

  private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d");

  static Integer extractCalibrationOutput(String input) {
    var matcher = NUMBER_PATTERN.matcher(input);
    var numbers = matcher.results()
        .map(MatchResult::group)
        .toList();

    var calibrationOutputAsString = numbers.getFirst() +
        numbers.getLast();

    return Integer.parseInt(calibrationOutputAsString);
  }

  static int calculatePuzzleOutput(String input) {
    return input.lines()
        .map(TrebuchetPartOne::extractCalibrationOutput)
        .reduce(0, Integer::sum);
  }

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec01", "TrebuchetPartOne.txt");
    var result = TrebuchetPartOne.calculatePuzzleOutput(puzzleInput);
    System.out.printf("Result: %d%n", result);
  }
}

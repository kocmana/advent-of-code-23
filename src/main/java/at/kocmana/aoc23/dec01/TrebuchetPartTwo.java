package at.kocmana.aoc23.dec01;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrebuchetPartTwo {

  private static final Map<String, Integer> SPELLED_NUMBERS = Map.of(
      "one", 1,
      "two", 2,
      "three", 3,
      "four", 4,
      "five", 5,
      "six", 6,
      "seven", 7,
      "eight", 8,
      "nine", 9
  );

  private static final String ALL_LOOKUPS_REGEX = SPELLED_NUMBERS.keySet()
      .stream()
      .collect(Collectors.joining("|", "(\\d|", ")"));
  private static final Pattern NUMBERS_PATTERN = Pattern.compile(ALL_LOOKUPS_REGEX);

  static Integer extractCalibrationOutput(String input) {
    var spelledOutNumberMatcher = NUMBERS_PATTERN.matcher(input);

    var results = spelledOutNumberMatcher.results()
        .sorted(Comparator.comparingInt(MatchResult::start))
        .map(MatchResult::group)
        .map(TrebuchetPartTwo::toDigit)
        .toList();

    var calibrationOutputAsString = results.getFirst() +
        results.getLast();

    return Integer.parseInt(calibrationOutputAsString);
  }

  private static String toDigit(String spelledOutDigit) {
    return SPELLED_NUMBERS.containsKey(spelledOutDigit)
        ? SPELLED_NUMBERS.get(spelledOutDigit).toString()
        : spelledOutDigit;
  }

  static int calculatePuzzleOutput(String input) {
    return input.lines()
        .map(TrebuchetPartTwo::extractCalibrationOutput)
        .reduce(0, Integer::sum);
  }

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec01", "TrebuchetPartOne.txt");
    var result = TrebuchetPartTwo.calculatePuzzleOutput(puzzleInput);
    System.out.printf("Result: %d%n", result);
  }
}

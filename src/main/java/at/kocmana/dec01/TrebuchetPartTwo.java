package at.kocmana.dec01;

import at.kocmana.common.ResourceToString;

import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class TrebuchetPartTwo {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d");

    private static final Map<String, Integer> SPELLED_LETTERS = Map.of(
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

    static Integer extractCalibrationOutput(String input) {
        var replacedInput = replaceSpelledOutNumbersWithDigits(input);
        var numberMatcher = NUMBER_PATTERN.matcher(replacedInput);
        var numbers = numberMatcher.results()
                .map(MatchResult::group)
                .toList();

        var calibrationOutputAsString = numbers.getFirst() +
                numbers.getLast();

        return Integer.parseInt(calibrationOutputAsString);
    }

    static String replaceSpelledOutNumbersWithDigits(String input) {
        var output = input;
        for (var entry : SPELLED_LETTERS.entrySet()) {
            output = output.replaceAll(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return output;
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

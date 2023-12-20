package at.kocmana.aoc23.dec06;

import static java.util.function.Predicate.not;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class WaitForItPartTwo {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec06", "WaitForItPartTwo.txt");
    var result = generateNumberOfWays(puzzleInput);
    System.out.printf("Result: %d", result);
  }

  static long generateNumberOfWays(String input) {
    var race = parseRaces(input).get(0);
    return WaitForItPartTwo.assessNumberOfPossibleWins(race);
  }

  static List<Race> parseRaces(String input) {
    input = input.replace("Time:", "")
        .replace("Distance:", "")
        .trim();
    var lines = input.split("\\n");
    var times = toNumberList(lines[0]);
    var distances = toNumberList(lines[1]);

    assert (times.size() == distances.size());

    return IntStream.range(0, times.size())
        .mapToObj(i -> new Race(times.get(i), distances.get(i)))
        .toList();
  }

  public static List<Long> toNumberList(String input) {
    return Arrays.stream(input.trim().split("\\s+"))
        .filter(not(String::isBlank))
        .map(Long::parseLong)
        .toList();
  }

  static long assessNumberOfPossibleWins(Race race) {
    return LongStream.range(1, race.time())
        .map(pressedMs -> calculateDistance(pressedMs, race.time()))
        .filter(distance ->  isWin(race.distanceToBeat(), distance))
        .count();
  }

  static long calculateDistance(long durationButtonPressed, long totalRaceDuration) {
    var remainingTime = totalRaceDuration - durationButtonPressed;
    var speed = durationButtonPressed;
    return speed * remainingTime;
  }

  static Boolean isWin(long distanceToBeat, long distance) {
    return distanceToBeat < distance;
  }

  record Race(
      long time,
      long distanceToBeat
  ) {
  }

}

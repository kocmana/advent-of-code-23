package at.kocmana.aoc23.dec06;

import static at.kocmana.aoc23.common.Parser.toNumberList;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.List;
import java.util.stream.IntStream;

public class WaitForItPartOne {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec06", "WaitForItPartOne.txt");
    var result = generateNumberOfWays(puzzleInput);
    System.out.printf("Result: %d", result);
  }

  static long generateNumberOfWays(String input) {
    return parseRaces(input).stream()
        .map(WaitForItPartOne::assessNumberOfPossibleWins)
        .reduce(1L, (i, j) -> i * j);
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
        .mapToObj(i -> new Race(times.get((int) i), distances.get((int) i)))
        .toList();
  }

  static long assessNumberOfPossibleWins(Race race) {
    return IntStream.range(1, race.time())
        .mapToObj(i -> {
          var distance = calculateDistance(i, race.time());
          return new RaceOutcome(
              distance,
              isWin(race.distanceToBeat(), distance)
          );
        })
        .filter(RaceOutcome::isWin)
        .count();
  }

  static int calculateDistance(int durationButtonPressed, int totalRaceDuration) {
    var remainingTime = totalRaceDuration - durationButtonPressed;
    var speed = durationButtonPressed;
    return speed * remainingTime;
  }

  static boolean isWin(int distanceToBeat, int distance) {
    return distanceToBeat < distance;
  }
}

record Race(
    int time,
    int distanceToBeat
) {
}

record RaceOutcome(
    int distance,
    boolean isWin
) {
}
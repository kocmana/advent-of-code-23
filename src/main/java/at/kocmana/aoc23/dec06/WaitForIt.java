package at.kocmana.aoc23.dec06;

import static at.kocmana.aoc23.common.Parser.toNumberList;

import java.util.List;
import java.util.stream.IntStream;

public class WaitForIt {

  public static void main(String[] args) {

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

  long generateNumberOfWays(String input) {
    return parseRaces(input).stream()
        .map(WaitForIt::assessNumberOfPossibleWins)
        .reduce(1L, (i, j) -> i * j);
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
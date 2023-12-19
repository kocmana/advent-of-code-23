package at.kocmana.aoc23.common;

import static java.util.function.Predicate.not;

import java.util.Arrays;
import java.util.List;

public class Parser {

  private Parser() {

  }

  public static List<Integer> toNumberList(String input) {
    return Arrays.stream(input.trim().split("\\s+"))
        .filter(not(String::isBlank))
        .map(Integer::parseInt)
        .toList();
  }

}

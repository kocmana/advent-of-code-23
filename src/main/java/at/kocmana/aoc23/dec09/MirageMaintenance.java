package at.kocmana.aoc23.dec09;

import at.kocmana.aoc23.common.Parser;
import at.kocmana.aoc23.common.ResourceToString;
import java.util.ArrayList;
import java.util.List;

public class MirageMaintenance {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec09", "MirageMaintenance.txt");
    var result = play(puzzleInput);
    System.out.printf("Result: %d%n", result);
  }

  static int play(String input) {
    return parseInput(input).stream()
        .map(MirageMaintenance::inferNextInLine)
        .reduce(0, (i, j) -> i + j);
  }

  static int inferNextInLine(List<Integer> list) {
    var currentList = list;
    var reductions = new ArrayList<List<Integer>>();
    //reduce downward
    while (canBeFurtherReduced(currentList)) {
      var reducedList = reduce(currentList);
      reductions.add(reducedList);
      currentList = reducedList;
    }
    //infer upward
    for (int i = 0; i < reductions.size() - 1; i++) {
      var last = reductions.get(reductions.size() - i - 1);
      var secondToLast = reductions.get(reductions.size() - i - 2);
      secondToLast.add(last.getLast() + secondToLast.getLast());
    }
    return list.getLast() + reductions.getFirst().getLast();
  }

  static List<List<Integer>> parseInput(String input) {
    return input.lines()
        .map(MirageMaintenance::parseLine)
        .toList();
  }

  static List<Integer> parseLine(String input) {
    return Parser.toNumberList(input);
  }

  static List<Integer> reduce(List<Integer> list) {
    List<Integer> reducedList = new ArrayList<>(list.size() - 1);
    for (int i = 0; i < list.size() - 1; i++) {
      reducedList.add(list.get(i + 1) - list.get(i));
    }
    return reducedList;
  }

  static boolean canBeFurtherReduced(List<Integer> list) {
    return !list.stream().allMatch(i -> i.equals(0));
  }
}

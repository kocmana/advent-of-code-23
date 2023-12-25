package at.kocmana.aoc23.dec08;

import at.kocmana.aoc23.common.ResourceToString;
import at.kocmana.aoc23.common.Tuple;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HauntedWastelandPartTwo {

  static final Pattern NETWORK_ENTRY_PATTERN =
      Pattern.compile("(?<source>[A-Z]{3}) = \\((?<left>[A-Z]{3}), (?<right>[A-Z]{3})\\)");

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec08", "HauntedWasteland.txt");
    var map = parseMap(puzzleInput);
    var result = play(map);
    System.out.printf("Result: %d%n", result);
  }

  static Long play(WastelandMap map) {
    return traverse(map);
  }

  static Long traverse(WastelandMap map) {
    var currentNodes = map.network().keySet().stream()
        .filter(node -> node.endsWith("A"))
        .map(node -> HauntedWastelandPartTwo.traversePerNode(map, node))
        .toList();

    return lcm(currentNodes);
  }

  static Long traversePerNode(WastelandMap map, String startNode) {
    var directions = map.directions();
    var network = map.network();

    var numberOfSteps = 0L;
    var currentNode = startNode;
    while (!currentNode.endsWith("Z")) {
      var currentDirection = directions.get((int) numberOfSteps % directions.size());
      currentNode = network.get(currentNode).move(currentDirection);
      numberOfSteps++;
    }
    return numberOfSteps;
  }

  /* LCM Solution for n numbers taken from
     https://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
   */
  private static long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  private static long lcm(List<Long> numbers) {
    return numbers.stream()
        .reduce(1L, (x, y) -> x * (y / gcd(x, y)));
  }

  public static WastelandMap parseMap(String input) {
    var directionsAndNetwork = input.split("\\n", 3);

    var directions = directionsAndNetwork[0].chars()
        .mapToObj(c -> Direction.valueOf(String.valueOf((char) c)))
        .toList();

    var network = directionsAndNetwork[2].lines()
        .map(NETWORK_ENTRY_PATTERN::matcher)
        .flatMap(Matcher::results)
        .map(HauntedWastelandPartTwo::toNetworkNode)
        .collect(Collectors.toMap(Tuple::key, Tuple::value));

    return new WastelandMap(directions, network);
  }

  static Tuple<String, Node> toNetworkNode(MatchResult matchResult) {
    return new Tuple<String, Node>(
        matchResult.group("source"),
        new Node(matchResult.group("left"), matchResult.group("right")));
  }

  static Map.Entry<String, Node> toMapEntry(String input) {
    System.out.printf("Parsing input: %s%n", input);
    var result = NETWORK_ENTRY_PATTERN.matcher(input);

    return new AbstractMap.SimpleImmutableEntry<>(
        result.group("source"),
        new Node(
            result.group("left"),
            result.group("right")
        )
    );
  }


}

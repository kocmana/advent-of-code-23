package at.kocmana.aoc23.dec08;

import at.kocmana.aoc23.common.ResourceToString;
import at.kocmana.aoc23.common.Tuple;
import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HauntedWastelandPartOne {

  static final Pattern NETWORK_ENTRY_PATTERN =
      Pattern.compile("(?<source>[A-Z]{3}) = \\((?<left>[A-Z]{3}), (?<right>[A-Z]{3})\\)");

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec08", "HauntedWasteland.txt");
    var map = parseMap(puzzleInput);
    var result = traverse(map);
    System.out.printf("Result: %d%n", result);
  }

  static int traverse(WastelandMap map) {
    var directions = map.directions();
    var network = map.network();

    var numberOfSteps = 0;
    var currentNode = "AAA";
    while (!currentNode.equals("ZZZ")) {
      var currentDirection = directions.get(numberOfSteps % directions.size());
      System.out.printf("Step %d. Current Node: %s, Possible Directions: %s, Next Move: %s%n",
          numberOfSteps, currentNode, network.get(currentNode), currentDirection);
      currentNode = network.get(currentNode).move(currentDirection);
      numberOfSteps++;
    }
    return numberOfSteps;
  }

  public static WastelandMap parseMap(String input) {
    var directionsAndNetwork = input.split("\\n", 3);

    var directions = directionsAndNetwork[0].chars()
        .mapToObj(c -> Direction.valueOf(String.valueOf((char) c)))
        .toList();

    var network = directionsAndNetwork[2].lines()
        .map(NETWORK_ENTRY_PATTERN::matcher)
        .flatMap(Matcher::results)
        .map(HauntedWastelandPartOne::toNetworkNode)
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

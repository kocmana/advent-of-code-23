package at.kocmana.aoc23.dec07;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class CamelCards {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec07", "CamelCards.txt");
    var result = play(puzzleInput);
    System.out.printf("Result: %d%n", result);
  }

  static long play(String input) {
    var sortedHands = sortLists(parseGame(input));
    return IntStream.range(0, sortedHands.size())
        .map(i -> (i + 1) * sortedHands.get(i).bid())
        .sum();
  }

  static List<Hand> parseGame(String input) {
    return input.lines()
        .map(CamelCards::parseHand)
        .toList();
  }

  static Hand parseHand(String input) {
    var cardsAndBid = input.split("( )+");
    var cards = Arrays.stream(cardsAndBid[0].split(""))
        .map(Card::fromString)
        .toList();
    var bid = Integer.parseInt(cardsAndBid[1]);

    return new Hand(cards, bid);
  }

  static List<Hand> sortLists(List<Hand> unsortedHands) {
    return unsortedHands.stream()
        .sorted(Comparator.comparing(Hand::power).reversed())
        .toList();
  }
}
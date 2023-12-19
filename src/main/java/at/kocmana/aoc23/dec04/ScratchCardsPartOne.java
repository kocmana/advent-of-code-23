package at.kocmana.aoc23.dec04;

import static java.util.function.Predicate.not;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScratchCardsPartOne {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec04", "ScratchCards.txt");
    var result = determineNumberOfTotalPoints(puzzleInput);
    System.out.printf("Result: %d", result);
  }

  static long determineNumberOfTotalPoints(String input) {
    return parseCards(input).stream()
        .map(ScratchCardsPartOne::determineNumberOfWins)
        .map(ScratchCardsPartOne::calculatePoints)
        .reduce(0L, Long::sum);
  }

  static List<Card> parseCards(String input) {
    return input.lines()
        .map(ScratchCardsPartOne::parseCard)
        .toList();
  }

  static Card parseCard(String input) {
    var cardIdAndNumbers = input.split(":");

    var winningNumbersAndDrawnNumbers = cardIdAndNumbers[1].split("\\|");

    var winningNumbers = toNumberList(winningNumbersAndDrawnNumbers[0]);
    var drawnNumbers = toNumberList(winningNumbersAndDrawnNumbers[1]);

    return new Card(
        winningNumbers,
        drawnNumbers
    );
  }

  private static Set<Integer> toNumberList(String input) {
    return Arrays.stream(input.trim().split("( )+"))
        .filter(not(String::isBlank))
        .map(Integer::parseInt)
        .collect(Collectors.toSet());
  }

  static long determineNumberOfWins(Card card) {
    return card.drawnNumbers
        .stream()
        .filter(card.winningNumbers::contains)
        .count();
  }

  static long calculatePoints(long matches) {
    return matches == 0
        ? matches
        : (long)Math.pow(2, matches - 1.0);
  }


  record Card(
      Set<Integer> winningNumbers,
      Set<Integer> drawnNumbers
  ) {}

}

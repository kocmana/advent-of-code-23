package at.kocmana.aoc23.dec04;

import static java.util.function.Predicate.not;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScratchCards {

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec04", "ScratchCards.txt");
    var result = determineNumberOfTotalPoints(puzzleInput);
    System.out.printf("Result: %f", result);
  }

  static double determineNumberOfTotalPoints(String input) {
    return parseCards(input).stream()
        .map(ScratchCards::determineNumberOfWins)
        .map(ScratchCards::calculatePoints)
        .reduce(0.0, Double::sum);
  }

  static long determineNumberOfWins(Card card) {
    return card.drawnNumbers
        .stream()
        .filter(card.winningNumbers::contains)
        .count();
  }

  static double calculatePoints(long matches) {
    return matches == 0
        ? matches
        : Math.pow(2, matches - 1);
  }

  static List<Card> parseCards(String input) {
    return input.lines()
        .map(ScratchCards::parseCard)
        .toList();
  }

  static Card parseCard(String input) {
    var cardIdAndNumbers = input.split(":");

    var winningNumbersAndDrawnNumbers = cardIdAndNumbers[1].split("\\|");

    var winningNumbers = Arrays.stream(winningNumbersAndDrawnNumbers[0].trim().split(" "))
        .map(String::trim)
        .filter(not(String::isBlank))
        .map(Integer::parseInt)
        .collect(Collectors.toSet());

    var drawnNumbers = Arrays.stream(winningNumbersAndDrawnNumbers[1].trim().split(" "))
        .map(String::trim)
        .filter(not(String::isBlank))
        .map(Integer::parseInt)
        .collect(Collectors.toSet());

    return new Card(
        winningNumbers,
        drawnNumbers
    );
  }


  record Card(
      Set<Integer> winningNumbers,
      Set<Integer> drawnNumbers
  ) {
  }

}

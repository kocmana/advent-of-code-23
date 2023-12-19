package at.kocmana.aoc23.dec04;

import static java.util.function.Predicate.not;

import at.kocmana.aoc23.common.ResourceToString;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScratchCardsPartTwo {

  private final CardCounter cardCounter = new CardCounter();

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec04", "ScratchCards.txt");
    var game = new ScratchCardsPartTwo();
    game.play(puzzleInput);
  }

  public void play(String input) {
    generateAllWinningCards(input);
    var result = getNumberOfCards();
    System.out.printf("Result: %d", result);
  }

  static List<Card> parseCards(String input) {
    return input.lines()
        .map(ScratchCardsPartTwo::parseCard)
        .toList();
  }

  static Card parseCard(String input) {
    var cardIdAndNumbers = input.split(":");

    var cardIdAsString = cardIdAndNumbers[0].split("( )+")[1];
    var cardId = Integer.parseInt(cardIdAsString);

    var winningNumbersAndDrawnNumbers = cardIdAndNumbers[1].split("\\|");

    var winningNumbers = toNumberList(winningNumbersAndDrawnNumbers[0]);
    var drawnNumbers = toNumberList(winningNumbersAndDrawnNumbers[1]);

    return new Card(
        cardId,
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

  static CardResult determineNumberOfWins(Card card) {
    var wins = (int) card.drawnNumbers().stream()
        .filter(card.winningNumbers()::contains)
        .count();

    return new CardResult(card.cardId(), wins);
  }

  void generateAllWinningCards(String input) {
    parseCards(input).stream()
        .map(ScratchCardsPartTwo::determineNumberOfWins)
        .sorted(Comparator.comparingInt(CardResult::cardId))
        .forEachOrdered(this::addNewCards);
  }

  void addNewCards(CardResult result) {
    //first add self
    var amountOfBaseCards = cardCounter.increase(result.cardId(), 1);


    //then add the next cards starting from this index
    IntStream.rangeClosed(1, result.numberOfWins())
        .forEach(index -> cardCounter.increase(result.cardId() + index, amountOfBaseCards));
  }

  long getNumberOfCards() {
    return cardCounter.getTotalNumberOfCards();
  }
}

record Card(
    int cardId,
    Set<Integer> winningNumbers,
    Set<Integer> drawnNumbers
) {
}

record CardResult(
    int cardId,
    int numberOfWins
) {
}

class CardCounter {
  private ConcurrentMap<Integer, AtomicLong> cardCounter = new ConcurrentHashMap<>();

  long get(int gameId) {
    if (!cardCounter.containsKey(gameId)) {
      return 0;
    }
    return cardCounter.get(gameId).get();
  }

  long increase(int cardId, long times) {
    if (!cardCounter.containsKey(cardId)) {
      cardCounter.put(cardId, new AtomicLong(times));
      return times;
    } else {
      return cardCounter.get(cardId).addAndGet(times);
    }
  }

  long getTotalNumberOfCards() {
    return cardCounter.values().stream()
        .map(AtomicLong::get)

        .reduce(0L, Long::sum);
  }
}

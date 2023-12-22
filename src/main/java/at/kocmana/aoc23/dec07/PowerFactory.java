package at.kocmana.aoc23.dec07;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class PowerFactory {

  private static final Map<List<Integer>, Type> TYPE_DICTIONARY = Map.of(
      List.of(5), Type.FIVE_OF_A_KIND,
      List.of(4, 1), Type.FOUR_OF_A_KIND,
      List.of(3, 2), Type.FULL_HOUSE,
      List.of(3, 1, 1), Type.THREE_OF_A_KIND,
      List.of(2, 2, 1), Type.TWO_PAIR,
      List.of(2, 1, 1, 1), Type.ONE_PAIR,
      List.of(1, 1, 1, 1, 1), Type.HIGH_CARD
  );

  private PowerFactory() {
  }

  static Power fromCards(List<Card> cardList) {
    var amountOfCardsByLabel = cardList.stream()
        .collect(groupingBy(Function.identity(), counting()));

    Comparator<Map.Entry<Card, Long>> frequencyThenCardLabel =
        Map.Entry.<Card, Long>comparingByValue().reversed()
            .thenComparing(Map.Entry.<Card, Long>comparingByKey());

    var sortedAmounts = amountOfCardsByLabel.entrySet().stream()
        .sorted(frequencyThenCardLabel)
        .toList();
    var sortedValues = sortedAmounts.stream()
        .map(e -> e.getValue().intValue())
        .toList();
    var sortedCards = sortedAmounts.stream()
        .map(Map.Entry::getKey)
        .toList();

    var type = TYPE_DICTIONARY.get(sortedValues);
    return new Power(
        type,
        sortedCards
    );
  }
}


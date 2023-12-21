package at.kocmana.aoc23.dec07;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class PowerFactory {

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

    var firstEntry = sortedAmounts.get(0);
    if (firstEntry.getValue() == 5) {
      return new Power(
          Type.FIVE_OF_A_KIND,
          singletonList(firstEntry.getKey())
      );
    }
    var secondEntry = sortedAmounts.get(1);
    if (firstEntry.getValue() == 4) {
      return new Power(
          Type.FOUR_OF_A_KIND,
          singletonList(firstEntry.getKey())
      );
    } else if (firstEntry.getValue() == 3 && secondEntry.getValue() == 2) {
      return new Power(
          Type.FULL_HOUSE,
          List.of(firstEntry.getKey(), secondEntry.getKey())
      );
    } else if (firstEntry.getValue() == 3) {
      return new Power(
          Type.THREE_OF_A_KIND,
          singletonList(firstEntry.getKey())
      );
    } else if (firstEntry.getValue() == 2 && secondEntry.getValue() == 2) {
      return new Power(
          Type.TWO_PAIR,
          List.of(firstEntry.getKey(), secondEntry.getKey())
      );
    } else if (firstEntry.getValue() == 2) {
      return new Power(
          Type.ONE_PAIR,
          singletonList(firstEntry.getKey())
      );
    } else {
      return new Power(
          Type.HIGH_CARD,
          cardList
      );
    }
  }
}


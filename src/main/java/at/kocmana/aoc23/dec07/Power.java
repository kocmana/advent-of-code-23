package at.kocmana.aoc23.dec07;

import java.util.List;
import java.util.stream.IntStream;

record Power(
    Type type,
    List<Card> relevantCards
) implements Comparable<Power> {
  @Override
  public int compareTo(Power other) {
    if (this.type.compareTo(other.type) != 0) {
      return this.type.compareTo(other.type);
    }
    return IntStream.range(0, relevantCards.size())
        .sequential()
        .map(i -> this.relevantCards.get(i).compareTo(other.relevantCards.get(i)))
        .filter(comparison -> comparison != 0)
        .findFirst()
        .orElse(0);
  }
}

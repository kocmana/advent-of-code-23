package at.kocmana.aoc23.dec07;

import java.util.List;

record Hand(
    List<Card> cards,
    int bid,
    Power power
) {
  Hand(List<Card> cards, int bid) {
    this(cards, bid, PowerFactory.fromCards(cards));
  }
}

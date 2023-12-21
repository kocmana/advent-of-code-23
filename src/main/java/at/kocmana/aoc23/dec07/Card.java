package at.kocmana.aoc23.dec07;

import java.util.Arrays;

enum Card {
  ACE("A"),
  KING("K"),
  QUEEN("Q"),
  JACK("J"),
  TEN("T"),
  NINE("9"),
  EIGHT("8"),
  SEVEN("7"),
  SIX("6"),
  FIVE("5"),
  FOUR("4"),
  THREE("3"),
  TWO("2");

  private final String textualRepresentation;

  private Card(String textualRepresentation) {
    this.textualRepresentation = textualRepresentation;
  }

  public static Card fromString(String textualRepresentation) {
    return Arrays.stream(Card.values())
        .filter(value -> value.textualRepresentation.equalsIgnoreCase(textualRepresentation))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown Card Type %s".formatted(textualRepresentation)));
  }

}

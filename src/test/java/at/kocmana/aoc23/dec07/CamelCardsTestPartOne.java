package at.kocmana.aoc23.dec07;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import at.kocmana.aoc23.common.Triple;
import at.kocmana.aoc23.common.Tuple;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CamelCardsTestPartOne {


  static Stream<Tuple<Hand, Power>> generateCanAssessPowerTestValues() {
    return Stream.of(
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.ACE), 1),
            new Power(Type.FIVE_OF_A_KIND, List.of(Card.ACE))),
        new Tuple<>(new Hand(List.of(Card.THREE, Card.THREE, Card.THREE, Card.THREE, Card.TWO), 1),
            new Power(Type.FOUR_OF_A_KIND, List.of(Card.THREE, Card.TWO))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.TWO, Card.TWO), 1),
            new Power(Type.FULL_HOUSE, List.of(Card.ACE, Card.TWO))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.TWO, Card.THREE), 1),
            new Power(Type.THREE_OF_A_KIND, List.of(Card.ACE, Card.THREE, Card.TWO))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.TWO, Card.TWO, Card.THREE), 1),
            new Power(Type.TWO_PAIR, List.of(Card.ACE, Card.TWO, Card.THREE))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.TWO, Card.THREE, Card.FOUR), 1),
            new Power(Type.ONE_PAIR, List.of(Card.ACE, Card.FOUR, Card.THREE, Card.TWO))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.SIX, Card.THREE, Card.FOUR, Card.FIVE), 1),
            new Power(Type.HIGH_CARD, List.of(Card.ACE, Card.SIX, Card.FIVE, Card.FOUR, Card.THREE)))
    );
  }

  @Test
  void canBeParsed() {
    //given
    var input = "32T3K 765";

    //when
    var actualResult = CamelCards.parseHand("32T3K 765");

    //then
    assertThat(actualResult).isNotNull()
        .returns(765, from(Hand::bid))
        .extracting(Hand::cards)
        .asList()
        .contains(
            Card.THREE,
            Card.TWO,
            Card.TEN,
            Card.THREE,
            Card.KING
        );
  }

  @ParameterizedTest
  @MethodSource("generateCanAssessPowerTestValues")
  void canAssessPower(Tuple<Hand, Power> testValue) {
    //when
    var actualResult = PowerFactory.fromCards(testValue.key().cards());

    //then
    assertThat(actualResult).isNotNull()
        .returns(testValue.value().type(), from(Power::type))
        .returns(testValue.value().relevantCards(), from(Power::relevantCards));
  }

  static Stream<Triple<Power, Power, Integer>> generateCanComparePowerTestValues() {
    return Stream.of(
        new Triple(
            new Power(Type.HIGH_CARD, List.of(Card.ACE, Card.KING, Card.QUEEN, Card.THREE, Card.TWO)),
            new Power(Type.HIGH_CARD, List.of(Card.KING, Card.QUEEN, Card.JACK, Card.TEN, Card.NINE)),
            -1
        ),
        new Triple(
            new Power(Type.HIGH_CARD, List.of(Card.ACE, Card.QUEEN, Card.JACK, Card.TEN, Card.NINE)),
            new Power(Type.HIGH_CARD, List.of(Card.ACE, Card.KING, Card.QUEEN, Card.THREE, Card.TWO)),
            1
        ),
        new Triple(
            new Power(Type.FOUR_OF_A_KIND, List.of(Card.FOUR, Card.FOUR, Card.FOUR, Card.FOUR, Card.NINE)),
            new Power(Type.FOUR_OF_A_KIND, List.of(Card.THREE, Card.THREE, Card.THREE, Card.THREE, Card.ACE)),
            -1
        ),
        new Triple(
            new Power(Type.FIVE_OF_A_KIND, List.of(Card.FOUR, Card.FOUR, Card.FOUR, Card.FOUR, Card.FOUR)),
            new Power(Type.TWO_PAIR, List.of(Card.THREE, Card.THREE, Card.FOUR, Card.FOUR, Card.ACE)),
            -4
        )
    );
  }

  @ParameterizedTest
  @MethodSource("generateCanComparePowerTestValues")
  void canComparePowers(Triple<Power, Power, Integer> testData) {
    //given
    var left = testData.first();
    var right = testData.second();
    var expectedResult = testData.third();

    //when
    var actualResult = left.compareTo(right);

    //then
    assertThat(actualResult).isEqualTo(expectedResult);
  }

  @Test
  void canIntegrate() {
    //given
    var input = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
        """;

    //when
    var actualResult = CamelCards.play(input);

    //then
    assertThat(actualResult).isEqualTo(6440L);
  }

  @Test
  void canSortHands() {
    //given
    var fifth = new Hand(List.of(Card.THREE, Card.THREE, Card.THREE, Card.THREE, Card.THREE), 0);
    var fourth = new Hand(List.of(Card.FOUR, Card.FOUR, Card.FOUR, Card.FOUR, Card.THREE), 0);
    var third = new Hand(List.of(Card.KING, Card.KING, Card.FOUR, Card.FOUR, Card.THREE), 0);
    var second = new Hand(List.of(Card.KING, Card.KING, Card.THREE, Card.THREE, Card.ACE), 0);
    var first = new Hand(List.of(Card.KING, Card.ACE, Card.THREE, Card.NINE, Card.EIGHT), 0);

    var unsortedHands = List.of(
        second,
        fourth,
        fifth,
        first,
        third
    );

    //when
    var actualResult = CamelCards.sortLists(unsortedHands);

    //then
    assertThat(actualResult).hasSize(5)
        .containsExactly(
            first,
            second,
            third,
            fourth,
            fifth
            );
  }

}
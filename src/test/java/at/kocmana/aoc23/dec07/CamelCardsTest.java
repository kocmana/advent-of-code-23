package at.kocmana.aoc23.dec07;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import at.kocmana.aoc23.common.Tuple;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CamelCardsTest {


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

  static Stream<Tuple<Hand, Power>> generateCanAssessPowerTestValues(){
    return Stream.of(
        //new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.ACE), 1), new Result(Type.FIVE_OF_A_KIND, singletonList(Card.ACE))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.TWO), 1), new Power(Type.FOUR_OF_A_KIND, singletonList(Card.ACE))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.TWO, Card.TWO), 1), new Power(Type.FULL_HOUSE, List.of(Card.ACE, Card.TWO))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.ACE, Card.TWO, Card.THREE), 1), new Power(Type.THREE_OF_A_KIND, singletonList(Card.ACE))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.TWO, Card.TWO, Card.THREE), 1), new Power(Type.TWO_PAIR, List.of(Card.ACE, Card.TWO))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.ACE, Card.TWO, Card.THREE, Card.FOUR), 1), new Power(Type.ONE_PAIR, List.of(Card.ACE))),
        new Tuple<>(new Hand(List.of(Card.ACE, Card.TWO, Card.THREE, Card.FOUR, Card.FIVE), 1), new Power(Type.HIGH_CARD, List.of(Card.ACE, Card.TWO, Card.THREE, Card.FOUR, Card.FIVE)))
    );
  }

  @ParameterizedTest
  @MethodSource("generateCanAssessPowerTestValues")
  void canAssessPower(Tuple<Hand, Power> testValue) {
    //when
    var actualResult = testValue.value().power();

    //then
    assertThat(actualResult).isNotNull()
        .returns(testValue.key().type(), from(Power::type))
        .returns(testValue.key().relevantCards(), from(Power::relevantCards));
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

}
package at.kocmana.aoc23.dec04;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScratchCardsPartOneTest {

  @Test
  void canParse() {
    //given
    var input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

    //when
    var actualResult = ScratchCardsPartOne.parseCard(input);

    //then
    assertThat(actualResult.winningNumbers())
        .contains(41, 48, 83, 86, 17);

    assertThat(actualResult.drawnNumbers())
        .hasSize(8)
        .contains(83, 86, 6, 31, 17, 9, 48, 53);
  }

  @ParameterizedTest
  @CsvSource({
      "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53, 4",
      "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19, 2",
      "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1, 2",
      "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83, 1",
      "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36, 0",
      "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11, 0"
  })
  void canDetermineNumberOfMatches(String input, long expectedNumberOfMatches) {
    //given
    var card = ScratchCardsPartOne.parseCard(input);

    //when
    var actualResult = ScratchCardsPartOne.determineNumberOfWins(card);

    //then
    assertThat(actualResult).isEqualTo(expectedNumberOfMatches);
  }

  @ParameterizedTest
  @CsvSource({
      "0,0",
      "1,1",
      "2,2",
      "3,4",
      "4,8",
      "5,16",
  })
  void canDeterminePoints(long numberOfMatches, int expectedNumberOfPoints) {
    //when
    var actualResult = ScratchCardsPartOne.calculatePoints(numberOfMatches);

    //then
    assertThat(actualResult).isEqualTo(expectedNumberOfPoints);
  }

  @Test
  void canDetermineTotalPoints() {
    //given
    var input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """;

    //when
    var actualResult = ScratchCardsPartOne.determineNumberOfTotalPoints(input);

    //then
    assertThat(actualResult).isEqualTo(13);
  }
}
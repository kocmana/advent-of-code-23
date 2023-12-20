package at.kocmana.aoc23.dec06;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WaitForItPartOneTest {

  @Test
  void canParse() {
    //given
    var input = """
        Time:      7  15   30
        Distance:  9  40  200
        """;

    //when
    var actualResult = WaitForItPartOne.parseRaces(input);

    //then
    assertThat(actualResult).isNotNull()
        .hasSize(3)
        .contains(
            new Race(7, 9),
            new Race(15, 40),
            new Race(30, 200)
        );
  }

  @ParameterizedTest
  @CsvSource({
      "0,0",
      "1,6",
      "2,10",
      "3,12",
      "4,12",
      "5,10",
      "6,6",
      "7,0"
  })
  void canCalculateDistance(int durationOfButton, int expectedDistance) {
    //given
    var durationOfRace = 7;

    //when
    var actualResult = WaitForItPartOne.calculateDistance(durationOfButton, durationOfRace);

    //then
    assertThat(actualResult).isEqualTo(expectedDistance);
  }

  @Test
  void canGenerateNumberOfPossibleWins(){
    //given
    var input = """
        Time:      7  15   30
        Distance:  9  40  200
        """;

    //when
    var actualResult = WaitForItPartOne.generateNumberOfWays(input);

    //then
    assertThat(actualResult).isEqualTo(288);
  }

}
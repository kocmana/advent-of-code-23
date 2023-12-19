package at.kocmana.aoc23.dec06;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WaitForItTest {

  @Test
  void canParse() {
    //given
    var input = """
        Time:      7  15   30
        Distance:  9  40  200
        """;

    //when
    var actualResult = WaitForIt.parseRaces(input);

    //then
    assertThat(actualResult).isNotNull()
        .hasSize(3)
        .contains(
            new Race(7, 9),
            new Race(15, 40),
            new Race(30, 200)
        );
  }

}
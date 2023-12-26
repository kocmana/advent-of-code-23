package at.kocmana.aoc23.dec09;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class MirageMaintenancePartTwoTest {

  @Test
  void canInferPreviousInLine() {
    //given
    var input = List.of(10, 13, 16, 21, 30, 45);

    //when
    var actualResult = MirageMaintenancePartTwo.inferPreviousInLine(input);

    //then
    assertThat(actualResult).isEqualTo(5);
  }
}
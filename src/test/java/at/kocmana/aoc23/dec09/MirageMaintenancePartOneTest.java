package at.kocmana.aoc23.dec09;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class MirageMaintenancePartOneTest {

  @Test
  void canParseLine() {
    //given
    var input = "0 3 6 9 12 15";

    //when
    var actualResult = MirageMaintenancePartOne.parseLine(input);

    //then
    assertThat(actualResult).hasSize(6)
        .containsExactly(0, 3, 6, 9, 12, 15);
  }

  @Test
  void canReduce() {
    //given
    var valuesToReduce = List.of(0, 3, 6, 9, 12, 15);

    //when
    var actualResult = MirageMaintenancePartOne.reduce(valuesToReduce);

    //then
    assertThat(actualResult).hasSize(valuesToReduce.size() - 1)
        .containsExactly(3, 3, 3, 3, 3);
  }

  @Test
  void canAssessIfFurtherReductionIsPossiblePositive() {
    //given
    var valuesToAssess = List.of(3, 6, 9, 12);

    //when
    var actualResult = MirageMaintenancePartOne.canBeFurtherReduced(valuesToAssess);

    //then
    assertThat(actualResult).isTrue();
  }

  @Test
  void canAssessIfFurtherReductionIsPossibleNegative() {
    //given
    var valuesToAssess = List.of(0, 0, 0, 0);

    //when
    var actualResult = MirageMaintenancePartOne.canBeFurtherReduced(valuesToAssess);

    //then
    assertThat(actualResult).isFalse();
  }

  @Test
  void canInferNextInLine() {
    //given
    var lineToInferValueFrom = List.of(10, 13, 16, 21, 30, 45);

    //when
    var actualResult = MirageMaintenancePartOne.inferNextInLine(lineToInferValueFrom);

    //then
    assertThat(actualResult).isEqualTo(68);
  }

  @Test
  void canIntegrate() {
    //given
    var testInput = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
        """;

    //when
    var actualResult = MirageMaintenancePartOne.play(testInput);

    //then
    assertThat(actualResult).isEqualTo(114);
  }

}
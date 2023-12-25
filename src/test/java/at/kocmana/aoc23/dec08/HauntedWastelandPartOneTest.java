package at.kocmana.aoc23.dec08;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import java.util.List;
import org.junit.jupiter.api.Test;

class HauntedWastelandPartOneTest {

  @Test
  void canBeParsed() {
    //given
    var input = """
        RL
                
        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """;

    //when
    var actualResult = HauntedWastelandPartOne.parseMap(input);

    //then
    assertThat(actualResult).isNotNull()
        .returns(List.of(Direction.R, Direction.L), from(WastelandMap::directions))
        .extracting(WastelandMap::network)
        .hasFieldOrPropertyWithValue("ZZZ", new Node("ZZZ", "ZZZ"))
        .hasFieldOrPropertyWithValue("AAA", new Node("BBB", "CCC"));
  }

  @Test
  void canTraverse() {
    //given
    var input = """
        RL
                
        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """;
    var map = HauntedWastelandPartOne.parseMap(input);

    //when
    var actualResult = HauntedWastelandPartOne.traverse(map);

    //then
    assertThat(actualResult).isEqualTo(2);
  }

}
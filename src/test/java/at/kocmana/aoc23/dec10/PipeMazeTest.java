package at.kocmana.aoc23.dec10;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PipeMazeTest {

  @Test
  void canBeParsed() {
    //given
    var input = """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
        """;

    //when
    var actualResult = PipeMaze.parseMaze(input);

    //then
    assertThat(actualResult).isNotNull();
    assertThat(actualResult.getCoordinate(Coordinate.of(2, 0))).isEqualTo(PipeType.SOUTH_TO_EAST);
    assertThat(actualResult.getCoordinate(Coordinate.of(3, 0))).isEqualTo(PipeType.SOUTH_TO_WEST);
    assertThat(actualResult.getCoordinate(Coordinate.of(0, 2))).isEqualTo(PipeType.START);
  }

  @Test
  void canFindStart() {
    //given
    var input = """
        ...
        ..S
        ...
        """;
    var maze = PipeMaze.parseMaze(input);

    //when
    var actualResult = maze.findStartPoint();

    //then
    assertThat(actualResult).isEqualTo(Coordinate.of(2, 1));
  }

  @Test
  void canWalk() {
    //given
    var input = """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
        """;
    var maze = PipeMaze.parseMaze(input);

    //when
    var actualResult = maze.walkRound();

    //then
    assertThat(actualResult).isEqualTo(8);
  }

}
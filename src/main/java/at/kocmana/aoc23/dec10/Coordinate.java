package at.kocmana.aoc23.dec10;

public record Coordinate(
    int x,
    int y) {

  static Coordinate of(int x, int y) {
    return new Coordinate(x, y);
  }

}

package at.kocmana.aoc23.dec10;

public enum Direction {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  public Direction getOpposite() {
    return switch (this) {
      case NORTH -> SOUTH;
      case EAST -> WEST;
      case SOUTH -> NORTH;
      case WEST -> EAST;
    };
  }
}

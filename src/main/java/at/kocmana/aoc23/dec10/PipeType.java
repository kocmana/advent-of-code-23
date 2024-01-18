package at.kocmana.aoc23.dec10;

import java.util.Arrays;

public enum PipeType {
  VERTICAL("|", Direction.NORTH, Direction.SOUTH),
  HORIZONTAL("-", Direction.EAST, Direction.WEST),
  NORTH_TO_EAST("L", Direction.NORTH, Direction.EAST),
  NORTH_TO_WEST("J", Direction.NORTH, Direction.WEST),
  SOUTH_TO_WEST("7", Direction.SOUTH, Direction.WEST),
  SOUTH_TO_EAST("F", Direction.SOUTH, Direction.EAST),
  START("S", Direction.EAST, Direction.EAST);

  private final String stringRepresentation;
  private final Direction firstDirection;
  private final Direction secondDirection;

  PipeType(String stringRepresentation, Direction firstDirection, Direction secondDirection) {
    this.stringRepresentation = stringRepresentation;
    this.firstDirection = firstDirection;
    this.secondDirection = secondDirection;
  }

  static PipeType fromString(String string) {
    return Arrays.stream(PipeType.values())
        .filter(pipeType -> pipeType.stringRepresentation.equalsIgnoreCase(string))
        .findFirst()
        .orElse(null);
  }

  public boolean canTraverse(Direction direction) {
    return direction.equals(firstDirection.getOpposite())
         || direction.equals(secondDirection.getOpposite());
  }

  public Direction traverse(Direction sourceDirection) {
    return sourceDirection.equals(firstDirection)
        ? secondDirection
        : firstDirection;
  }

  @Override
  public String toString() {
    return this.stringRepresentation;
  }
}

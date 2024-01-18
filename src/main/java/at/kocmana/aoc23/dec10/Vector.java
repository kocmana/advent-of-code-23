package at.kocmana.aoc23.dec10;

import java.util.Map;
import java.util.function.IntPredicate;

public class Vector {

  private final int x;
  private final int y;

  static final IntPredicate IS_VALID_VECTOR_VALUE = value -> value == 0 || Math.abs(value) == 1;
  static final Map<Direction, Vector> VECTOR_MAP = Map.of(
      Direction.NORTH, new Vector(0, -1),
      Direction.EAST, new Vector(1, 0),
      Direction.SOUTH, new Vector(0, 1),
      Direction.WEST, new Vector(-1, 0)
  );

  private Vector(int x, int y) {
    if (!IS_VALID_VECTOR_VALUE.test(x) || !IS_VALID_VECTOR_VALUE.test(y)) {
      throw new IllegalArgumentException("Must only consist of 0 or 1");
    }
    this.x = x;
    this.y = y;
  }

  public static Vector fromDirection(Direction direction) {
    return VECTOR_MAP.get(direction);
  }

  public Direction toDirection() {
    return VECTOR_MAP.entrySet().stream()
        .filter(entry -> entry.getValue().equals(this))
        .map(Map.Entry::getKey)
        .findFirst()
        .orElseThrow();
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "Vector{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}

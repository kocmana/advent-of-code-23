package at.kocmana.aoc23.dec08;

public record Node (
    String left,
    String right
) {

  String move(Direction direction) {
    return switch (direction) {
      case L -> left;
      case R -> right;
    };
  }

}

package at.kocmana.aoc23.dec10;

public class Agent {

  private Coordinate position;
  private Vector currentMovingDirection;

  public Agent(Coordinate position, Vector currentMovingDirection) {
    this.position = position;
    this.currentMovingDirection = currentMovingDirection;
  }

  public void move(Vector vector) {
    this.position = Coordinate.of(
        position.x() + vector.getX(),
        position.y() + vector.getY()
    );
    this.currentMovingDirection = vector;
  }

  public void move(Direction direction) {
    move(Vector.fromDirection(direction));
  }

  public Coordinate getPosition() {
    return position;
  }

  public Direction getCurrentMovingDirection() {
    return currentMovingDirection.toDirection();
  }

  @Override
  public String toString() {
    return "Agent{" +
        "position=" + position +
        ", currentMovingDirection=" + currentMovingDirection +
        '}';
  }
}

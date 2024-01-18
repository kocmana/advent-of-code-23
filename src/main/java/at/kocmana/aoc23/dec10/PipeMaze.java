package at.kocmana.aoc23.dec10;

import static java.util.Objects.nonNull;

import at.kocmana.aoc23.common.ResourceToString;
import at.kocmana.aoc23.common.Tuple;
import java.util.Arrays;

public class PipeMaze {

  private final PipeType[][] maze;

  public PipeMaze(PipeType[][] maze) {
    this.maze = maze;
  }

  public static void main(String[] args) {
    var puzzleInput = ResourceToString.from("dec10", "PipeMaze.txt");
    var maze = parseMaze(puzzleInput);
    System.out.printf("Result: %d%n", maze.walkRound());
  }

  public static PipeMaze parseMaze(String input) {
    var maze = input.lines()
        .map(PipeMaze::parseLine)
        .toArray(PipeType[][]::new);

    return new PipeMaze(maze);
  }

  private static PipeType[] parseLine(String input) {
    return Arrays.stream(input.split(""))
        .map(PipeType::fromString)
        .toArray(PipeType[]::new);
  }

  int walkRound() {
    var start = findStartPoint();
    var startDirection = Direction.EAST;

    var agent = new Agent(start, Vector.fromDirection(startDirection));
    var distanceCounter = 0;
    do {
      var pipe = getCoordinate(agent.getPosition());
      var originatingDirection = agent.getCurrentMovingDirection().getOpposite();
      var resultingDirection = pipe.traverse(originatingDirection);
      agent.move(resultingDirection);
      distanceCounter++;
    } while (!agent.getPosition().equals(start));

    return distanceCounter / 2;
  }

  Coordinate findStartPoint() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        var pipeType = maze[i][j];
        if (nonNull(pipeType) && pipeType.equals(PipeType.START)) {
          return Coordinate.of(j, i);
        }
      }
    }
    return null;
  }

  PipeType getCoordinate(Coordinate coordinate) {
    return maze[coordinate.y()][coordinate.x()];
  }

}

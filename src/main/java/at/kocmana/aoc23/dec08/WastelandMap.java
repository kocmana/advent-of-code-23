package at.kocmana.aoc23.dec08;

import java.util.List;
import java.util.Map;

public record WastelandMap(
    List<Direction> directions,
    Map<String, Node> network
) {}

package heuristics;

import datatypes.Vertex;

import java.util.Map;

public interface Heuristic {

    double h(Vertex source, Vertex target, Map<Integer, Long> vertexTime);
}

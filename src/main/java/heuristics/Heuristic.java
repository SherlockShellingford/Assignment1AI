package heuristics;

import agents.State;
import datatypes.Vertex;

import java.util.Map;

public interface Heuristic {

    double h(Vertex source, Vertex target, Map<Integer, Long> vertexTime);
    double h(State source, State target, Map<Integer, Long> vertexTime);
}

package heuristics;

import datatypes.Vertex;

public interface Heuristic {

    double h(Vertex source, Vertex target);
}

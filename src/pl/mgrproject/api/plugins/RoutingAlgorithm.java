package pl.mgrproject.api.plugins;

import java.awt.Point;
import java.util.List;

import pl.mgrproject.api.Graph;

public interface RoutingAlgorithm extends Plugin {
    public void setGraph(Graph<?> g);
    public List<Point> getPath();
    public void run(int start, int stop);
}

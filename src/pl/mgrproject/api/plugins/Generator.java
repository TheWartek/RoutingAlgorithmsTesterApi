package pl.mgrproject.api.plugins;

import pl.mgrproject.api.Graph;

public interface Generator extends Plugin {
    public void generate(int nVertices);
    public Graph<?> getGraph();
}

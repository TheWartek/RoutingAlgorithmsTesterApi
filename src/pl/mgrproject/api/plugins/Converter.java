package pl.mgrproject.api.plugins;

import pl.mgrproject.api.Graph;

public interface Converter extends Plugin {
    void setGraph(Graph<?> graph);
}

package pl.mgrproject.api;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import pl.mgrproject.api.plugins.PluginManager;
import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;

public class Environment {
    private static PluginManager pluginManager;
    private static boolean stop;
    private static GraphPanel graph;
    private static ChartPanel chart;
    private static List<Long> times = new LinkedList<Long>();

    public static PluginManager getPluginManager() {
	if (pluginManager == null) {
	    pluginManager = new PluginManager();
	}

	return pluginManager;
    }

    public static void stopTest() {
	stop = true;
    }

    public static void startTest() {
	stop = false;
    }

    public static boolean testIsStopped() {
	return stop;
    }

    public static GraphPanel getGraphPanel() {
	if (graph == null) {
	    graph = new GraphPanel();
	}
	return graph;
    }

    public static ChartPanel getChartPanel() {
	if (chart == null) {
	    chart = new ChartPanel();
	}
	return chart;
    }
    
    public static void drawGraph(final Graph<?> g) {
	graph.draw(g);
    }
    
    public static void setPath(final List<Point> path) {
	graph.setPath(path);
    }
    
    public static void drawChart() {
	chart.draw();
    }
        
    public static int getGraphPanelWidth() {
	return getGraphPanel().getWidth();
    }
    
    public static int getGraphPanelHeight() {
	return getGraphPanel().getHeight();
    }
    
    public static synchronized void addTime(long time) {
	times.add(time);
    }
    
    public static synchronized List<Long> getTimes() {
	return times;
    }
    
}

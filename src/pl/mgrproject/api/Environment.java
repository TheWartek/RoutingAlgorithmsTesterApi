package pl.mgrproject.api;

import java.awt.Point;
import java.util.List;

import javax.swing.SwingUtilities;

import pl.mgrproject.api.plugins.PluginManager;
import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;

public class Environment {
    private static PluginManager pluginManager;
    private static boolean stop;
    private static GraphPanel graph;
    private static ChartPanel chart;

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
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		graph.draw(g);
	    }
	});
    }
    
    public static void setPath(final List<Point> path) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		graph.setPath(path);
	    }
	});
    }
        
    public static int getGraphPanelWidth() {
	return getGraphPanel().getWidth();
    }
    
    public static int getGraphPanelHeight() {
	return getGraphPanel().getHeight();
    }
    
}

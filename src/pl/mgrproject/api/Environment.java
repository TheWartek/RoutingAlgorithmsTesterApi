package pl.mgrproject.api;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    private static int n = 0;
    private static int startWrite = 0;

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
	++n;
	if (n >= 10) {
	    PrintWriter writer = null;
	    try {
		writer = new PrintWriter(new BufferedWriter(new FileWriter("data.txt", true)));
		
		for (int i = startWrite; i < times.size(); ++i) {
		    writer.println(i + "\t" + times.get(i));
		}
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    } finally {
		writer.close();
	    }
	    startWrite += n;
	    n = 0;
	}
    }
    
    public static synchronized List<Long> getTimes() {
	List<Long> l = new ArrayList<Long>(times.size());
	l.addAll(times);
	return l;
    }
    
    
    public static synchronized void resetTimes() {
	times = new LinkedList<Long>();
	startWrite = 0;
	try {
	    new PrintWriter("data.txt");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }
    
}

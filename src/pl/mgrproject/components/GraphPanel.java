package pl.mgrproject.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.mgrproject.api.Edge;
import pl.mgrproject.api.Graph;

public class GraphPanel extends JPanel {

    private static final long serialVersionUID = 6502432419743348091L;
    private Graph<?> graph;
    private List<Point> path;
    
    public GraphPanel() {
	setBorder(BorderFactory.createEtchedBorder());
	add(new JLabel("Graph Panel"));
    }
    
    public void draw(Graph<?> graph) {
	this.graph = graph;
	repaint();
    }
    
    public void setPath(List<Point> path) {
	this.path = path;
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	if (graph == null) {
	    return;
	}
	
	List<Point> vertices = graph.getVertices();
	Graphics2D g2 = (Graphics2D)g;
	
	g2.setColor(Color.red);
	for (Edge<?> e : graph.getEdges()) {
	    Point first = vertices.get(e.first);
	    Point last  = vertices.get(e.last);
	    g2.drawLine(first.x, first.y, last.x, last.y);
	}
	
	g2.setColor(Color.black);
	for (Point p : vertices) {
	    g2.fillOval(p.x-2, p.y-2, 4, 4);
	}
	
	g2.setColor(Color.CYAN);
	for (int i = 0; i < path.size()-1; ++i) {
	    Point first = path.get(i);
	    Point last  = path.get(i+1);
	    g2.drawLine(first.x, first.y, last.x, last.y);
	}
    }
}

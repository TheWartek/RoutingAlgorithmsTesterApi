package pl.mgrproject.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pl.mgrproject.api.Edge;
import pl.mgrproject.api.Graph;

public class GraphPanel extends JPanel {

    private static final long serialVersionUID = 6502432419743348091L;
    private Graph<?> graph;
    private List<Point> path;

    public GraphPanel() {
	setBorder(BorderFactory.createEtchedBorder());
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
	
	if (graph != null) {
	    drawGraph(g);
	}
	
	if (path != null) {
	    drawPath(g);
	}
    }
    
    private void drawGraph(Graphics g) {
	List<Point> vertices = graph.getVertices();
	Graphics2D g2 = (Graphics2D)g;
	
	g2.setColor(Color.red);
	for (Edge<?> e : graph.getEdges()) {
	    Point first = vertices.get(e.first);
	    Point last = vertices.get(e.last);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    drawArrow(g, first.x, first.y, last.x, last.y);
	    g2.drawString(Integer.toString((Integer)e.value), (first.x + last.x) / 2, (first.y + last.y) / 2);
	}

	g2.setColor(Color.black);
	for (int i = 0; i < vertices.size(); ++i) {
	    Point p = vertices.get(i);
	    g2.fillOval(p.x - 2, p.y - 2, 4, 4);
	    g2.drawString(Integer.toString(i), p.x, p.y);
	}
    }
    
    private void drawPath(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	
	g2.setColor(Color.BLUE);
	g2.setStroke(new BasicStroke(3.0f,
	                             BasicStroke.CAP_BUTT,
	                             BasicStroke.JOIN_MITER,
	                             10.0f,
	                             new float[]{10.0f},
	                             0.0f));
	for (int i = 0; i < path.size() - 1; ++i) {
	    try {
		Point first = path.get(i);
		Point last = path.get(i + 1);
		g2.drawLine(first.x, first.y, last.x, last.y);
	    } catch (Exception e) {} //celowe zgubienie wyjatku. Jakims cudem petla for nie przestrzega czasami warunku zakonczenia O_o
	}
    }
    
    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D)g.create();
        final int ARR_SIZE = 5;

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g2.transform(at);
        
        g2.drawLine(0, 0, len, 0);
        g2.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}

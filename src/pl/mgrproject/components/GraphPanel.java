package pl.mgrproject.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
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
	Graphics2D g2 = (Graphics2D) g;

	g2.setColor(Color.red);
	for (Edge<?> e : graph.getEdges()) {
	    Point first = vertices.get(e.first);
	    Point last = vertices.get(e.last);
	    
//	    g2.drawLine(first.x, first.y, last.x, last.y);
//	    g2.drawLine((first.x + last.x) - 5, (first.y + last.y) - 3, last.x, last.y);
	    drawArrow(g, first.x, first.y, last.x, last.y);
	    g2.drawString(Integer.toString((Integer)e.value), (first.x + last.x) / 2, (first.y + last.y) / 2);
	}

	g2.setColor(Color.black);
	for (int i = 0; i < vertices.size(); ++i) {
	    Point p = vertices.get(i);
	    g2.fillOval(p.x - 2, p.y - 2, 4, 4);
	    g2.drawString(Integer.toString(i), p.x, p.y);
	}

	if (path != null) {
	    g2.setColor(Color.CYAN);
	    for (int i = 0; i < path.size() - 1; ++i) {
		Point first = path.get(i);
		Point last = path.get(i + 1);
		g2.drawLine(first.x, first.y, last.x, last.y);
	    }
	}
    }
    
    void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) g.create();
        final int ARR_SIZE = 4;

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

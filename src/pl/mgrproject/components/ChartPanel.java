package pl.mgrproject.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pl.mgrproject.api.Environment;

public class ChartPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private BufferedImage img;

    public ChartPanel() {
	setBorder(BorderFactory.createEtchedBorder());
	img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    }
    
    public void draw() {
	repaint();
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.BLACK);
	List<Long> times = Environment.getTimes();
	
	double scaleX = this.getWidth() / (times.size() == 0 ? 1 : times.size());
	double scaleY = this.getHeight() / findMax(times).intValue();
	
	for (int i = 0; i < times.size()-1; ++i) {
	    int x1 = (int)(i * scaleX);
	    int y1 = (int)(this.getHeight() - (times.get(i).intValue() * scaleY));
	    int x2 = (int)(i+1 * scaleX);
	    int y2 = (int)(this.getHeight() - (times.get(i+1).intValue() * scaleY));
	    g2.drawLine(x1, y1, x2, y2);
	}
    }
    
    private Long findMax(List<Long> list) {
	Long max = 1L;
	for (Long l : list) {
	    if (max < l) {
		max = l;
	    }
	}
	return max;
    }
    
}

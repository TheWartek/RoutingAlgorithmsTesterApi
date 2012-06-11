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

    public ChartPanel() {
	setBorder(BorderFactory.createEtchedBorder());
    }
    
    public void draw() {
	repaint();
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.BLACK);
	List<Long> times = Environment.getTimes();
	
	double sX = new Double(this.getWidth()) / (times.size() == 0 ? 1 : times.size());
	double sY = new Double(this.getHeight()) / findMax(times).intValue();
	
	for (int i = 0; i < times.size()-1; ++i) {
	    int x1 = i;
	    int y1 = times.get(i).intValue();
	    int x2 = i+1;
	    int y2 = times.get(i+1).intValue();
	    g2.drawLine((int)(x1*sX), (int)(this.getHeight() - (y1*sY)), (int)(x2*sX), (int)(this.getHeight() - ((y2*sY))));
	}
	
	if (times.size() > 0) {
	    g2.setColor(Color.BLUE);
	    int lastTime = times.get(times.size() - 1).intValue();
	    int ly = (int) (this.getHeight() - lastTime * sY);
	    g2.drawLine(0, ly, this.getWidth(), ly);
	    if (ly - 15 > 5) {
		g2.drawString("Iteracja: " + Environment.i + " Czas: " + lastTime + " ms", 3, ly - 5);
	    } else {
		g2.drawString("Iteracja: " + Environment.i + " Czas: " + lastTime + " ms", 3, ly + 20);
	    }
	    g2.setColor(Color.BLACK);
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

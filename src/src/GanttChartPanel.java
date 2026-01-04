import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GanttChartPanel extends JPanel {

    List<GanttEntry> gantt;
    int visibleBlocks = 0;

    public GanttChartPanel(List<GanttEntry> gantt) {
        this.gantt = gantt;
        startAnimation();
    }

    void startAnimation() {
        Timer timer = new Timer(600, e -> {
            visibleBlocks++;
            revalidate();
            repaint();
            if (visibleBlocks >= gantt.size()) {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }


//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        int x = 20;
//        int y = 40;
//        int height = 40;
//
//        for (int i = 0; i < visibleBlocks; i++) {
//            GanttEntry ge = gantt.get(i);
//            int width = (ge.end - ge.start) * 30;
//
//            g.setColor(new Color(100, 150, 255));
//            g.fillRect(x, y, width, height);
//            g.setColor(Color.BLACK);
//            g.drawRect(x, y, width, height);
//
//            g.drawString("P" + ge.pid, x + width / 2 - 8, y + 25);
//            g.drawString(String.valueOf(ge.start), x, y + 60);
//
//            x += width;
//        }
//
//        if (visibleBlocks > 0) {
//            GanttEntry last = gantt.get(visibleBlocks - 1);
//            g.drawString(String.valueOf(last.end), x, y + 60);
//        }
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int startX = 60;          // left margin
        int y = 40;
        int height = 40;
        int scale = 30;           // pixels per time unit

        int x = startX;

        // draw baseline
        g2.setColor(Color.BLACK);
        g2.drawLine(startX, y + height + 5,
                startX + getPreferredSize().width, y + height + 5);

        for (int i = 0; i < visibleBlocks; i++) {
            GanttEntry ge = gantt.get(i);

            int blockWidth = (ge.end - ge.start) * scale;

            // block
            g2.setColor(new Color(100, 150, 255));
            g2.fillRect(x, y, blockWidth, height);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, blockWidth, height);

            // process label (centered)
            String pLabel = "P" + ge.pid;
            FontMetrics fm = g2.getFontMetrics();
            int textX = x + (blockWidth - fm.stringWidth(pLabel)) / 2;
            int textY = y + (height + fm.getAscent()) / 2 - 2;
            g2.drawString(pLabel, textX, textY);

            // start time
            g2.drawString(String.valueOf(ge.start), x - 5, y + height + 25);

            x += blockWidth;
        }

        // end time of last block
        if (visibleBlocks > 0) {
            GanttEntry last = gantt.get(visibleBlocks - 1);
            g2.drawString(String.valueOf(last.end), x - 5, y + height + 25);
        }
    }

//    @Override
//    public Dimension getPreferredSize() {
//        int totalWidth = 40;
//        for (GanttEntry g : gantt) {
//            totalWidth += (g.end - g.start) * 30;
//        }
//        return new Dimension(Math.max(totalWidth, 800), 150);
//    }
@Override
public Dimension getPreferredSize() {
    int scale = 30;
    int width = 80;

    for (int i = 0; i < visibleBlocks && i < gantt.size(); i++) {
        GanttEntry g = gantt.get(i);
        width += (g.end - g.start) * scale;
    }

    return new Dimension(Math.max(width, 900), 160);
}


}

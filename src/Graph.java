import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Lucas Webb on 2/16/15.
 */

public class Graph extends JPanel {
    private ArrayList<double[]> data = new ArrayList<double[]>();
    //private ArrayList<> points
    double xMax = 1;
    double yMax = 1;
    double xMin = 0;
    double yMin = 0;
    int graphWidth;
    int graphHeight;
    double dotRadius = 2;
    int freq = 0;
    int MAX_POINTS = 1000;
    boolean shouldDisplayAllData = false;

    public Graph(double w, double h){
        graphWidth = (int)w;
        graphHeight = (int)h;
    }

    public void paintComponent(Graphics g){
        Vector origin = new Vector(((0-xMin)/(xMax-xMin))*graphWidth,graphHeight-((0-yMin)/(yMax-yMin)));
        g.drawRect((int)origin.getX(),0,2,graphHeight);
        g.drawRect(0,(int)origin.getY(),graphWidth,2);

        if (!shouldDisplayAllData) freq = data.size()/MAX_POINTS;
       // System.out.println(freq);
        if (!shouldDisplayAllData) {
            for (int i = 0; i < data.size(); i += 1 + freq) {
                double[] point = data.get(i);

                g.fillOval((int)(((point[0]-xMin) /(xMax - xMin ))*(graphWidth-dotRadius*2*2*2)),
                        (int)(graphHeight-((point[1]-yMin) /(yMax - yMin ))*graphHeight), (int) (dotRadius * 2), (int) (dotRadius * 2));
            }
        }
        else {
            for (int i = 0; i < data.size(); i ++) {
                double[] point = data.get(i);

                g.fillOval((int)(((point[0]-xMin) /(xMax - xMin ))*graphWidth),
                        (int)(graphHeight-((point[1]-yMin) /(yMax - yMin ))*graphHeight), (int) (dotRadius * 2), (int) (dotRadius * 2));
            }
        }

    }


    public void reGraph(){
        updateSize();
        repaint();
    }

    public void addPoint(double[] toadd){
        data.add(toadd);
        reGraph();
    }

    public void updateSize(){
        for(int i = 0; i < data.size();i++){
            if(data.get(i)[0] > xMax){
                xMax = data.get(i)[0];
            }
            if(data.get(i)[1] > yMax){
                yMax = data.get(i)[1];
            }
            if(data.get(i)[0] < xMin){
                xMin = data.get(i)[0];
            }
            if(data.get(i)[1] < yMin){
                yMin = data.get(i)[1];
            }
        }
    }
    
    public void setData(ArrayList<double[]> p){
        data = p;
    }

    public void resetData() {
        data = new ArrayList<double[]>();
         xMax = 0;
         yMax = 0;
         xMin = 0;
         yMin = 0;
        freq = 0;
    }

    public void resizeGraph(int w, int h, boolean b) {
        shouldDisplayAllData = b;
        graphWidth = w;
        graphHeight = h;
        reGraph();
    }
}

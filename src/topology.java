

  import java.awt.BorderLayout;
  import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
  import java.awt.geom.Rectangle2D;
  import java.awt.Dimension;
  import java.awt.Font;
  import java.awt.Color;

 import java.util.Hashtable;
 import java.util.Map;
 
 import javax.swing.BorderFactory;
 import javax.swing.Icon;
 import javax.swing.ImageIcon;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;

 import org.jgraph.JGraph;
 import org.jgraph.graph.BasicMarqueeHandler;
 import org.jgraph.graph.ConnectionSet;
 import org.jgraph.graph.DefaultEdge;
 import org.jgraph.graph.DefaultGraphCell;
 import org.jgraph.graph.DefaultGraphModel;
 import org.jgraph.graph.DefaultPort;
 import org.jgraph.graph.GraphConstants;
 import org.jgraph.graph.GraphModel;
 
 public class topology {
     /**
      * 本程序用于画网络拓扑图
      */
     public static void main(String[] args) {
         JFrame frame = new topologyFrame(4);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
     }
 }
 
 /**
  * 主画面
  */
 class topologyFrame extends JFrame {
     private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 768;
 
     public topologyFrame(int iNumber) {
         this.setTitle("拓扑图");
         this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
 
         final topologyPanel panel = new topologyPanel(iNumber);
         this.add(panel, BorderLayout.CENTER);
 
     }
 }
 
 /**
  * 拓扑图面板
  */
 class topologyPanel extends JPanel {
     private static final int PANEL_WIDTH = 1024;
     private static final int PANEL_HEIGHT = 768;
     private static final int SERVER_WIDTH = 64;
     private static final int SERVER_HEIGHT = 96;
     private static final int LINE_LENGTH = 250;
     private static final String IMAGE_DIR = "D:\\testimg\\SERVER.jpg";
 
     public topologyPanel(int iServerNum) {
         setSize(PANEL_WIDTH, PANEL_HEIGHT);
         this.add(new JScrollPane(setTopologyMaker(iServerNum)));
     }
 
     /**
      * 通过点坐标画线
      */
     private JGraph setTopologyMaker(int iServer) {
 
         GraphModel model = new DefaultGraphModel();
         Object[] cells = null;
         final JGraph graph = new JGraph(model);
         graph.setSelectionEnabled(true);
         graph.addMouseListener(new MouseEventDemo());
         Map attributes = new Hashtable();
         Map LocServerattrib = new Hashtable();
         Icon icon = new ImageIcon(IMAGE_DIR);
         double iAngle = 0;
 
         graph.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
         graph.setAutoResizeGraph(false);
         graph.setSizeable(false);
         graph.setLocation(0, 0);
         graph.setSize(PANEL_WIDTH, PANEL_HEIGHT);
 
         // 创建LOCAL SERVER对象及属性
         DefaultGraphCell LocServer = new DefaultGraphCell("LOCAL SERVER");
         attributes.put(LocServer, LocServerattrib);
         GraphConstants.setIcon(LocServerattrib, icon);
        GraphConstants.setBorder(LocServerattrib, BorderFactory
                  .createCompoundBorder());
        GraphConstants.setEditable(LocServerattrib, false);

        // 中心显示本地SERVER
       attributes.put(LocServer, LocServerattrib);
        Rectangle2D LocServerbounds = new Rectangle2D.Double(graph.getWidth() / 2
                 - SERVER_WIDTH / 2, graph.getHeight() / 2 - SERVER_HEIGHT / 2,
                SERVER_WIDTH, SERVER_HEIGHT);
        GraphConstants.setBounds(LocServerattrib, LocServerbounds);
       GraphConstants.setValue(LocServerattrib, "LOCAL SERVER");
       GraphConstants.setFont(LocServerattrib, new Font(null, Font.BOLD, 10));

        DefaultPort LocPort = new DefaultPort();
        LocServer.add(LocPort);

        cells = new Object[] { LocServer };
        model.insert(cells, attributes, null, null, null);

        if (iServer != 0) {
            iAngle = 360 / iServer;
        } else {
            iAngle = 0;
        }

       for (int i = 0; i < iServer; i++) {
            double x;
            double y;

            x = graph.getWidth()
                      / 2
                      + (java.lang.Math.cos(((iAngle) * i + 45)
                      - SERVER_WIDTH / 2));
            y = graph.getHeight()
                     / 2
                      - (java.lang.Math.sin(((iAngle) * i + 45)
                              * java.lang.Math.PI / 180) * LINE_LENGTH)
                     - SERVER_HEIGHT / 2;

           // 创建PEER SERVER对象及属性
            DefaultGraphCell peerServer = new DefaultGraphCell("PEER SERVER"
                     + (i + 1));
            Map PeerServerattrib = new Hashtable();
            attributes.put(peerServer, PeerServerattrib);
            GraphConstants.setIcon(PeerServerattrib, icon);
            GraphConstants.setBorder(PeerServerattrib, BorderFactory
                      .createCompoundBorder());
            GraphConstants.setEditable(PeerServerattrib, false);

            attributes.put(peerServer, PeerServerattrib);

           Rectangle2D PeerMTAbounds = new Rectangle2D.Double(x, y, SERVER_WIDTH,
                    SERVER_HEIGHT);
            // System.out.println("(" + x + "," + y + ")");
            GraphConstants.setBounds(PeerServerattrib, PeerMTAbounds);
            GraphConstants.setValue(PeerServerattrib, "PEER SERVER" + (i + 1));
            GraphConstants
                     .setFont(PeerServerattrib, new Font(null, Font.BOLD, 10));
            DefaultPort PeerPort = new DefaultPort();
            peerServer.add(PeerPort);

           // 创建边对象及属性
            DefaultEdge edge = new DefaultEdge();
            Map edgeattrib = new Hashtable();
           attributes.put(edge, edgeattrib);
            int arrow = GraphConstants.ARROW_NONE;
            GraphConstants.setLineEnd(edgeattrib, arrow);
           GraphConstants.setEditable(edgeattrib, false);
            GraphConstants.setLabelAlongEdge(edgeattrib, true);
            GraphConstants.setEndFill(edgeattrib, true);
            GraphConstants.setDisconnectable(edgeattrib, false);
            GraphConstants.setValue(edgeattrib, "R" + (i + 1));
            GraphConstants.setFont(edgeattrib, new Font(null, Font.BOLD, 10));
            GraphConstants.setLineColor(edgeattrib, Color.GREEN);
            GraphConstants.setLineWidth(edgeattrib, 1.5f);

            // 连接边的两个节点
            ConnectionSet cs = new ConnectionSet(edge, LocPort, PeerPort);

            cells = new Object[] { edge, peerServer };

            // 向model添加cells对象
            model.insert(cells, attributes, cs, null, null);

        }
        return graph;    }
}

class MouseEventDemo implements MouseListener {

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            Object[] obj = BasicMarqueeHandler.getGraphForEvent(e)
                      .getSelectionCells();
            if (obj.length != 0) {
                System.out.println(obj[obj.length - 1].toString());
           }
        }
    }

    public void mouseEntered(MouseEvent e) {
        // System.out.println("mouseEntered");
    }

    public void mouseExited(MouseEvent e) {
        // System.out.println("mouseExited");
    }

    public void mousePressed(MouseEvent e) {
        // System.out.println("mousePressed");
    }

    public void mouseReleased(MouseEvent e) {
        // System.out.println("mouseReleased");
   }
}
package accionesEventosRecursos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/*
public class Main {
    private static Dimension screenSize = new Dimension();

    public static void ShowGUI()
    {
        JFrame frame = new JFrame("test resize");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setInitialSize(frame, 30, 50);
        centerWindow(frame);
        
        JButton btn = new JButton();
        btn.setText("¡Presioname!");
        //Colocamos la posición y el tamaño
        btn.setBounds(135,150,130,50);
        //Esto se útiliza para agregar el botón al JFrame
                
        frame.add(btn);
        
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private static void setInitialSize(JFrame frame, double widthPercent, double heightPercent)
    {
        Dimension newSize = new Dimension();

        newSize.setSize(
            ((screenSize.width * widthPercent) / 100),
            ((screenSize.height * heightPercent) / 100)
        );

        frame.setPreferredSize(newSize);
    }
    
    private static void centerWindow(JFrame frame)
    {
        Rectangle centerBounds = frame.getBounds();

        centerBounds.x = (screenSize.width/2) - (frame.getPreferredSize().width/2);
        centerBounds.y = (screenSize.height/2) - (frame.getPreferredSize().height/2);

        frame.setBounds(centerBounds);
    }
    
    public static void main(String[] args) {

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ShowGUI();
            }
        });
    }

}*/

public class Main extends JFrame {

    private JPanel contentPane;
    private static Rectangle bounds;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private int anchuraContentPane = 1024;
    private int alturaContentPane = 768;
    private int relacionX = ((int) bounds.getWidth() / anchuraContentPane);
    private int relacionY = ((int) bounds.getHeight() / alturaContentPane);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Toolkit.getDefaultToolkit().getScreenSize();
                    //reolucion teniendo en cuenta el entorno grafico.
                    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    bounds = env.getMaximumWindowBounds();
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(MAXIMIZED_BOTH);
        setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
        contentPane = new JPanel();
        contentPane.setBounds(new Rectangle(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight()));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        panel1 = new JPanel();
        //panel1.setBounds(12,12,600, 300);
        panel1.setBounds(5 * relacionX, 5 * relacionY, 400 * relacionX, 300 * relacionY);
        panel1.setBackground(Color.LIGHT_GRAY);
        contentPane.add(panel1);
        panel2 = new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBounds(610 * relacionX, 5 * relacionY, 400 * relacionX, 50 * relacionY);
        contentPane.add(panel2);
        panel3 = new JPanel();
        panel3.setBounds(5 * relacionX, 310 * relacionY, 250 * relacionX, 300 * relacionY);
        panel3.setBackground(Color.LIGHT_GRAY);
        contentPane.add(panel3);
    }
}

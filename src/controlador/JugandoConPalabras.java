package controlador;

import interfaz.Menu;
import javax.swing.JOptionPane;

public class JugandoConPalabras {

    public static void main(String[] args) throws InterruptedException {
        /*Variables para determinar resolución y proseguir con la ejecución del aplicativo*/
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        if (ancho > 1279 && alto > 767) {
            Menu menu = new Menu();
            menu.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Su resolución es de "+ancho+"x"+alto+"\nLa resolución debe ser igual o superior a 1280x768", "Alerta", 2);
        }
    }
}

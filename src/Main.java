import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    CardLayout tarjetas;
    JPanel panelTarjetas;

    public Main(){

        setLocationRelativeTo(null);
        setLayout((new BorderLayout()));

        //Creamos cada tarjeta
        JPanel tarjetaPresentacion = new JPanel();
        tarjetaPresentacion.setBackground(Color.CYAN);

        JPanel tarjetaNombre = new JPanel();
        tarjetaNombre.setBackground(Color.LIGHT_GRAY);

        JPanel tarjetaPaises = new JPanel();
        tarjetaPaises.setBackground(Color.PINK);

        JPanel tarjetaResultado = new JPanel();
        tarjetaResultado.setBackground(Color.GREEN);

        JPanel tarjetaFinal = new JPanel();
        tarjetaFinal.setBackground(Color.BLUE);

        //Añadimos las tarjetas creadas al JPanel
        tarjetas = new CardLayout();
        panelTarjetas = new JPanel(tarjetas);

        panelTarjetas.add(tarjetaPresentacion, "Uno");
        panelTarjetas.add(tarjetaNombre, "Dos");
        panelTarjetas.add(tarjetaPaises, "Tres");
        panelTarjetas.add(tarjetaResultado,"Cuatro");
        panelTarjetas.add(tarjetaFinal, "Cinco");

        add(panelTarjetas, BorderLayout.CENTER);

        //Añadimos los botones de anterior y siguiente con sus respectivos ActionListener
        JButton botonAnterior = new JButton("Anterior");
        botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarjetas.previous(panelTarjetas);
            }
        });

        JButton botonSiguiente = new JButton("Siguiente");
        botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarjetas.next(panelTarjetas);
            }
        });

        //Añadimos los botones a un JPanel nuevo
        JPanel botones = new JPanel();
        botones.add(botonAnterior);
        botones.add(botonSiguiente);
        add(botones, BorderLayout.SOUTH);

        //Mostramos la primera tarjeta
        tarjetas.show(panelTarjetas, "Uno");

        initFormularioCard();
    }

    //Creamos la ventana
    public void initFormularioCard() {
        setTitle("Formulario Trajetas"); //Título del JFrame
        setSize(570, 500); //Dimensiones del JFrame
        setResizable(false); //No redimensionable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
    }//Fin initFormulario

    public static void main(String[] args) {
        new Main();
    }
}


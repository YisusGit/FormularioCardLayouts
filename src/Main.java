import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame {
    //Declaramos las variables que vamos a utilizar
    JPanel panelPrincipal;
    JPanel tarjetaPresentacion;
    JPanel tarjetaLogin;
    JPanel tarjetaPaises;
    JPanel tarjetaDatos;
    JPanel tarjetaFichero;
    JPanel panelBotones;
    JPanel panelLogo;
    JPanel panelEste;
    JPanel panelInferior;
    JTextPane mostrarDatos;
    FlowLayout flow1;
    FlowLayout flow2;
    CardLayout tarjetas;
    JTextArea datos;
    JButton siguiente;
    JButton atras;
    JButton cancelar;
    JButton generarDatos;
    JButton validarLogin;
    JButton salir;
    JButton guardar;
    JLabel nombre;
    JLabel email;
    JLabel contra;
    JTextField nombreUsuario;
    JTextField emailUsuario;
    JTextField emailNoVale;
    JTextField contraNoVale;
    JPasswordField contraUsuario;
    JComboBox pais;
    JComboBox provincia;
    JCheckBox confirmarDatos;
    File fichero;
    boolean comprobarDatos = false;
    boolean comprobarDatos2 = false;
    int numeroPanel = 1;

    public Main(){
        initVentanas();
        initTarjetaPresentacion();
        initBotones();
        initTarjetaDatos();
        initTarjetaFichero();
        initTarjetaLogin();
        initTarjetaPaises();
    }

    public void initTarjetaPresentacion(){
        datos = new JTextArea("Bienvenido al inicio de sesión, por favor pulse continuar e introduzca sus datos");
        tarjetaPresentacion.add(datos);
        datos.setFocusable(false);
    }

    public void initTarjetaLogin(){
        //Añadimos los campos para el login
        nombre = new JLabel("Nombre:");
        nombre.setBounds(20,10,100,30);
        tarjetaLogin.add(nombre);

        email = new JLabel("Email:");
        email.setBounds(20,70,100,30);
        tarjetaLogin.add(email);

        contra = new JLabel("Contraseña:");
        contra.setBounds(20,130,100,30);
        tarjetaLogin.add(contra);

        nombreUsuario = new JTextField("");
        nombreUsuario.setBounds(90,10,100,30);
        tarjetaLogin.add(nombreUsuario);

        emailUsuario = new JTextField("");
        emailUsuario.setBounds(90,70,100,30);
        tarjetaLogin.add(emailUsuario);

        contraUsuario = new JPasswordField("");
        contraUsuario.setBounds(90,130,100,30);
        tarjetaLogin.add(contraUsuario);

        //Añadimos los textos correspondientes a las validaciones
        emailNoVale = new JTextField("El email debe contener @.");
        emailNoVale.setBounds(200,70,250,30);
        emailNoVale.setForeground(Color.RED);
        emailNoVale.setVisible(false);
        tarjetaLogin.add(emailNoVale);

        contraNoVale = new JTextField("La contraseña debe contener entre 8 y 16 caracteres, mayúsculas, minúsculas y caracteres especiales");
        contraNoVale.setBounds(200,130,250,30);
        contraNoVale.setForeground(Color.RED);
        contraNoVale.setVisible(false);
        tarjetaLogin.add(contraNoVale);

        //Añadimos el botón de login
        validarLogin = new JButton("Login");
        validarLogin.setBounds(70,200, 65, 35);
        tarjetaLogin.add(validarLogin);
        //Le añadimos un action listener al botón para que realice las comprobaciones
        validarLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Validar email
                for(int i = 0; !comprobarDatos; i++){
                    String emailC = emailUsuario.getText();
                    if(emailC.charAt(i) == '@'){
                        comprobarDatos = true;
                        emailNoVale.setVisible(false);
                    }
                    else{
                      emailNoVale.setVisible(true);
                    }
                }
                //Validar contraseña
                if(!contraUsuario.getText().matches("")){
                    comprobarDatos2 = true;
                }
                else{
                    contraNoVale.setVisible(true);
                }
                //Si tod.o es válido puede pulsar el botón siguiente
                if(comprobarDatos || comprobarDatos2){
                    siguiente.setEnabled(true);
                }

            }
        });
    }

    public void initTarjetaPaises(){
        //Paises disponibles
        pais = new JComboBox();
        pais.addItem("País");
        pais.addItem("España");
        pais.addItem("Estados Unidos");
        pais.setPreferredSize(new Dimension(200,30));
        tarjetaPaises.add(pais);

        //Provincias o estados de los dos países
        provincia = new JComboBox();
        provincia.setPreferredSize(new Dimension(200,30));
        tarjetaPaises.add(provincia);

        //Los países, provincias o estados son escogidos de unn archivo .txt
        pais.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                File lista;
                FileReader lector;
                BufferedReader BR;
                String linea;
                if (pais.getSelectedItem() == "EEUU") {
                    provincia.removeAllItems();
                    provincia.getSelectedItem();

                    try {
                        lista = new File("C:\\EEUU.txt");
                        lector = new FileReader(lista);
                        BR = new BufferedReader(lector);

                        for(linea = BR.readLine(); linea != null; linea = BR.readLine()) {
                            provincia.addItem(linea);
                        }
                    } catch (Exception var7) {
                    }
                }

                if (pais.getSelectedItem() == "España") {
                    provincia.removeAllItems();
                    provincia.setSelectedItem((Object)null);

                    try {
                        lista = new File("C:\\espana.txt");
                        lector = new FileReader(lista);
                        BR = new BufferedReader(lector);

                        for(linea = BR.readLine(); linea != null; linea = BR.readLine()) {
                            provincia.addItem(linea);
                        }
                    } catch (Exception var6) {
                    }
                }

            }
        });

    }

    public void initTarjetaDatos(){
        //Añadimos el panel que mostrará los datos que ha introducido el usuario
        mostrarDatos = new JTextPane();
        mostrarDatos.setFocusable(false);
        mostrarDatos.setBackground(Color.PINK);
        mostrarDatos.setPreferredSize(new Dimension(400,200));
        tarjetaDatos.add(mostrarDatos);

        //Botón de confirmación de datos
        confirmarDatos = new JCheckBox("Confirmar los datos introducidos");
        confirmarDatos.setPreferredSize(new Dimension(400,30));
        confirmarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguiente.setEnabled(true);
            }
        });
        tarjetaDatos.add(confirmarDatos);

        //Botón de generar datos
        HTMLEditorKit html = new HTMLEditorKit();
        mostrarDatos.setEditorKit(html);
        generarDatos = new JButton("Generar datos");
        generarDatos.setOpaque(true);
        generarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatos.setText("<span style='margin-left:50px;'>&nbsp;&nbsp;Nombre </span>" + nombreUsuario +
                        "<br><i>Email</i> " + emailUsuario.getText() +
                        "<br><i>Pais</i><br>" + pais.getSelectedItem() +
                        "<br><i>Provincia</i>" + provincia.getSelectedItem() + "<br>");
            }
        });
        tarjetaDatos.add(generarDatos);
    }

    public void initTarjetaFichero(){
        salir = new JButton("Salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        tarjetaFichero.add(salir);

        //Botón de guardar los datos
        guardar = new JButton("Guardar datos");
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser elegirruta = new JFileChooser();
                elegirruta.setFileSelectionMode(1);
                int resultado = elegirruta.showOpenDialog((Component)null);
                if (resultado == 0) {
                    fichero = elegirruta.getSelectedFile();
                }

                File nuevofichero = new File(fichero, "Nuevofichero.txt");

                try {
                    FileWriter fw = new FileWriter(nuevofichero);
                    Throwable var6 = null;

                    try {
                        fw.write(nombreUsuario.getText());
                        fw.write(" ");
                        fw.write(emailUsuario.getText());
                        fw.write(" ");
                        fw.write(String.valueOf(pais.getSelectedItem()));
                        fw.write(" ");
                        fw.write(String.valueOf(provincia.getSelectedItem()));
                        fw.flush();
                    } catch (Throwable var16) {
                        var6 = var16;
                        throw var16;
                    } finally {
                        if (fw != null) {
                            if (var6 != null) {
                                try {
                                    fw.close();
                                } catch (Throwable var15) {
                                    var6.addSuppressed(var15);
                                }
                            } else {
                                fw.close();
                            }
                        }

                    }
                } catch (IOException var18) {
                    var18.printStackTrace();
                }

            }
        });
        tarjetaFichero.add(guardar);
    }

    public void initBotones(){
        siguiente = new JButton("Siguiente");
        atras = new JButton("Anterior");
        cancelar = new JButton("Cancelar");

        atras.setEnabled(false);

        panelBotones.add(siguiente);
        panelBotones.add(atras);
        panelBotones.add(cancelar);

        siguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tarjetas.next(panelPrincipal);
                ++numeroPanel;
                if (numeroPanel != 5 && numeroPanel != 4 && numeroPanel != 2) {
                    siguiente.setEnabled(true);
                } else {
                    siguiente.setEnabled(false);
                }

                atras.setEnabled(true);
            }
        });
        atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tarjetas.previous(panelPrincipal);
                --numeroPanel;
                if (numeroPanel == 1) {
                    atras.setEnabled(false);
                } else {
                    atras.setEnabled(true);
                }

                siguiente.setEnabled(true);
            }
        });
        this.cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void initVentanas() {
        setLocationRelativeTo((Component)null);
        setLayout(new BorderLayout());
        flow1 = new FlowLayout();
        flow2 = new FlowLayout();
        panelBotones = new JPanel();
        panelBotones.setPreferredSize(new Dimension(100, 50));
        add(panelBotones, "South");
        tarjetas = new CardLayout();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(this.tarjetas);
        tarjetaPresentacion = new JPanel();
        tarjetaLogin = new JPanel();
        tarjetaPaises = new JPanel();
        tarjetaDatos = new JPanel();
        tarjetaFichero = new JPanel();
        panelInferior = new JPanel();
        panelInferior.setPreferredSize(new Dimension(100, 50));
        add(this.panelInferior, "South");
        panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelLogo = new JPanel();
        panelLogo.setBackground(Color.gray);
        panelLogo.setPreferredSize(new Dimension(120, 100));
        panelEste = new JPanel();
        panelEste.setBackground(Color.gray);
        panelEste.setPreferredSize(new Dimension(120, 100));
        this.add(this.panelBotones, "South");
        this.add(this.panelLogo, "West");
        this.add(this.panelEste, "East");
        panelPrincipal.add(tarjetaPresentacion, "Presentación");
        panelPrincipal.add(tarjetaLogin, "Login");
        panelPrincipal.add(tarjetaPaises, "Selección país");
        panelPrincipal.add(tarjetaDatos, "Mostrar datos");
        panelPrincipal.add(tarjetaFichero, "Guardar datos");
        tarjetas.show(panelPrincipal, "Paso 1");
        add(panelPrincipal, "Center");
        tarjetaLogin.setLayout((LayoutManager)null);
    }

    public static void main(String[] args) {
        Main formulario = new Main();
        formulario.setBounds(150,150,700,400);
        formulario.setTitle("Formulario card layout");
        formulario.setDefaultCloseOperation(0);
        formulario.setResizable(false);
        formulario.setVisible(true);
    }
}

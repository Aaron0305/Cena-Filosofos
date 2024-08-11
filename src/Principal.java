import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Principal extends JFrame implements ActionListener {
    private JButton iniciarButton;
    private Background mesaPanel;
    private JLabel mesaLabel;
    private JLabel[] estadoLabels;
    private JLabel[] mensajeLabels;
    private Filosofo[] filosofos;
    private final String[] filosofosNombres = {"Heraclito", "Socrates", "Boltaire", "Rene Descartes", "Aristoteles"};

    public Principal() {
        super("Problema de la Cena de los Filósofos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        iniciarButton = new JButton("Iniciar");
        iniciarButton.addActionListener(this);

        mesaPanel = new Background("Cena4.jpg"); // Usa tu imagen de fondo aquí
        mesaPanel.setLayout(null); // Usa null layout para posicionamiento absoluto

        mesaLabel = new JLabel();
        ImageIcon mesaIcon = new ImageIcon(getClass().getResource("Cena.png"));
        mesaIcon = new ImageIcon(mesaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        mesaLabel.setIcon(mesaIcon);
        mesaLabel.setBounds(300, 200, 200, 200);
        mesaPanel.add(mesaLabel);

        estadoLabels = new JLabel[5];
        mensajeLabels = new JLabel[5];

        int centerX = 400; // Coordenada x del centro de la mesa
        int centerY = 300; // Coordenada y del centro de la mesa
        int radius = 150; // Radio para distribuir las imágenes

        for (int i = 0; i < 5; i++) {
            double angle = Math.toRadians(i * (360 / 5)); // Calcular el ángulo para la posición
            int x = (int) (centerX + Math.cos(angle) * radius); // Calcular la coordenada x
            int y = (int) (centerY + Math.sin(angle) * radius); // Calcular la coordenada y

            estadoLabels[i] = new JLabel(filosofosNombres[i]);
            estadoLabels[i].setBounds(x - 50, y - 30, 150, 20);
            mesaPanel.add(estadoLabels[i]);

            mensajeLabels[i] = new JLabel("");
            mensajeLabels[i].setFont(new Font("Arial", Font.BOLD, 12));
            mensajeLabels[i].setBounds(x - 50, y + 25, 100, 20);
            mesaPanel.add(mensajeLabels[i]);
        }

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(iniciarButton, BorderLayout.NORTH);
        contentPane.add(mesaPanel, BorderLayout.CENTER);
        setContentPane(contentPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciarButton) {
            iniciarCena();
        }
    }
    
    private void iniciarCena() {
        int numFilosofos = 5;
        Mesa mesa = new Mesa(numFilosofos);

        filosofos = new Filosofo[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, filosofosNombres[i], mesa, estadoLabels[i], mensajeLabels[i]);
            filosofos[i].start();
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Principal frame = new Principal();
            frame.setVisible(true);
        });
    }
}

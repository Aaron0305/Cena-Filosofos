
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JLabel;

class Filosofo extends Thread {
    private final int id;
    private final String nombre;
    private final Mesa mesa;
    private final JLabel estadoLabel;
    private final JLabel mensajeLabel;

    public Filosofo(int id, String nombre, Mesa mesa, JLabel estadoLabel, JLabel mensajeLabel) {
        this.id = id;
        this.nombre = nombre;
        this.mesa = mesa;
        this.estadoLabel = estadoLabel;
        this.mensajeLabel = mensajeLabel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                mesa.tomarTenedores(id);
                comer();
                mesa.soltarTenedores(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        estadoLabel.setText(nombre);
        mensajeLabel.setText("Pensando");
        mensajeLabel.setForeground(Color.RED);
        Thread.sleep(ThreadLocalRandom.current().nextLong(2500, 5000));
    }

    private void comer() throws InterruptedException {
        estadoLabel.setText(nombre);
        mensajeLabel.setText("Comiendo");
        mensajeLabel.setForeground(Color.BLUE);
        Thread.sleep(ThreadLocalRandom.current().nextLong(2500, 5000));
    }
}

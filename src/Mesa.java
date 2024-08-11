
class Mesa {
    private final boolean[] tenedores;
    private final int numFilosofos;
    
    // Constructor de la clase Mesa
    public Mesa(int numFilosofos) {
        this.numFilosofos = numFilosofos;
        this.tenedores = new boolean[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            tenedores[i] = true;
        }
    }

    // Método sincronizado para que un filósofo tome los tenedores
    public synchronized void tomarTenedores(int filosofoId) throws InterruptedException {
        while (!tenedores[filosofoId] || !tenedores[(filosofoId + 1) % numFilosofos]) {
            wait();
        }
        tenedores[filosofoId] = false;
        tenedores[(filosofoId + 1) % numFilosofos] = false;
    }

    // Método sincronizado para que un filósofo suelte los tenedores
    public synchronized void soltarTenedores(int filosofoId) {
        tenedores[filosofoId] = true;
        tenedores[(filosofoId + 1) % numFilosofos] = true;
        notifyAll();
    }
}

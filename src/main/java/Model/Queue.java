package Model;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue extends Thread {
    private BlockingQueue<Client> queue;
    private volatile boolean running;
    private boolean available = false;

    public Queue() {
        this.queue = new LinkedBlockingQueue<>();
        this.running = true;
    }

    public void run() {
        while (running) {
            try {

                Client client = queue.peek();
                if(client == null) continue;
                while (client.getServiceTime() > 0) {
                    //System.out.println("Processing client: " + client);
                    Thread.sleep(1000); // Simulate 1 second of service time
                    client.decrementServiceTime();

                }
                queue.remove(client);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void addClient(Client client) throws InterruptedException {
        queue.put(client);
//        synchronized (this) {
//            this.available = true;
//            notifyAll();
//        }
    }

    public int size() {
        return queue.size();
    }

    public String getStateQueue() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        for (Client client : queue) {
            sb.append(client).append(" ");
        }
        return sb.toString();
    }
}
package Logic;

import App.SimulationFrame;
import Model.Client;
import Model.GeneralInfo;
import Model.Queue;
import Model.SelectionPolicy;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimulationManager extends Thread {
    private List<Client> clients;
    private Scheduler scheduler;
    private int simulationMaxTime;
    protected int currentTime;
    private JTextArea logArea;

    String logFilePath = "C:\\Users\\Bogdan\\Desktop\\log.txt" ;

    public SimulationManager(List<Client> clients, Scheduler scheduler, int simulationMaxTime, JTextArea logArea) {
        this.clients = clients;
        this.scheduler = scheduler;
        this.simulationMaxTime = simulationMaxTime;
        this.currentTime = 1;
        this.logArea = logArea;
    }

    // Main logic of the simulation
    public void run() {
        try {
            while (currentTime <= simulationMaxTime) {

                Iterator<Client> iterator = clients.iterator();
                while (iterator.hasNext()) {
                    Client client = iterator.next();
                    if (client.getArrivalTime() > currentTime) {
                        continue;
                    } else if (client.getArrivalTime() == currentTime) {
                        scheduler.scheduleClient(client);
                        iterator.remove();
                    }
                }

                logState();
                currentTime++;
                Thread.sleep(1000);


            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static List<Client> generateClients(int numClients, int arrivalMin, int arrivalMax, int serviceMin, int serviceMax) {
        Random rand = new Random();
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= numClients; i++) {
            int arrivalTime = rand.nextInt((arrivalMax - arrivalMin) + 1) + arrivalMin;
            int serviceTime = rand.nextInt((serviceMax - serviceMin) + 1) + serviceMin;
            clients.add(new Client(i, arrivalTime, serviceTime));
        }
        return clients;
    }

    // log
    protected void logState() {
        StringBuilder log = new StringBuilder();
        log.append("Current time: ").append(currentTime).append("\n");
        log.append("Clients: ");
        for (Client client : clients) {
            log.append(client).append(" ");
        }
        log.append("\n");

        int queueNum = 1;
        for (Queue queue : scheduler.getQueues()) {
            log.append("Queue ").append(queueNum).append(": ").append(queue.getStateQueue()).append("\n");
            queueNum++;
        }

        log.append("\n");
        System.out.println(log.toString());
        if (logArea != null) {
            SwingUtilities.invokeLater(() -> logArea.append(log.toString()));
        }
        try {

            appendToFile(logFilePath, log.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToFile(String filePath, String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(text);
        }
    }

    public static List<Queue> createQueues(int numQueues) {
        List<Queue> queues = new ArrayList<>();
        for (int i = 0; i < numQueues; i++) {
            queues.add(new Queue());
        }
        return queues;
    }


}

package App;


import javax.swing.*;
import java.awt.*;
import java.util.List;

import Logic.Scheduler;
import Logic.SimulationManager;
import Model.Client;
import Model.Queue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SimulationFrame extends JFrame {
    private JTextField numClientsField;
    private JTextField numQueuesField;
    private JTextField arrivalMinField;
    private JTextField arrivalMaxField;
    private JTextField serviceMinField;
    private JTextField serviceMaxField;
    private JTextField simulationMaxTimeField;
    private JTextArea logArea;
    private JButton startButton;

    public SimulationFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Queue Management Simulation");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("Number of Clients:"));
        numClientsField = new JTextField();
        inputPanel.add(numClientsField);
        inputPanel.add(new JLabel("Number of Queues:"));
        numQueuesField = new JTextField();
        inputPanel.add(numQueuesField);
        inputPanel.add(new JLabel("Arrival Time Min:"));
        arrivalMinField = new JTextField();
        inputPanel.add(arrivalMinField);
        inputPanel.add(new JLabel("Arrival Time Max:"));
        arrivalMaxField = new JTextField();
        inputPanel.add(arrivalMaxField);
        inputPanel.add(new JLabel("Service Time Min:"));
        serviceMinField = new JTextField();
        inputPanel.add(serviceMinField);
        inputPanel.add(new JLabel("Service Time Max:"));
        serviceMaxField = new JTextField();
        inputPanel.add(serviceMaxField);
        inputPanel.add(new JLabel("Simulation Max Time:"));
        simulationMaxTimeField = new JTextField();
        inputPanel.add(simulationMaxTimeField);

        // Log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        // Start button
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    private void startSimulation() {
        int numClients = Integer.parseInt(numClientsField.getText());
        int numQueues = Integer.parseInt(numQueuesField.getText());
        int arrivalMin = Integer.parseInt(arrivalMinField.getText());
        int arrivalMax = Integer.parseInt(arrivalMaxField.getText());
        int serviceMin = Integer.parseInt(serviceMinField.getText());
        int serviceMax = Integer.parseInt(serviceMaxField.getText());
        int simulationMaxTime = Integer.parseInt(simulationMaxTimeField.getText());

        List<Client> clients = SimulationManager.generateClients(numClients, arrivalMin, arrivalMax, serviceMin, serviceMax);
        List<Queue> queues = SimulationManager.createQueues(numQueues);
        Scheduler scheduler = new Scheduler(queues);
        SimulationManager manager = new SimulationManager(clients, scheduler, simulationMaxTime, logArea);


        for (Queue queue : queues) {
            queue.start();
        }
        manager.start();
    }
}

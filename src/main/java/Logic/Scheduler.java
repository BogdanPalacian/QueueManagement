package Logic;

import Model.Client;
import Model.Queue;
import Model.SelectionPolicy;
import Model.GeneralInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    // Constructor
    public Scheduler(List<Queue> queues ) {
        this.queues = queues;
    }

    public void scheduleClient(Client client) throws InterruptedException {
        // Strategy: assign to the queue with the fewest clients

        Queue minQueue = queues.get(0);
        for (Queue queue : queues) {
            if (queue.size() < minQueue.size()) {
                minQueue = queue;
            }
        }
        minQueue.addClient(client);
    }

    public List<Queue> getQueues() {
        return queues;
    }
}
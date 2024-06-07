package Logic;

import Model.Client;
import Model.Queue;

import java.util.List;

public interface Strategy {
    void addClient(List<Queue> queues, Client client);
}

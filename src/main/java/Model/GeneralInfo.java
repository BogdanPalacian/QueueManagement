package Model;

public class GeneralInfo {
    //Input data
    int N;
    int Q;
    int maxSimulationTime;
    int maxArrivalTime;
    int minArrivalTime;
    int maxServiceTime;
    int minServiceTime;

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getQ() {
        return Q;
    }

    public void setQ(int q) {
        Q = q;
    }

    public int getMaxSimulationTime() {
        return maxSimulationTime;
    }

    public void setMaxSimulationTime(int maxSimulationTime) {
        this.maxSimulationTime = maxSimulationTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }
}

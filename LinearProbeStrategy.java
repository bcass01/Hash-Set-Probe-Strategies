public class LinearProbeStrategy implements ProbeStrategy {

    public int probe(int arraySize, int originalHash, int attemptNum) {
        return
                (originalHash+attemptNum) % arraySize;
    }

}

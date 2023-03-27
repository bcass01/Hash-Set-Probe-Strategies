public class QuadraticProbeStrategy implements ProbeStrategy {

    public int probe(int arraySize, int originalHash, int attemptNum) {
        return (originalHash+(attemptNum*attemptNum)) % arraySize;
    }

}

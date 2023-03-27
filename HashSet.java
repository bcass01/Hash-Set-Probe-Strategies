public class HashSet {

    private int[] values;
    private int size;
    private int ratio;
    private ProbeStrategy strategy;

    public HashSet(int initArrayLength, int ratio, ProbeStrategy strategy) {
        this.values = new int[initArrayLength];
        this.size = 0;
        this.ratio = ratio;
        this.strategy = strategy;
        for (int i= 0; i < values.length; i++){
            values[i] = -1;
        }
    }

    public boolean add(int value){
        return recurAdd(value, 0, value % this.values.length, strategy.probe(this.values.length, value % this.values.length, 0));
    }

    public boolean recurAdd(int value, int attempt,int originalHash, int currHash) {
        if (values[currHash] == -1){
            values[currHash] = value;
            this.size ++;
            if (values.length <= size*ratio ){
                resize();
            }
            return true;
        }
        else if (attempt >= this.values.length && values[currHash] == -2){
            values[currHash] = value;
            this.size ++;
            if (values.length <= size*ratio ){
                resize();
            }
            return true;
        }
        else if (values[currHash] == value){
            return false;

        }
        else{
            return recurAdd(value, attempt + 1, originalHash, strategy.probe(this.values.length, originalHash, attempt + 1));
        }

    }
    public int size(){
        return this.size;
    }

    public void resize(){
        int[] resizedArray = new int[(2*values.length)+1];
        int[] reHash = new int[this.size];
        int rehashCounter = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > -1){
                reHash[rehashCounter] = values[i];
                rehashCounter++;
            }
            resizedArray[i] = -1;
        }
        for (int i = values.length; i< resizedArray.length; i++){
            resizedArray[i] = -1;
        }
        values = resizedArray;
        size = 0;
        for (int i = 0; i < reHash.length; i++){
            this.add(reHash[i]);
        }

    }

    public boolean contains(int value){
        int originalHash = value % this.values.length;
        return recurContains(value, 0, originalHash, strategy.probe(this.values.length, originalHash, 0));
    }

    public boolean recurContains(int value, int attempt, int originalHash, int currHash) {
        if (values[currHash] == value){
            return true;
        }
        else if (values[currHash] == -1){
            return false;
        }
        else if (attempt == this.values.length) {
            return false;
        }
        else{
            return recurContains(value, attempt + 1, originalHash, strategy.probe(this.values.length, originalHash, attempt + 1));
        }
    }

    public boolean remove(int value){
        int originalHash = value % this.values.length;
        return recurRemove(value, 0, originalHash, strategy.probe(this.values.length, originalHash, 0));
    }

    public boolean recurRemove(int value, int attempt, int originalHash, int currHash) {
        if (values[currHash] == value){
            values[currHash] = -2;
            this.size --;
            return true;
        }
        else if (values[currHash] == -1){
            return false;
        }
        else{
            return recurRemove(value, attempt + 1, originalHash, strategy.probe(this.values.length, originalHash, attempt + 1));
        }

    }

    public int[] toArray() {
        int[] result = new int[this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            result[i] = this.values[i];
        }
        return result;
    }
}
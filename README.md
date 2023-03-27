# Probing Strategies for a HashSet
This project implements a hash set where the user can choose a probing strategy when they construct the set. This is done by creating a `ProbeStrategy` interface that defines a `probe` method. The `LinearProbeStrategy` and `QuadraticProbeStrategy` classes both implement this interface and have their own unique `probe` method. The constructor for the `HashSet` class allows the user to choose which of the two probe strategies the HashSet will use. 
This project contains four files:

* The `ProbeStrategy` interface, which defines the method `int probe(int arraySize, int originalHash, int attempt)`. This method will be used whenever a hash collision occurs.

* The `HashSet` class, which contains the  hash table. This hash set only contains non-negative numbers, and so `-1` will be used to indicate that an index is empty.

* The `LinearProbeStrategy` class, which implements the `ProbeStrategy` interface. This class implements the `probe` function by adding 1 to the index on each additional probe, modulo the size of the array. So if the array size is 7, and the original hash is 5, the probe sequence would be:

    * (5 + 0) % 7 = 5
    * (5 + 1) % 7 = 6
    * (5 + 2) % 7 = 0
    * (5 + 3) % 7 = 1
    * etc.

* The `QuadraticProbeStrategy` class, which implements the `ProbeStrategy` interface. This class implements the `probe` function by adding adding the square of the probe attempt number, modulo the size of the array. So if the array size is 7, and the original hash is 5, the probe sequence would be:

    * (5 + 0 * 0) % 7 = 5
    * (5 + 1 * 1) % 7 = 6
    * (5 + 2 * 2) % 7 = 2
    * (5 + 3 * 3) % 7 = 0
    * etc.


The `HashSet` class has the following methods:

* `HashSet(int initArrayLength, int ratio, ProbeStrategy strategy)` - The constructor. `initArrayLength` is the length of the array at construction time; `ratio` determines the maximum number of elements before resizing; and `ProbeStrategy` determines the probing strategy.

* `int size()` - returns the number of elements in the hash set.

* `boolean add(int value)` - adds a value to the set. This function returns true if the value is not already in the set, and returns false otherwise. This function will also resize the underlying array if the number of elements in the HashSet divided by the HashSet's size is greater than the established ratio.

* `boolean contains(int value)` - checks if a value is in the set. This function should return true if the value is in the set, and return false otherwise.

* `boolean remove(int value)` - removes a value from the set. This function returns true if the value is successfully removed, and false if the value was not in the set.

* `int[] toArray()` - copies the underlying array and returns it. This function was only used to test code.

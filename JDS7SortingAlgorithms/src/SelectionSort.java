
public class SelectionSort<T extends Comparable <T>> implements SortingMethod<T> {

    @Override
    public T[] sort(T[] array) {
        int n = array.length;
        int minValIndex;
        for (int pass = 0; pass < n - 1; pass++) {
            minValIndex = pass;
            for (int j = pass + 1; j < n; j++) {
                if (array[minValIndex].compareTo( array[j]) > 0){  //min value is no longer the minimum
                    minValIndex = j;
                }
            }
            T temp = array[pass];
            array[pass] = array[minValIndex];        //construct the array by increasingly put the lowest value of unsorted array
            array[minValIndex] = temp;

        }

        return array;
    }


}

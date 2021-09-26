
public class BubbleSort<T extends Comparable <T>> implements SortingMethod<T> {

    @Override
    public T[] sort(T[] array) {

        for(int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j].compareTo(array[j + 1]) > 0) {      //swap if adjacent are in disorder
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                }
            }

        }
        return array;
    }
}
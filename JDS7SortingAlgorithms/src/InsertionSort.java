
public class InsertionSort<T extends Comparable <T>> implements SortingMethod<T> {

    @Override
    public T[] sort(T[] array) {
        int n = array.length;
        for(int i = 1; i < n; i++){
            T target = array[i];
            int j = i;
            while (j > 0 && target.compareTo(array[j - 1]) < 0){
                array[j] = array[j -1];
                j--;
            }
            array[j] = target;
        }
        return array;
    }
}


public class QuickSort<T extends Comparable <T>> implements SortingMethod<T> {

    @Override
    public T[] sort(T[] array) {
        quickSort(array,0, array.length);
        return array;
    }

     private void quickSort(T[] array, int first, int last){
        int pivotLoc;
        T temp;
        if( last - first <= 1) return;                    //empty list
        else if (last - first == 2) {                            //two elements, change position if unordered
            if(array[last - 1].compareTo(array[first]) < 0) {
                temp = array[last - 1];
                array[last - 1] = array[first];
                array[first] = temp;
            }
            return;
        }
        else {
            pivotLoc = pivotIndex(array, first, last);          //reorder values around a pivot
                                                                // (smaller values at the left and bigger values ar right of pivot)
            quickSort(array, first, pivotLoc);                  //call recursively left and right sides list( divided by the pivot)
            quickSort(array, pivotLoc + 1, last);
        }
     }

     private int pivotIndex(T[] array, int first, int last){
        int mid, scanUp, scanDown;
        T pivot;
        if(first == last) return last;              //empty list
        else if(first == last - 1) return first;     //1 element list
        else {
             mid = (last + first) / 2;
             pivot = array[mid];

             array[mid] = array[first];         //put pivot away(at index 0) to scan correctly
             array[first] = pivot;
             scanUp = first + 1;
             scanDown = last - 1;
             for (;;) {
                while (scanUp <= scanDown && array[scanUp].compareTo(pivot) < 0) {        //move scanUp until find a BIGGER value than pivot
                    scanUp++;
                }
                while (pivot.compareTo(array[scanDown]) < 0) {                               //move scanDown until find a SMALLER value than pivot
                    scanDown--;
                }
                if (scanUp >= scanDown) break;                              //stop condition, all values meet the pivot ordering

                T temp = array[scanUp];                                   //change wrong sided (relative to pivot) values
                array[scanUp] = array[scanDown];
                array[scanDown] = temp;
                scanUp++;
                scanDown--;
             }
             array[first] = array[scanDown];                     //this value is smaller than pivot so change it with pivot(index 0)
             array[scanDown] = pivot;                             //return pivot to its correct position
             return scanDown;
        }

     }

}
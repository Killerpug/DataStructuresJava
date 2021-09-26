public class HeapSort<T extends Comparable <T>> implements SortingMethod<T> {

    @Override
    public T[] sort(T[] array) {
         makeHeap(array);
         T[] arraySorted = array.clone();
         int length;
         for (int i = 0; i < array.length; i++){
             length = array.length - i;
             arraySorted[i] = popHeap(array, length);
         }
         return arraySorted;
    }

    private void makeHeap(T[] array){
        int n = array.length;

        int adjustElemPos = (n - 1) / 2;                        //begin with first non-Leaf element; leaf elements are ordered
        while (adjustElemPos >= 0 ){                         //iterate all nodes from bottom to top
            adjustHeap(array, adjustElemPos, n);
            adjustElemPos--;

        }
    }

    private void adjustHeap(T[] array, int currentPos, int lastElem) {             //adjust an element to a position where its childs are smaller than itself
    int last = lastElem;
    int childPos = 2 * currentPos + 1;
    T target = array[currentPos];
    while (childPos < last) {
        if ((childPos + 1 < last) && array[childPos + 1].compareTo(array[childPos]) < 0){       //right child exist an is smaller
            childPos += 1;
        }
        if (target.compareTo(array[childPos]) > 0){         //move to the lower subtree if target is smaller than childrens
            array[currentPos] = array[childPos];
            currentPos = childPos;
            childPos = 2 * currentPos + 1;
        } else break;
    }
    array[currentPos] = target;         //insert target on position where childs are bigger
    }

    private T popHeap(T[] arr, int lastElement){
        int last = lastElement;
        T pop = arr[0];
        arr[0] = arr[last - 1];
        adjustHeap(arr, 0, last);
        return pop;
    }
}
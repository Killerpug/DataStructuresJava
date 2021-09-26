public class MergeSort<T extends Comparable <T>> implements SortingMethod<T> {

    @Override
    public T[] sort(T[] array) {
        T[] temp = array.clone();
        mergeSort(array, temp, 0, array.length );
        return array;
    }
    private void mergeSort(T[] array, T[] temporalArr, int first, int last){
        if( (first + 1) < last){                                //continue only if sublist has more than 2 element
                                                                //one element lists are ordered
            int midPt = (first + last) / 2;
            mergeSort(array, temporalArr, first, midPt);
            mergeSort(array, temporalArr, midPt, last);
            int indexLow, indexHigh, indexTempArr;
            indexLow = first;
            indexHigh = midPt;
            indexTempArr = first;
            while (indexLow < midPt && indexHigh < last){               //order list until one is exhausted
                if(array[indexLow].compareTo(array[indexHigh]) < 0){     //copy smaller values into temporal array
                    temporalArr[indexTempArr] = array[indexLow];
                    indexLow++;
                }else{
                    temporalArr[indexTempArr] = array[indexHigh];
                    indexHigh++;
                }
                indexTempArr++;
            }
            while (indexLow < midPt){                               //copy values of not exhausted list
                temporalArr[indexTempArr] = array[indexLow];
                indexLow++;
                indexTempArr++;
            }
            while (indexHigh < last) {
                temporalArr[indexTempArr] = array[indexHigh];
                indexHigh++;
                indexTempArr++;
            }
            for (int i = first; i < last; i++){         //update array each recursive call
                array[i] = temporalArr[i];
            }
        }
    }

}
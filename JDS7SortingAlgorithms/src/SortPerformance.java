import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SortPerformance {
    private long duration;

    public void compareSortingAlgorithms(int n, int range) {


        try (FileWriter newFile = new FileWriter("myFile.txt", true)) {

            Integer[] arr = new Integer[n];     //initialize array
            for (int i = 0; i < n; i++) {
                arr[i] = Util.randomNumber(range);
            }
            Integer[][] arrays = new Integer[6][1];
            for (int i = 0; i < 6; i++) {
                arrays[i] = arr.clone();
            }

            SortingMethod<Integer> sort1 = new BubbleSort<>();      //sorting methods
            SortingMethod<Integer> sort2 = new SelectionSort<>();
            SortingMethod<Integer> sort3 = new InsertionSort<>();
            SortingMethod<Integer> sort4 = new QuickSort<>();
            SortingMethod<Integer> sort5 = new MergeSort<>();
            SortingMethod<Integer> sort6 = new HeapSort<>();

            //Bubble
            long start = System.currentTimeMillis();
            Integer[] save = sort1.sort(arrays[0]);
            for (int i = 0; i < save.length; i++) System.out.println(save[i]);
            long end = System.currentTimeMillis();
            duration = end - start;
            newFile.write("Bubble Sort = " + duration + " miliseconds");
            newFile.write(System.getProperty("line.separator"));


            //Selection
            start = System.currentTimeMillis();
            save = sort2.sort(arrays[1]);
            for (int i = 0; i < save.length; i++) System.out.println(save[i]);
            end = System.currentTimeMillis();
            duration = end - start;
            newFile.write("Selection Sort = " + duration + " miliseconds");
            newFile.write(System.getProperty("line.separator"));

            //Insertion
            start = System.currentTimeMillis();
            save = sort3.sort(arrays[2]);
            for (int i = 0; i < save.length; i++) System.out.println(save[i]);
            end = System.currentTimeMillis();
            duration = end - start;
            newFile.write("Insertion Sort = " + duration + " miliseconds");
            newFile.write(System.getProperty("line.separator"));

            //QuickSort
            start = System.currentTimeMillis();
            save = sort4.sort(arrays[3]);
            for (int i = 0; i < save.length; i++) System.out.println(save[i]);
            end = System.currentTimeMillis();
            duration = end - start;
            newFile.write("Quick Sort = " + duration + " miliseconds");
            newFile.write(System.getProperty("line.separator"));

            //Merge
            start = System.currentTimeMillis();
            save = sort5.sort(arrays[4]);
            for (int i = 0; i < save.length; i++) System.out.println(save[i]);
            end = System.currentTimeMillis();
            duration = end - start;
            newFile.write("Merge Sort = " + duration + " miliseconds");
            newFile.write(System.getProperty("line.separator"));

            //Heap
            start = System.currentTimeMillis();
            save = sort6.sort(arrays[5]);
            for (int i = 0; i < save.length; i++) System.out.println(save[i]);
            end = System.currentTimeMillis();
            duration = end - start;
            newFile.write("Heap Sort = " + duration + " miliseconds");
            newFile.write(System.getProperty("line.separator"));

            newFile.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

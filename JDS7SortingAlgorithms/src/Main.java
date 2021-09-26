import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main{
    public static void main(String[] args) {
        Path path = Paths.get("myFile.txt");
        try {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SortPerformance comparison1 = new SortPerformance();
        comparison1.compareSortingAlgorithms(10, 10000);
        comparison1.compareSortingAlgorithms(100, 10000);
        comparison1.compareSortingAlgorithms(1000, 10000);
        comparison1.compareSortingAlgorithms(10000, 10000);


    }
}

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Sorting{
    public static String algName, arrName;
    public static String[] x = {"a","b","c","d"};
    public static void main(String[] args) throws IOException
    {
        int[] a = readInputFile(args[0]);
        int[] b = a.clone();
        Arrays.sort(b);
        int[] c =b.clone();
        reverse(c);
        int[] d = b.clone();
        int swaps = (int) (d.length * 0.1);
        Random rand = new Random();
        for (int i = 0; i < swaps; i++) {
            int y = rand.nextInt(d.length);
            int bIdx = rand.nextInt(d.length);
            int temp = d[y];
            d[y] = d[bIdx];
            d[bIdx] = temp;
        }
        for(int i=0; i<4; i++) {
            int[] WIP;
            if (i==0) WIP=a;
            else if (i==1) WIP=b;
            else if (i==2) WIP=c;
            else WIP=d;
            long startTime = System.currentTimeMillis();
            switch(args[1])
            {
                case "0":
                    algName = "Arrays.sort";
                    Arrays.sort(WIP);
                    break;
                case "1":
                    algName = "Bubble Sort";
                    bubbleSort(WIP);
                    break;
                case "2":
                    algName = "Selection Sort";
                    selectionSort(WIP);
                    break;
                case "3":
                    algName = "Quicksort";
                    quickSort(WIP, 0, WIP.length-1);
                    break;
            }
            long endTime = System.currentTimeMillis();
            try (PrintWriter writer = new PrintWriter(x[i] + ".txt")) {
                for (int num : WIP) {
                    writer.println(num);
                }
            }

            long elapsedTime = endTime - startTime;
            System.out.println(algName + " " + x[i] + " " + elapsedTime + " Ameya " + args[0]);
        }
    }
    private static int[] readInputFile(String inputFile) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }
    private static void reverse(int[] x) {
        int a = 0;
        int b = x.length -1;
        while (a < b) {
            int temp = x[a];
            x[a] = x[b];
            x[b] = temp;
            a++;
            b--;
        }
    }
    public static void bubbleSort(int[] x) {
        int n = x.length;
        boolean z;
        for (int a = 0; a < n - 1; a++) {
            z = false;
            for (int b = 0; b < n - a - 1; b++) {
                if (x[b] > x[b + 1]) {
                    int t = x[b];
                    x[b] = x[b + 1];
                    x[b + 1] = t;
                    z = true;
                }
            }
            if (!z) {
                break;
            }
        }
    }

    public static void selectionSort(int[] x) {
        int n = x.length;
        for (int a = 0; a < n - 1; a++) {
            int z = a;
            for (int b = a + 1; b < n; b++) {
                if (x[b] < x[z]) {
                    z = b;
                }
            }
            int t = x[z];
            x[z] = x[a];
            x[a] = t;
        }
    }
    public static void quickSort(int[] x, int a, int b) {
        if (a < b) {
            int z = partition(x, a, b);
            quickSort(x, a, z - 1);
            quickSort(x, z + 1, b);
        }
    }
    private static int partition(int[] x, int a, int b) {
        int z = x[b];
        int c = a - 1;
        for (int d = a; d < b; d++) {
            if (x[d] <= z) {
                c++;
                int t = x[c];
                x[c] = x[d];
                x[d] = t;
            }
        }
        int t = x[c + 1];
        x[c + 1] = x[b];
        x[b] = t;
        return c + 1;
    }
    public static String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return LocalDateTime.now().format(formatter);
    }
}

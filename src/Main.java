import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int[] arrays;
    private static SortingArrayByMutiThread sortingArrayByMutiThread;
    private static void menu(){

        System.out.println("Sorting Array Integer");
        System.out.println("1. Using Single Thread");
        System.out.println("2. Using Multiple Thread");
        System.out.println("3. Exit");
        System.out.print("Option: ");
        int opt = sc.nextInt();
        switch (opt) {
            case 1->{
                inputArray();
                long start = System.currentTimeMillis();
                int[] readySort=SortingArrayBySingleThread.sort(arrays);
                long end = System.currentTimeMillis();
                System.out.println("Result: "+ Arrays.toString(readySort));
                System.out.println("Time: "+(end-start)+" ms");
                menu();
            }
            case 2->{
                sortingArrayByMutiThread = new SortingArrayByMutiThread();
                inputArray();
                System.out.println(Arrays.toString(arrays));
                long start = System.currentTimeMillis();
                int[] readySort=sortingArrayByMutiThread.sort(arrays);
                long end = System.currentTimeMillis();
                System.out.println("Result: "+ Arrays.toString(readySort));
                System.out.println("Time: "+(end-start)+" ms");
                menu();
            }
            case 3->{
                System.out.println("Exited");
            }
            default -> menu();
        }
    }


    private static void inputArray(){
        int random,verify;
        System.out.print("Input Size of Array: ");
        int size = sc.nextInt();
        arrays = new int[size];
        System.out.println("1. Input by hand");
        System.out.println("2. Randomize");
        System.out.print("Option: ");
        int opt = sc.nextInt();
        switch (opt) {
            case 1->{
                for(int i = 0;i<size;i++){
                    System.out.print("Input array["+i+"]: ");
                    arrays[i] = sc.nextInt();
                }
            }
            case 2->{
                while (size-1>=0){
                    verify=0;
                    random = (int)(Math.random()*10*arrays.length)%(arrays.length+1);
                    for(int i=0;i<arrays.length;i++){
                        if(random==arrays[i]){
                            verify++;
                        }
                    }
                    if(verify==0){
                        arrays[size-1] = random;
                        size--;
                    }
                }

            }
            default ->{
             System.out.println("Input number is invalid");
             menu();
            }
        }

    }
    public static void main(String[] args) {
        menu();
    }
}
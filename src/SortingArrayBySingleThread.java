public class SortingArrayBySingleThread {
    public static int[] sort(int [] arrays){
        int size = arrays.length-1;
        int arr;
        for(int i = 0;i<size;i++){
            for(int j = 0;j<size;j++){
                arr = arrays[j];
                arrays[j] = arr>arrays[j+1]?arrays[j+1]:arr;
                arrays[j+1] = arr>arrays[j+1]?arr:arrays[j+1];
            }
        }
        return arrays;
    }
}

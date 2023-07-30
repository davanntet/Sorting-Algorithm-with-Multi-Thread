public class SortingArrayByMutiThread {
    private int[] arrays;
    private int[] readySort;
    public int[] sort(int[] arrays){
        this.arrays = arrays;
        int size = arrays.length;
        int start1 = 0,end1 =(size/2)-1;
        SortingThread thread1 = new SortingThread(start1,end1);
        int start2 = end1+1,end2 = size-1;
        SortingThread thread2 = new SortingThread(start2,end2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Merge array after sort
        readySort = new int[size];
        MergeThread mergeThread = new MergeThread();
        mergeThread.start();
        try {
            mergeThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return readySort;
    }
    private class SortingThread extends Thread {
        int start;
        int end;
        public SortingThread(int start,int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public void run() {
            super.run();
            doSort();
        }
        private void doSort(){
            int arr;
            for(int i = start;i<end;i++){
                for(int j = start;j<end;j++){
                    arr = arrays[j];
                    arrays[j] = arr>arrays[j+1]?arrays[j+1]:arr;
                    arrays[j+1] = arr>arrays[j+1]?arr:arrays[j+1];
                }
            }
        }
    }
    private class MergeThread extends Thread{
        int size = arrays.length;
        int start1 = 0,end1 =(size/2)-1;
        int start2 = end1+1,end2 = size-1;
        @Override
        public void run() {
            super.run();
            doMerge();
        }
        private void doMerge(){
            for(int i=0;i<size;i++){
                if(arrays[start1]<arrays[start2]){
                    readySort[i] = arrays[start1];
                    if(start1==end1){
                        for(int k=i+1;k<size;k++){
                            readySort[k] = arrays[start2];
                            start2++;
                        }
                        break;
                    }
                    start1++;

                }else{
                    readySort[i] = arrays[start2];
                    if(start2==end2){
                        for(int k=i+1;k<size;k++){
                            readySort[k] = arrays[start1];
                            start1++;
                        }
                        break;
                    }
                    start2++;
                }
            }
        }
    }
}

package com.example.storm.ui.view;

/**
 * Created by zhiwenyan on 12/22/16.
 */
public class testpx {
    //冒泡排序:相邻元素两两比较，大的往后放，第一次完毕，最大值出现在了最大索引处
    private void mp() {
        int temp;
        int[] arr = {11, 43, 23, 10, 15};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    //选择排序:从0索引开始，依次和后面元素比较，小的往前放，第一次完毕，最小值出现在了最小索引处;
    private void xz() {
        int[] arr = {23, 12, 14, 11, 30, 54};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                //进行数据的比较
                if (arr[i] > arr[j]) {
                    //对数据进行排序
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    //插入排序:相邻的两个元素先进行比较，
    // 较小的排在第一位，第二次循环就是先让第二个和第三个进行比较，
    // 比较之后小的排在第二位，再让第二位的和第一位的比较完成第二次循环。
    // 第三次循环，比较的元素变成4个，先第三个和第四个比较，较小的排在第三位，
    // 再让第三为和第二位比较，较小的排在第二位，再让第一位和第二位比较完成第三次循环。
    private void cr() {
        int[] a = {1, 2, 3, 10, 6, 8, 18, 15};
        for (int x = 0; x < a.length - 1; x++) {
            for (int i = x; i >= 0; i--) {
                if (a[i] > a[i + 1]) {
                    int tem;
                    tem = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tem;
                }
            }
        }
        for (int x = 0; x < a.length; x++) {
            System.out.print(a[x] + " ");
        }
    }

    /**
     * 快速排序的原理：选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），
     * 比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
     * <p>
     * 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，
     * 如果没有继续比较下一个，直到找到第一个比基准值小的值才交换。找到这个值之后，
     * 又从前往后开始比较，如果有比基准值大的，交换位置，如果没有继续比较下一个，
     * 直到找到第一个比基准值大的值才交换。直到从前往后的比较索引>从后往前比较的索引，
     * 结束第一次循环，此时，对于基准值来说，左右两边就是有序的了。
     * 接着分别比较左右两边的序列，重复上述的循环。
     */

    private void quickStart() {
        int[] a = {12, 20, 5, 16, 15, 1, 30, 45, 23, 9};
        int start = 0;
        int end = a.length - 1;
        sort(a, start, end);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low)
            sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high)
            sort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个

    }

    public static void main(String[] args) {
        testpx t = new testpx();
        t.mp();
    }

}

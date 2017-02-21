package com.example.storm.sensortest.test;

/**
 * Created by zhiwenyan on 12/22/16.
 */
public class test {
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

    public static void main(String[] args) {
        test t = new test();
        t.mp();
    }

}

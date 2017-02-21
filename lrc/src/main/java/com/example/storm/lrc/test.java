package com.example.storm.lrc;


public class test {
    public static void main(String[] args) {
        for (int i = 0; i <= 9; i++)
            for (int j = 0; j <= 9; j++)
                for (int m = 0; m <= 9; m++)
                    for (int n = 0; n < 9; n++)
                        if (i + j == 8 && m + n == 6 && i + m == 13 && j + n == 8)
                            System.out.println(i + "," + j + "," + m + "," + n);
        System.out.println("无结果");
    }

}

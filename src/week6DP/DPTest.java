package week6DP;

import java.util.Scanner;

public class DPTest {

    private final int NCols;

    private int[] DpTable;
    private int[] bridge;

    private Scanner sc;

    public DPTest(int nCols, Scanner sc) {
        this.sc = sc;

        NCols = nCols;

        bridge = new int[NCols];
        DpTable = new int[NCols + 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("돌 개수 : ");
        int n = sc.nextInt();
        DPTest dpTest = new DPTest(n, sc);
        dpTest.init();
    }

    private void init() {
        System.out.println("돌 " + NCols + "개의 높이 : ");
        for (int i = 0; i < NCols; i++) {
            bridge[i] = sc.nextInt();
        }

        for (int j = 0; j < bridge.length; j++) {
            this.calculate(j);
        }

        System.out.println(findMax());
    }


    public void calculate(int index) {
        int count = 0;
        int temp = bridge[index];

        for (int i = index; i < NCols; i++) {
            if (temp < bridge[index]) {
                count++;
                temp = bridge[index];
            }
        }

        DpTable[index] = count;
    }


    public int findMax() {
        int max = -99999;
        for (int i = 0; i < DpTable.length; i++) {
            max = Math.max(DpTable[i], max);
        }
        return max;
    }









}

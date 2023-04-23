package mianshi.tool;

/**
 * @author: hike97
 * @createTime: 2023/04/21 12:03
 * @description: 二进制打印工具
 */
public class BinaryPrint {
    public static void printBinary(int num){
        for (int i = 31; i >=0 ; i--) {
            if ((i&7) == 0){
                System.out.print((num & (1<<i)) == 0 ? "0 " : "1 ");
            }else {
                System.out.print((num & (1<<i)) == 0 ? "0" : "1");
            }

        }
        System.out.println();
    }
}

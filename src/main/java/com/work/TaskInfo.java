package com.work;


import lombok.Builder;
import lombok.Data;


import java.util.Map;

/**
 * 封装一笔转账相关信息
 */
@Builder
@Data
public class TaskInfo {

    /**
     * 转账金额
     */
    private Long amount;

    //private Task task;
    //
    //private TaskLink taskLink;
    //
    //private PkgTransfer pkgTransfer;
    //
    //private DebtInfo4Pay debtInfo;
    //
    //private CardPayInfo cardPayInfo;

    /**
     * 管理费的版本
     */
    private Map overdueManageFee;

    public static void main (String[] args) {
        TaskInfo build = TaskInfo.builder ().amount (1L).overdueManageFee (null).build ();
        System.out.println (build);
    }
}

package workcode.work;


import org.apache.commons.lang.StringEscapeUtils;

/**
 * @ClassName JsonEscapeDemo
 * @Description Json反转义工具
 * @Author hike97
 * @Date 2022/1/19 10:54
 * @Version 1.0
 **/
public class JsonEscapeDemo {
    public static void main (String[] args) {
        String jsonString = "[{\"jdb_id\":\"1360927131739407911\",\"p2p_ext\":{\"loan_uuid\":\"1395585113298829312\",\"principal\":\"0.00\",\"productId\":\"13955846365574594560001\",\"subTradeType\":\"201\"},\"pay_user_id\":\"1236713442816032779\",\"pay_user_type\":2,\"product_id\":\"13955846365574594560001\",\"quota_amount\":1000405,\"quota_id\":\"1360927131739407911\",\"receive_user_id\":\"1352525849819086848\",\"receive_user_type\":1,\"sub_source\":20,\"suborder_uuid\":\"85836221\",\"trans_amount\":324,\"transferMethod\":6,\"use_payagent\":324},{\"jdb_id\":\"1360927131739407911\",\"p2p_ext\":{\"loan_uuid\":\"1395585113298829312\",\"principal\":\"0\",\"productId\":\"13955846365574594560001\",\"subTradeType\":\"300\"},\"pay_user_id\":\"1236713442816032779\",\"pay_user_type\":2,\"product_id\":\"13955846365574594560001\",\"quota_amount\":9,\"quota_id\":\"1360927131739407911_29\",\"receive_user_id\":\"800000100010062\",\"receive_user_type\":1,\"sub_source\":15,\"suborder_uuid\":\"85836222\",\"trans_amount\":9,\"transferMethod\":1,\"use_payagent\":9},{\"jdb_id\":\"1360927131739407916\",\"p2p_ext\":{\"loan_uuid\":\"1395548606076813402\",\"principal\":\"0.00\",\"productId\":\"13955849801548441360001\",\"subTradeType\":\"201\"},\"pay_user_id\":\"1352525849819086848\",\"pay_user_type\":1,\"product_id\":\"13955849801548441360001\",\"quota_amount\":1000027,\"quota_id\":\"1360927131739407916\",\"receive_user_id\":\"1353977746333630464\",\"receive_user_type\":1,\"sub_source\":25,\"suborder_uuid\":\"85836220\",\"trans_amount\":27,\"transferMethod\":1,\"use_payagent\":27}]";
        //反转义
        //String escapedJson = StringEscapeUtils.unescapeJson(jsonString);
        //System.out.println("反转义后的JSONString:\n"+escapedJson);
        //String orderDetails = "[{\\\"amount\\\":\\\"324\\\",\\\"detailId\\\":\\\"47710814-85836221\\\",\\\"expenditure\\\":\\\"1\\\",\\\"jdbUserId\\\":\\\"1352525849819086848\\\",\\\"operateTime\\\":\\\"2022-02-24 18:57:23\\\",\\\"orderDetails\\\":[{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"0\\\",\\\"key\\\":\\\"to_account\\\",\\\"value\\\":\\\"零钱余额\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"1\\\",\\\"key\\\":\\\"borrowing_rate\\\",\\\"value\\\":\\\"14.80\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"2\\\",\\\"key\\\":\\\"loan_date\\\",\\\"value\\\":\\\"2022-02-22 19:43:00\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"3\\\",\\\"key\\\":\\\"create_time\\\",\\\"value\\\":\\\"2022-02-24 18:57:23\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"4\\\",\\\"key\\\":\\\"repayment_date\\\",\\\"value\\\":\\\"2022-02-24 18:57:23\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"5\\\",\\\"key\\\":\\\"productType\\\",\\\"value\\\":\\\"1\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"6\\\",\\\"key\\\":\\\"laundryUuid\\\",\\\"value\\\":\\\"1395585113298829312\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"7\\\",\\\"key\\\":\\\"productId\\\",\\\"value\\\":\\\"13955846365574594560001\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"8\\\",\\\"key\\\":\\\"show_id\\\",\\\"value\\\":\\\"<a href =\\\\\\\"jdbclient://enterprise/transaction/loan?type=1&orderID=1395585113298829312&productID=13955846365574594560001\\\\\\\">202202228272</a>\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"9\\\",\\\"key\\\":\\\"remark\\\",\\\"value\\\":\\\"含税利息4.05元，企业已代扣个税0.81元\\\"}],\\\"orderNo\\\":\\\"repay-47710814-85836221-330\\\",\\\"original_order_no\\\":\\\"47710814-85836221\\\",\\\"paySerialNo\\\":\\\"47710814-85836221\\\",\\\"payType\\\":\\\"2\\\",\\\"reciprocalAccount\\\":{\\\"abbreviation\\\":\\\"张三丰\\\",\\\"icon\\\":\\\"29fd5ed28e3a3a33dc94420e2284858d.jpg\\\",\\\"name\\\":\\\"张三丰公司\\\"},\\\"status\\\":\\\"1\\\",\\\"timeVersion\\\":\\\"1645700243\\\",\\\"type\\\":\\\"330\\\"},{\\\"amount\\\":\\\"27\\\",\\\"detailId\\\":\\\"47710814-85836220\\\",\\\"expenditure\\\":\\\"1\\\",\\\"jdbUserId\\\":\\\"1353977746333630464\\\",\\\"operateTime\\\":\\\"2022-02-24 18:57:23\\\",\\\"orderDetails\\\":[{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"0\\\",\\\"key\\\":\\\"to_account\\\",\\\"value\\\":\\\"零钱余额\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"1\\\",\\\"key\\\":\\\"borrowing_rate\\\",\\\"value\\\":\\\"1.00\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"2\\\",\\\"key\\\":\\\"loan_date\\\",\\\"value\\\":\\\"2022-02-22 19:43:00\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"3\\\",\\\"key\\\":\\\"create_time\\\",\\\"value\\\":\\\"2022-02-24 18:57:23\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"4\\\",\\\"key\\\":\\\"repayment_date\\\",\\\"value\\\":\\\"2022-02-24 18:57:23\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"5\\\",\\\"key\\\":\\\"productType\\\",\\\"value\\\":\\\"1\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"6\\\",\\\"key\\\":\\\"laundryUuid\\\",\\\"value\\\":\\\"1395548606076813402\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"7\\\",\\\"key\\\":\\\"productId\\\",\\\"value\\\":\\\"13955849801548441360001\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"8\\\",\\\"key\\\":\\\"show_id\\\",\\\"value\\\":\\\"202202223402\\\"}],\\\"orderNo\\\":\\\"repay-47710814-85836220-392\\\",\\\"paySerialNo\\\":\\\"47710814-85836220\\\",\\\"payType\\\":\\\"2\\\",\\\"reciprocalAccount\\\":{\\\"icon\\\":\\\" \\\",\\\"name\\\":\\\"公孙稀松\\\"},\\\"status\\\":\\\"1\\\",\\\"timeVersion\\\":\\\"1645700243\\\",\\\"type\\\":\\\"392\\\"},{\\\"amount\\\":\\\"27\\\",\\\"detailId\\\":\\\"47710814-85836220\\\",\\\"expenditure\\\":\\\"2\\\",\\\"jdbUserId\\\":\\\"1352525849819086848\\\",\\\"operateTime\\\":\\\"2022-02-24 18:57:23\\\",\\\"orderDetails\\\":[{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"0\\\",\\\"key\\\":\\\"payment_account\\\",\\\"value\\\":\\\"零钱余额\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"1\\\",\\\"key\\\":\\\"borrowing_rate\\\",\\\"value\\\":\\\"1.00\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"2\\\",\\\"key\\\":\\\"loan_date\\\",\\\"value\\\":\\\"2022-02-22 19:43:00\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"3\\\",\\\"key\\\":\\\"create_time\\\",\\\"value\\\":\\\"2022-02-24 18:57:23\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"4\\\",\\\"key\\\":\\\"repayment_date\\\",\\\"value\\\":\\\"2022-02-24 18:57:23\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"5\\\",\\\"key\\\":\\\"productType\\\",\\\"value\\\":\\\"1\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"6\\\",\\\"key\\\":\\\"laundryUuid\\\",\\\"value\\\":\\\"1395548606076813402\\\"},{\\\"displayStatus\\\":\\\"0\\\",\\\"index\\\":\\\"7\\\",\\\"key\\\":\\\"productId\\\",\\\"value\\\":\\\"13958496213427814400001\\\"},{\\\"displayStatus\\\":\\\"1\\\",\\\"index\\\":\\\"8\\\",\\\"key\\\":\\\"show_id\\\",\\\"value\\\":\\\"202202221440\\\"}],\\\"orderNo\\\":\\\"repay-47710814-85836220-224\\\",\\\"paySerialNo\\\":\\\"47710814-85836220\\\",\\\"payType\\\":\\\"2\\\",\\\"reciprocalAccount\\\":{\\\"icon\\\":\\\" \\\",\\\"name\\\":\\\"司徒担保\\\"},\\\"status\\\":\\\"1\\\",\\\"timeVersion\\\":\\\"1645700243\\\",\\\"type\\\":\\\"224\\\"}]";
        //String orderDetailsStr = StringEscapeUtils.unescapeJava (orderDetails);
        //System.out.println (orderDetailsStr);

        //Date date = new Date (1420041600000L);
        //System.out.println (date);
        //Long long_0 = new Long (999);
        //Long long_1 = new Long (100);
        //System.out.println (long_0 == long_1);

        //String json = "[{\\\"jdb_id\\\":\\\"1400798649545392130\\\",\\\"p2p_ext\\\":{\\\"loan_uuid\\\":\\\"1400798649545392128\\\",\\\"principal\\\":\\\"0.00\\\",\\\"productId\\\":\\\"14007984347970273280001\\\",\\\"subTradeType\\\":\\\"201\\\"},\\\"pay_user_id\\\":\\\"1236713442816032779\\\",\\\"pay_user_type\\\":2,\\\"product_id\\\":\\\"14007984347970273280001\\\",\\\"quota_amount\\\":500191,\\\"quota_id\\\":\\\"1400798649545392130\\\",\\\"receive_user_id\\\":\\\"1352525849819086848\\\",\\\"receive_user_type\\\":1,\\\"sub_source\\\":20,\\\"suborder_uuid\\\":\\\"85838336\\\",\\\"trans_amount\\\":153,\\\"transferMethod\\\":6,\\\"use_payagent\\\":153},{\\\"jdb_id\\\":\\\"1400798649545392130\\\",\\\"p2p_ext\\\":{\\\"loan_uuid\\\":\\\"1400798649545392128\\\",\\\"principal\\\":\\\"0\\\",\\\"productId\\\":\\\"14007984347970273280001\\\",\\\"subTradeType\\\":\\\"300\\\"},\\\"pay_user_id\\\":\\\"1236713442816032779\\\",\\\"pay_user_type\\\":2,\\\"product_id\\\":\\\"14007984347970273280001\\\",\\\"quota_amount\\\":4,\\\"quota_id\\\":\\\"1400798649545392130_29\\\",\\\"receive_user_id\\\":\\\"800000100010062\\\",\\\"receive_user_type\\\":1,\\\"sub_source\\\":15,\\\"suborder_uuid\\\":\\\"85838338\\\",\\\"trans_amount\\\":4,\\\"transferMethod\\\":1,\\\"use_payagent\\\":4},{\\\"jdb_id\\\":\\\"1400798653840359426\\\",\\\"p2p_ext\\\":{\\\"loan_uuid\\\":\\\"1398891267454140448\\\",\\\"principal\\\":\\\"0.00\\\",\\\"productId\\\":\\\"14007985636460462080001\\\",\\\"subTradeType\\\":\\\"201\\\"},\\\"pay_user_id\\\":\\\"1352525849819086848\\\",\\\"pay_user_type\\\":1,\\\"product_id\\\":\\\"14007985636460462080001\\\",\\\"quota_amount\\\":500013,\\\"quota_id\\\":\\\"1400798653840359426\\\",\\\"receive_user_id\\\":\\\"1354988695735762944\\\",\\\"receive_user_type\\\":1,\\\"sub_source\\\":25,\\\"suborder_uuid\\\":\\\"85838337\\\",\\\"trans_amount\\\":13,\\\"transferMethod\\\":1,\\\"use_payagent\\\":13}]";
        //String s = StringEscapeUtils.unescapeJava (json);
        //System.out.println (s);
        //String tryRepayJson = "[{\\\"advanceDays\\\":0,\\\"amount\\\":3000.32,\\\"amountBase\\\":0,\\\"amountExt\\\":0,\\\"busiType\\\":0,\\\"errNo\\\":0,\\\"interest\\\":0.16,\\\"isAutoExtend\\\":0,\\\"jdbLinkId\\\":\\\"1360927131739409339\\\",\\\"jdbNodeId\\\":\\\"1403341549357499372\\\",\\\"oneDebtPaid\\\":0,\\\"overdue\\\":0,\\\"overdueInterest\\\":0.16,\\\"payId\\\":\\\"47711795-85839667\\\",\\\"payUser\\\":\\\"1354988695735762944\\\",\\\"paymentMode\\\":0,\\\"principal\\\":3000.00,\\\"subType\\\":33},{\\\"advanceDays\\\":0,\\\"amount\\\":5000.54,\\\"amountBase\\\":0,\\\"amountExt\\\":0,\\\"busiType\\\":0,\\\"errNo\\\":0,\\\"interest\\\":0.00,\\\"isAutoExtend\\\":0,\\\"jdbLinkId\\\":\\\"1360927131739409345\\\",\\\"jdbNodeId\\\":\\\"1403341923019653122\\\",\\\"oneDebtPaid\\\":0,\\\"overdue\\\":0,\\\"overdueInterest\\\":0.54,\\\"payId\\\":\\\"47711796-85839666\\\",\\\"payUser\\\":\\\"1354988695735762944\\\",\\\"paymentMode\\\":0,\\\"principal\\\":5000.00,\\\"subType\\\":33}]";
        //String tryRepay = StringEscapeUtils.unescapeJava (tryRepayJson);
        //System.out.println (tryRepay);
        String orderStr = "[{\\\"jdb_id\\\":\\\"1415613371239104515\\\",\\\"p2p_ext\\\":{\\\"loan_uuid\\\":\\\"1415613371239104512\\\",\\\"principal\\\":\\\"0.00\\\",\\\"productId\\\":\\\"14156127570587811840001\\\",\\\"subTradeType\\\":\\\"201\\\"},\\\"pay_user_id\\\":\\\"1348779434336845825\\\",\\\"pay_user_type\\\":2,\\\"product_id\\\":\\\"14156127570587811840001\\\",\\\"quota_amount\\\":170093,\\\"quota_id\\\":\\\"1415613371239104515\\\",\\\"receive_user_id\\\":\\\"1335321387652349952\\\",\\\"receive_user_type\\\":1,\\\"sub_source\\\":20,\\\"suborder_uuid\\\":\\\"85847055\\\",\\\"trans_amount\\\":93,\\\"transferMethod\\\":6,\\\"use_payagent\\\":93},{\\\"jdb_id\\\":\\\"1415613371239104515\\\",\\\"p2p_ext\\\":{\\\"loan_uuid\\\":\\\"1415613371239104512\\\",\\\"principal\\\":\\\"0\\\",\\\"productId\\\":\\\"14156127570587811840001\\\",\\\"subTradeType\\\":\\\"300\\\"},\\\"pay_user_id\\\":\\\"1348779434336845825\\\",\\\"pay_user_type\\\":2,\\\"product_id\\\":\\\"14156127570587811840001\\\",\\\"quota_amount\\\":3,\\\"quota_id\\\":\\\"1415613371239104515_29\\\",\\\"receive_user_id\\\":\\\"800000100010062\\\",\\\"receive_user_type\\\":1,\\\"sub_source\\\":15,\\\"suborder_uuid\\\":\\\"85847056\\\",\\\"trans_amount\\\":3,\\\"transferMethod\\\":1,\\\"use_payagent\\\":3},{\\\"jdb_id\\\":\\\"1415613375534071811\\\",\\\"p2p_ext\\\":{\\\"loan_uuid\\\":\\\"1414806376948957239\\\",\\\"principal\\\":\\\"0.00\\\",\\\"productId\\\":\\\"14156130534115246080001\\\",\\\"subTradeType\\\":\\\"201\\\"},\\\"pay_user_id\\\":\\\"1335321387652349952\\\",\\\"pay_user_type\\\":1,\\\"product_id\\\":\\\"14156130534115246080001\\\",\\\"quota_amount\\\":170083,\\\"quota_id\\\":\\\"1415613375534071811\\\",\\\"receive_user_id\\\":\\\"1336808821531607040\\\",\\\"receive_user_type\\\":1,\\\"sub_source\\\":29,\\\"suborder_uuid\\\":\\\"85847053\\\",\\\"trans_amount\\\":83,\\\"transferMethod\\\":1,\\\"use_payagent\\\":83}]";
        orderStr = "[{\"amount\":1016,\"detailId\":\"19320-187508\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"800000100010042\",\"toUserType\":3,\"tradeNum\":1,\"tradeType\":\"JTHKYQGLF\"},{\"amount\":40,\"detailId\":\"19320-187509\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"800010000010031\",\"toUserType\":3,\"tradeNum\":2,\"tradeType\":\"QJCZF\"},{\"amount\":19365,\"detailId\":\"19320-187492\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"1352525849819086848\",\"toUserType\":1,\"tradeNum\":3,\"tradeType\":\"JTHK\"},{\"amount\":968,\"detailId\":\"19320-187510\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"800000100010042\",\"toUserType\":3,\"tradeNum\":4,\"tradeType\":\"JTHKYQGLF\"}]";
        orderStr = "[{\"amount\":1016,\"detailId\":\"19337-187570\",\"fromUserId\":\"1353977746333630464\",\"fromUserType\":1,\"outRemark\":\"代付\",\"toUserId\":\"800000100010042\",\"toUserType\":3,\"tradeNum\":1,\"tradeType\":\"JTHKYQGLF\"},{\"amount\":40,\"detailId\":\"19337-187571\",\"fromUserId\":\"1353977746333630464\",\"fromUserType\":1,\"outRemark\":\"代付\",\"toUserId\":\"800010000010031\",\"toUserType\":3,\"tradeNum\":2,\"tradeType\":\"QJCZF\"},{\"amount\":19365,\"detailId\":\"19337-187548\",\"fromUserId\":\"1353977746333630464\",\"fromUserType\":1,\"toUserId\":\"1352525849819086848\",\"toUserType\":1,\"tradeNum\":3,\"tradeType\":\"JTHK\"},{\"amount\":968,\"detailId\":\"19337-187572\",\"fromUserId\":\"1353977746333630464\",\"fromUserType\":1,\"toUserId\":\"800000100010042\",\"toUserType\":3,\"tradeNum\":4,\"tradeType\":\"JTHKYQGLF\"}]";
        orderStr = "[{\"amount\":1016,\"detailId\":\"19339-187575\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"800000100010042\",\"toUserType\":3,\"tradeNum\":1,\"tradeType\":\"JTHKYQGLF\"},{\"amount\":40,\"detailId\":\"19339-187576\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"800010000010031\",\"toUserType\":3,\"tradeNum\":2,\"tradeType\":\"QJCZF\"},{\"amount\":19365,\"detailId\":\"19339-187548\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"1352525849819086848\",\"toUserType\":1,\"tradeNum\":3,\"tradeType\":\"JTHK\"},{\"amount\":968,\"detailId\":\"19339-187577\",\"fromUserId\":\"1353572374435332096\",\"fromUserType\":1,\"toUserId\":\"800000100010042\",\"toUserType\":3,\"tradeNum\":4,\"tradeType\":\"JTHKYQGLF\"}]";
        String order = StringEscapeUtils.unescapeJava (orderStr);
        System.out.println (order);
        //boolean b = !(false || true);
        //System.out.println (b);

        System.out.println (3 | 1);


    }
}
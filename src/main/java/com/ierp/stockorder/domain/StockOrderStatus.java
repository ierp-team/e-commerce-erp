package com.ierp.stockorder.domain;

public enum StockOrderStatus {
	ORIGINAL,    //订单初始状态
    MATCHED,    //订单匹配成功
    NOMATCH,   //订单处理中
    ASSIGNED,    //拣货完成
    NOASSIGN,   //采购流程中
    DELIVERED,  //已发货
    NODELIVER, //未发货
    COMPLETED//订单完结
}

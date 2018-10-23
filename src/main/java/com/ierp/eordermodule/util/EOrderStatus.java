package com.ierp.eordermodule.util;

public enum EOrderStatus {
    ORIGINAL,      //订单初始状态
    RECEIVED,      //订单已接收，待匹配订单
    NOMATCH,     //订单未匹配成功，订单处理中
    ASSIGNED,      //订单完成分配，等待拣货
    PICKED,           //订单拣货完成，等待发货
    NOASSIGN,     //订单未完成分配，进入采购流程中
    DELIVERED,    //订单发货完成，等待客户收货
    COMPLETED,  //客户收货，订单完结
    CANCEL           //订单已取消
}

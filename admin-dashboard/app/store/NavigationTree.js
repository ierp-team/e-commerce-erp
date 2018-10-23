Ext.define('Admin.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',
    proxy: {
        type: 'ajax',
        url: '/navigation/findNodes',
        reader:{
            type:'json'
        }
    },
    root : {
		text : '组织架构',
		rootVisable:false,
		expanded : true	 //发送node=root
	},

    fields: [
        {name: 'text'},
        {name:'expanded',type:"boolean"},
        {name:'leaf',type:"boolean"},
        {name:'iconCls'},
        {name:'reference'},
        {name:'id'}
    ],
    autoLoad: 'true',
	autoSync: true,//连后台后修改sotre数据自动触发rest请求


    // root: {
    //     expanded: true,
    //     children: [
    //         {
    //             text: 'Dashboard',
    //             iconCls: 'x-fa fa-desktop',
    //             rowCls: 'nav-tree-badge nav-tree-badge-new',
    //             viewType: 'admindashboard',
    //             routeId: 'dashboard', // routeId defaults to viewType
    //             leaf: true,
    //         },{
    //             text: '订单管理模块',
    //             iconCls: 'x-fa fa-address-card',
    //             //rowCls: 'nav-tree-badge nav-tree-badge-new',
    //             viewType: 'order',
    //             leaf: true,
    //             reference:'orderMainPanel',
                
    //         },{
    //             text: 'Process Definition',
    //             iconCls: 'x-fa fa-code-fork',
    //             rowCls: 'nav-tree-badge nav-tree-badge-hot',
    //             viewType: 'processDefinitionPanel',
    //             leaf: true
    //         },
    //         {
    //             text: 'Leave Process',
    //             iconCls: 'x-fa fa-align-justify',
    //             rowCls: 'nav-tree-badge',
    //             viewType: 'leavePanel',
    //             leaf: true
    //         },
    //         {
    //             text: 'Process Approval',
    //             iconCls: 'x-fa fa-archive',
    //             rowCls: 'nav-tree-badge',
    //             viewType: 'leaveApproveMainPanel',
    //             leaf: true
    //         },{
    //             text: 'Login',
    //             iconCls: 'x-fa fa-check',
    //             viewType: 'login',
    //             leaf: true
    //         },{
    //             text: 'EOrder',
    //             iconCls: 'x-fa fa-first-order',
    //             viewType: 'eOrderPanel',
    //             leaf: false,
    //             children:[
    //                 {
    //                     text: '初始订单',
    //                     iconCls: 'x-fa fa-tasks',
    //                     viewType: 'originalOrderPanel',
    //                     leaf: true,
    //                     handler:'originalOrderPanelShow',
    //                 },
    //                 {
    //                     text: '未匹配订单',
    //                     iconCls: 'x-fa fa-balance-scale',
    //                     viewType: 'noMatchOrderPanel',
    //                     leaf: true,
    //                 },
    //                 {
    //                     text: '已分配订单',
    //                     iconCls: 'x-fa fa-bookmark',
    //                     viewType: 'assignedOrderPanel',
    //                     leaf: true,
    //                 },
    //                 {
    //                     text: '未分配订单',
    //                     iconCls: 'x-fa fa-bookmark-o',
    //                     viewType: 'noAssignOrderPanel',
    //                     leaf: true,
    //                 },
    //                 {
    //                     text: '已发货订单',
    //                     iconCls: 'x-fa fa-truck',
    //                     viewType: 'deliveredOrderPanel',
    //                     leaf: true,
    //                 },
    //                 {
    //                     text: '未发货订单',
    //                     iconCls: 'x-fa fa-th-large',
    //                     viewType: 'noDeliverOrderPanel',
    //                     leaf: true,
    //                 },
    //                 {
    //                     text: '已完结订单',
    //                     iconCls: 'x-fa fa-calendar-check-o',
    //                     viewType: 'completedOrderPanel',
    //                     leaf: true,
    //                 },
    //             ]
    //         },{
    //             text: '订单处理',
    //             iconCls: 'x-fa fa-thumb-tack',
    //             viewType: 'eOrderManageMainPanel',
    //             leaf: true
    //         }
    //     ]
    // }
    
});

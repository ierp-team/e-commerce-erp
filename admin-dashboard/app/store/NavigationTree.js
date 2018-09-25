Ext.define('Admin.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',

    fields: [{
        name: 'text'
    }],

    root: {
        expanded: true,
        children: [
            {
                text: 'Dashboard',
                iconCls: 'x-fa fa-desktop',
                rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'admindashboard',
                routeId: 'dashboard', // routeId defaults to viewType
                leaf: true
            },{
                text: '订单管理模块',
                iconCls: 'x-fa fa-address-card',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'order',
                leaf: true
            },{
                text:'员工管理模块',
                iconCls:'x-fa fa-users',
                viewType:'user',
                leaf:true
            },{
                text:'权限管理模块',
                iconCls:'x-fa fa-gear',
                viewType:'permission',
                leaf:true
            }
        ]
    }
});

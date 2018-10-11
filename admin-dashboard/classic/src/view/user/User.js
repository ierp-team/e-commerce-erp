Ext.define('Admin.view.user.User', {
    extend: 'Ext.container.Container',
    xtype: 'user',
    	
    layout: 'fit',

    controller: 'userViewController',
    viewModel: {type: 'userViewModel'},

    items: [{xtype:'userPanel'}]
    
});

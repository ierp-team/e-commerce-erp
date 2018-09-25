Ext.define('Admin.view.user.User', {
    extend: 'Ext.container.Container',
    xtype: 'user',
    
    controller: 'userViewController',
    viewModel: {type: 'userViewModel'},
    	
    layout: 'fit',
    
    items: [{xtype:'userPanel'}]
});

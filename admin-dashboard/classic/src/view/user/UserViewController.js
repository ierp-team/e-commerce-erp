Ext.define('Admin.view.user.UserViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.userViewController',
    
	onEditButton:function(grid, rowIndex, colIndex){
		var rec = grid.getStore().getAt(rowIndex);
        Ext.Msg.alert('Title', rec.get('fullname'));
	},
	onDeleteButton:function(grid, rowIndex, colIndex){
		Ext.Msg.alert("Title","Click Delete Button");
	},
	onDisableButton:function(grid, rowIndex, colIndex){
		Ext.Msg.alert("Title","Click Disable Button");
	}
});

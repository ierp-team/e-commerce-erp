Ext.define('Admin.view.order.OrderViewController', {
    extend: 'Ext.app.ViewController',
	alias: 'controller.orderViewController',
/***************************************** Controller View ****************************************/
	/* Add */
	openAddWindow:function(grid, rowIndex, colIndex){
		//var win = 
		grid.up('container').add(Ext.widget('orderAddWindow')).show();
	},
	
	
	/* Delete More */
	deleteMoreRows:function(grid, rowIndex, colIndex){
		Ext.Msg.alert("Delete More Rows","Click Delete Button");
	},
	
	/* Edit */
	onEditButton:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		if (record ) {
			var win = grid.up('container').add(Ext.widget('orderEditWindow'));
			// var win = Ext.widget('orderEditWindow');
			win.show(); 
			win.down("form").getForm().loadRecord(record);
		}
        
	},
	/* Search More */
	openSearchWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('grid').up('container').add(Ext.widget('orderSearchWindow')).show();
	},
	/*combobox选中后控制对应输入（文本框和日期框）框显示隐藏*/
	selectComboboxChange:function(combo,record,index){
		//alert(record.data.name);
		var searchField = this.lookupReference('searchFieldName').getValue();
		if(searchField==='createTime'){
			this.lookupReference('searchFieldValue').hide();
			this.lookupReference('searchDateFieldValue').show();
			this.lookupReference('searchDateFieldValue2').show();
		}else{
			this.lookupReference('searchFieldValue').show();
			this.lookupReference('searchDateFieldValue').hide();
			this.lookupReference('searchDateFieldValue2').hide();
		}
		
	},
/***************************************** Submit / Ajax / Rest ****************************************/
	submitAddForm:function(btn){
		var win  = btn.up('window');
		var form = win.down('form');
		var record = Ext.create('Admin.model.order.OrderModel');
		var values  =form.getValues();//获取form数据
		
		record.set(values);
		record.save();
		Ext.data.StoreManager.lookup('orderGridStore').load();
		win.close();
	},
	/* Delete */
	onDeleteButton:function(grid, rowIndex, colIndex){
		Ext.MessageBox.confirm('提示', '确定要进行删除操作吗？数据将无法还原！',function(btn, text){
			if(btn=='yes'){
				var store = grid.getStore();
				var record = store.getAt(rowIndex);
				store.remove(record);
				//store.sync();
			}
		}
        , this);	
	},
	/*Delete More Rows*/	
	deleteMoreRows:function(btn, rowIndex, colIndex){
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var rows = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(rows, function (row) {
                        selectIds.push(row.data.id);
                    });
                  	Ext.Ajax.request({ 
						url : '/order/deletes', 
						method : 'post', 
						params : { 
							//ids[] :selectIds
							ids :selectIds
						}, 
						success: function(response, options) {
			                var json = Ext.util.JSON.decode(response.responseText);
				            if(json.success){
				            	Ext.Msg.alert('操作成功', json.msg, function() {
				                    grid.getStore().reload();
				                });
					        }else{
					        	 Ext.Msg.alert('操作失败', json.msg);
					        }
			            }
					});
                }
            });
        }else {
            Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
        }
    },
	/* Quick Search */
	quickSearch:function(btn){
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchValue = this.lookupReference('searchFieldValue').getValue();
		var searchDateFieldValue = this.lookupReference('searchDateFieldValue').getValue();
		var searchDateFieldValue2 = this.lookupReference('searchDateFieldValue2').getValue();
		
		var store =	btn.up('gridpanel').getStore();
		//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在OrderPanel设置id属性
		Ext.apply(store.proxy.extraParams, {orderNumber:"",createTimeStart:"",createTimeEnd:""});
		
		if(searchField==='orderNumber'){
			Ext.apply(store.proxy.extraParams, {orderNumber:searchValue});
		}
		if(searchField==='createTime'){
			Ext.apply(store.proxy.extraParams,{
				createTimeStart:Ext.util.Format.date(searchDateFieldValue, 'Y/m/d H:i:s'),
				createTimeEnd:Ext.util.Format.date(searchDateFieldValue2, 'Y/m/d H:i:s')
			});
		}
		store.load({params:{start:0, limit:20, page:1}});
	},
	
	submitSearchForm:function(btn){
		var store =	Ext.data.StoreManager.lookup('orderGridStroe');
		var win = btn.up('window');
		var form = win.down('form');
		var values  = form.getValues();
		Ext.apply(store.proxy.extraParams, {orderNumber:"",createTimeStart:"",createTimeEnd:""});
		Ext.apply(store.proxy.extraParams,{
			orderNumber:values.orderNumber,
			createTimeStart:Ext.util.Format.date(values.createTimeStart, 'Y/m/d H:i:s'),
			createTimeEnd:Ext.util.Format.date(values.createTimeEnd, 'Y/m/d H:i:s')
		});
		store.load({params:{start:0, limit:20, page:1}});
		win.close();
	},	
	/* Update */
	submitEditForm:function(btn){
		//Ext.Msg.alert('submitEdit!')
		//先获取store用于更新
		var win = btn.up("window");//拿到窗口
		var store = Ext.data.StoreManager.lookup('orderGridStore');//拿到要更新的store
		//获取改变的记录
		var values = win.down('form').getValues();
		//获取改变的记录对应于store中的记录
		var record = store.getById(values.id);
		//加载到store中，自动发请求到后台更新
		record.set(values);
		//store.load();
		win.close();
	},
	/* Disable */
	onDisableButton:function(grid, rowIndex, colIndex){
		Ext.Msg.alert("Title","Click Disable Button");
	},
/***************************************** Common ****************************************/
	/* Close Window */
	closeWindow:function(btn) {
		btn.up('window').close();
	}
});

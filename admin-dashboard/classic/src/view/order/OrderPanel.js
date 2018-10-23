Ext.define('Admin.view.order.OrderPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'orderPanel',

    requires: [
        'Ext.grid.Panel',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Date',
        'Ext.form.field.ComboBox',
        'Ext.selection.CheckboxModel',
        'Ext.grid.column.RowNumberer'
    ],
    // controller: 'OrderViewController',
    // viewModel: {type: 'orderViewModel'},
    layout: 'fit',
    items: [{
        xtype: 'gridpanel',
        cls: 'user-grid',
        title: 'OrderGrid Results',
        //routeId: 'user',
        bind: '{orderLists}',
        scrollable: false,
        selModel: {type: 'checkboxmodel',checkOnly: true},
        columns: [
            {xtype: 'gridcolumn',width: 40,dataIndex: 'id',text: '#',hidden:true},
            {xtype: 'gridcolumn', cls: 'content-column',dataIndex: 'orderNumber',text: 'Order Number',flex: 1},
            {xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'createTime',text: 'Create Time',formatter: 'date("Y-m-d H:i:s")',flex: 1},
            {xtype: 'actioncolumn',cls: 'content-column', width: 120,dataIndex: 'bool',text: 'Actions',tooltip: 'edit ',flex: 1,
                items: [
                    {xtype: 'button', iconCls: 'x-fa fa-pencil' ,handler: 'onEditButton'},
                    {xtype: 'button',iconCls: 'x-fa fa-close'	,handler: 'onDeleteButton'},
                    {xtype: 'button',iconCls: 'x-fa fa-ban'	 	,handler: 'onDisableButton'}
                ]
            }
        ],
        tbar: [{
            xtype: 'combobox',
            //reference: 'states',
            //publishes: 'value',
            //fieldLabel: 'Select State',
            //displayField: 'state',
            //typeAhead: true
            //minChars: 0,
            reference: 'searchFieldName',
            hideLabel: true,
            store: Ext.create("Ext.data.Store",{
                fields: ["name","value"],
                data:[
                    {name:'订单编号',value:"orderNumber"},
                    {name:"创建时间",value:"createTime"}
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            value:'orderNumber',
            
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',/*在extjs底层，默认查询是query，当使用triggerAction ：
            ‘all’这个属性的时候使用的是queryall，就是说我们没有triggerAction这个属性，查
            询时，当没有选择，那么会配置全部，当有了选择，会匹配选择的，与editable：false配合*/
            emptyText: 'Select a state...',
            width: 135,
            listeners:{
                select:'selectComboboxChange'
            }
        },'-',{
            xtype:'textfield',
            name:'orderPanelSearchField',
            reference:'searchFieldValue',
        },'-',{
            xtype: 'datefield',
            hideLabel: true,
            hidden:true,
            format: 'Y-m-d H:i:s',
            reference:'searchDateFieldValue',
            fieldLabel: 'From',
            name: 'from_date'
            //,id:'from_date',
            //vtype: 'daterange',
            //endDateField: 'to_date'
        }, {
            xtype: 'datefield',
            hideLabel: true,
            hidden:true,
            format: 'Y-m-d H:i:s',
            reference:'searchDateFieldValue2',
            fieldLabel: 'To',
            name: 'to_date'
            //,id:'to_date',
            //vtype: 'daterange',
            //startDateField: 'from_date'
        },'-',{
            text: 'Search',
            tooltip: 'Search a price of order',
            iconCls: 'fa fa-search',
            handler: 'quickSearch'
        },'-',{
            text: 'Search More',
            tooltip: 'Search more orders',
            iconCls: 'fa fa-search-plus',
            handler: 'openSearchWindow'	
        },'->',{
            text: 'Add',
            tooltip: 'Add a new price of order',
            iconCls: 'fa fa-plus-circle',
            handler: 'openAddWindow'
        },'-',{
            text: 'Remove',
            tooltip: 'Remove the selected item',
            iconCls:'fa fa-trash',
            disabled: true,
            handler: 'deleteMoreRows',
            itemId: 'orderGridPanelRemove'
        }],
        dockedItems: [{
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            //itemId: 'userPaginationToolbar',
            displayInfo: true,
            bind: '{orderLists}'
        }],
        listeners: {
            selectionchange: function(selModel, selections){
                this.down('#orderGridPanelRemove').setDisabled(selections.length === 0);
            }
        }  
    }]
});

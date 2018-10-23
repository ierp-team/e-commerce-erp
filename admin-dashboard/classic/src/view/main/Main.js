Ext.define('Admin.view.main.Main', {
    extend: 'Ext.container.Viewport',
    id:'mainPanel',

    requires: [
        'Ext.button.Segmented',
        'Ext.list.Tree'
    ],

    controller: 'main',
    viewModel: 'main',

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    listeners: {
        render: 'onMainViewRender'
    },

    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo"><img src="resources/images/company-logo.png">iERP电商EPR管理系统</div>',
                    width: 250
                },
                {
                    margin: '0 0 0 8',
                    ui: 'header',
                    iconCls:'x-fa fa-navicon',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
                '->',
                {
                    xtype: 'tbtext',
                    text: '未登录',
                    id:'loginUserName',
                    cls: 'top-user-name'
                },
                {
                    xtype: 'image',
                    cls: 'header-right-profile-image',
                    id:'loginUserImage',
                    height: 35,
                    width: 35,
                    alt:'current user image',
                    src: 'resources/images/user-profile/2.png'
                },{
                    iconCls:'x-fa fa-sign-out',
                    ui: 'header',
                    tooltip: 'Logout',
                    handler: 'logoutButton'
                }   
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'nav',
                    store: 'NavigationTree',
                    width: 250,
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ],
    listeners:{
        afterrender:function(){
            Ext.Ajax.request({ 
                url : '/checkLogin', 
                method : 'get', 
                success: function(response, options) {
                    var json = Ext.util.JSON.decode(response.responseText);
                    if(json.success){
                        var un=json.map.userName;
                        if(un!=null){
                            Ext.getCmp('loginUserName').setText(un);
                        }else{

                            Ext.Msg.alert('温馨提示', "请先登录！");
                        }      
                    }else{
                        Ext.Msg.alert('登录失败', json.msg);
                        this.redirectTo("login");
                    }
                }
            });
            // this.lookupReference('loginPanel').hide();
            // var ln = Ext.getCmp('loginUserName').getText();
            // Ext.Msg.alert(ln);
            // if (ln =="amy") {
            //     
            //     this.lookupReference('orderMainPanel').setVisavle(false);
            // }
        }

    }
});

Ext.define('Admin.view.main.MainController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.main',

    listen : {
        controller : {
            '#' : {
                unmatchedroute : 'onRouteChange'
            }
        }
    },

    routes: {
        ':node': 'onRouteChange'
    },

    lastView: null,

    setCurrentView: function(hashTag) {
        hashTag = (hashTag || '').toLowerCase();

        var me = this,
            refs = me.getReferences(),
            mainCard = refs.mainCardPanel,
            mainLayout = mainCard.getLayout(),
            navigationList = refs.navigationTreeList,
            store = navigationList.getStore(),
            node = store.findNode('routeId', hashTag) ||
                   store.findNode('viewType', hashTag),
            view = (node && node.get('viewType')),// || 'page404'
            lastView = me.lastView,
            existingItem = mainCard.child('component[routeId=' + hashTag + ']'),
            newView;

        // Kill any previously routed window
        if (lastView && lastView.isWindow) {
            lastView.destroy();
        }

        lastView = mainLayout.getActiveItem();

        if (!existingItem) {
            newView = Ext.create({
                xtype: view,
                routeId: hashTag,  // for existingItem search later
                hideMode: 'offsets'
            });
        }

        if (!newView || !newView.isWindow) {
            // !newView means we have an existing view, but if the newView isWindow
            // we don't add it to the card layout.
            if (existingItem) {
                // We don't have a newView, so activate the existing view.
                if (existingItem !== lastView) {
                    mainLayout.setActiveItem(existingItem);
                }
                newView = existingItem;
            }
            else {
                // newView is set (did not exist already), so add it and make it the
                // activeItem.
                Ext.suspendLayouts();
                mainLayout.setActiveItem(mainCard.add(newView));
                Ext.resumeLayouts(true);
            }
        }

        navigationList.setSelection(node);

        if (newView.isFocusable(true)) {
            newView.focus();
        }

        me.lastView = newView;
    },

    onNavigationTreeSelectionChange: function (tree, node) {
        // var gird = Ext.getCmp('originalOrderGridPanel');// Ext.getCmp(）需要在OrderPanel设置id属性
        // var store = grid.getStore();
		// Ext.apply(store.proxy.extraParams, {orderStatus:'ORIGINAL'});				
		// store.load({params:{start:0, limit:20, page:1}});
        var to = node && (node.get('routeId') || node.get('viewType'));
        var nodeKey = node.get('viewType');
        var store =	Ext.data.StoreManager.lookup('eOrderStore');

        if (to) {
            
            this.redirectTo(to);             
        }
        // if(nodeKey == 'originalOrderPanel'){  
            
        //     //Ext.apply(store.proxy.extraParams, {orderStatus:""});		
        //     //Ext.apply(store.proxy.extraParams, {orderStatus:'ORIGINAL'});
        //     // store.on('beforeload',function(){  
        //     //     Ext.Msg.alert(nodeKey);
        //         Ext.apply(store.proxy.extraParams, {orderStatus:'ORIGINAL'}); 
        //     // });
        //     // store.load({params:{start:0, limit:20, page:1}});  				               
        // }else if(nodeKey == 'noMatchOrderPanel'){
        //     Ext.Msg.alert(nodeKey);
        //     Ext.apply(store.proxy.extraParams, {orderStatus:'NOMATCH'});
        //     store.load({params:{start:0, limit:20, page:1}}); 	
        // }else if(nodeKey == 'assignedOrderPanel'){
        //     Ext.Msg.alert(nodeKey);
        //     Ext.apply(store.proxy.extraParams, {orderStatus:'ASSIGNED'});
        //     store.load({params:{start:0, limit:20, page:1}}); 	
        // }else if(nodeKey == 'noAssignOrderPanel'){
        //     Ext.Msg.alert(nodeKey);
        //     Ext.apply(store.proxy.extraParams, {orderStatus:'NOASSIGN'});
        //     store.load({params:{start:0, limit:20, page:1}}); 	
        // }else if(nodeKey == 'deliveredOrderPanel'){
        //     Ext.Msg.alert(nodeKey);
        //     Ext.apply(store.proxy.extraParams, {orderStatus:'DELIVERED'});
        //     store.load({params:{start:0, limit:20, page:1}}); 	
        // }else if(nodeKey == 'noDeliverOrderPanel'){
        //     Ext.Msg.alert(nodeKey);
        //     Ext.apply(store.proxy.extraParams, {orderStatus:'NODELIVER'});
        //     store.load({params:{start:0, limit:20, page:1}}); 	
        // }else if(nodeKey == 'completedOrderPanel'){
        //     Ext.Msg.alert(nodeKey);
        //     Ext.apply(store.proxy.extraParams, {orderStatus:'COMPLETED'});
        //     store.load({params:{start:0, limit:20, page:1}}); 	
        // }
    },

    onToggleNavigationSize: function () {
        var me = this,
            refs = me.getReferences(),
            navigationList = refs.navigationTreeList,
            wrapContainer = refs.mainContainerWrap,
            collapsing = !navigationList.getMicro(),
            new_width = collapsing ? 64 : 250;

        if (Ext.isIE9m || !Ext.os.is.Desktop) {
            Ext.suspendLayouts();

            refs.senchaLogo.setWidth(new_width);

            navigationList.setWidth(new_width);
            navigationList.setMicro(collapsing);

            Ext.resumeLayouts(); // do not flush the layout here...

            // No animation for IE9 or lower...
            wrapContainer.layout.animatePolicy = wrapContainer.layout.animate = null;
            wrapContainer.updateLayout();  // ... since this will flush them
        }
        else {
            if (!collapsing) {
                // If we are leaving micro mode (expanding), we do that first so that the
                // text of the items in the navlist will be revealed by the animation.
                navigationList.setMicro(false);
            }
            navigationList.canMeasure = false;

            // Start this layout first since it does not require a layout
            refs.senchaLogo.animate({dynamic: true, to: {width: new_width}});

            // Directly adjust the width config and then run the main wrap container layout
            // as the root layout (it and its chidren). This will cause the adjusted size to
            // be flushed to the element and animate to that new size.
            navigationList.width = new_width;
            wrapContainer.updateLayout({isRoot: true});
            navigationList.el.addCls('nav-tree-animating');

            // We need to switch to micro mode on the navlist *after* the animation (this
            // allows the "sweep" to leave the item text in place until it is no longer
            // visible.
            if (collapsing) {
                navigationList.on({
                    afterlayoutanimation: function () {
                        navigationList.setMicro(true);
                        navigationList.el.removeCls('nav-tree-animating');
                        navigationList.canMeasure = true;
                    },
                    single: true
                });
            }
        }
    },
    logoutButton: function(){
		var me = this;
        Ext.Ajax.request({
            url: 'logout',
            method: 'post',
            success: function(response, options) {
            	var json = Ext.util.JSON.decode(response.responseText);
	            if(json.success){
	            	me.redirectTo('login', true);
	            	window.location.reload();
		        }else{
		        	Ext.Msg.alert('登出失败', json.msg);
		        }
            }
        });
    },


    onMainViewRender:function() {
        if (!window.location.hash) {
            this.redirectTo("login");
        }
    },

    onRouteChange:function(id){
        this.setCurrentView(id);
        //登录校验:没有登录无法访问其他模块.
        /*
        var me = this;
	    if(loginUser!="null" || id=="login"){
			me.setCurrentView(id);
		}else{
			Ext.Msg.alert('警告', '非法登录系统!',function(){
				me.setCurrentView('login');
			});
		}*/

    },

    //状态订单相应函数
    // originalOrderPanelShow:function(btn){
    //     // var to = node && (node.get('routeId') || node.get('viewType'));

    //     // if (to) {
    //     //     this.redirectTo(to);
    //     // }
    //     Ext.Msg.alert("originalOrderPanelShow!");
    //     var store = Ext.getCmp('originalOrderGridPanel').getStore();// Ext.getCmp(）需要在OrderPanel设置id属性
	// 	Ext.apply(store.proxy.extraParams, {orderStatus:'ORIGINAL'});
				
	// 	store.load();
    // }
});

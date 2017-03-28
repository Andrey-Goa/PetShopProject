Ext.onReady(function() {
 var  PetShop = Ext.data.Record.create([
        {name: 'pershop_id'},
        {
            name: 'storage',
            type: 'string'
        }, {
            name: 'produck_name',
            type: 'string'
        }, {
            name: 'quantity',
            type: 'float'
        }, {
            name: 'price',
            type: 'float'
        }]);
    var proxy = new Ext.data.HttpProxy({
        api: {
            read: '/getXML',
            create: '/controller/new',
            update: '/controller/update',
            destroy: '/controller/delete'
        }
    });


    var reader = new Ext.data.XmlReader({
        totalRecord: 'Petshops',
        record: 'petshop',
        fields: [ 'petshop_id', 'storage', 'produck_name','quantity', 'price']
    });

    var writer =  new Ext.data.XmlWriter({
        writeAllFields: true,
        listful: true
    });
    var store = new Ext.data.Store({
        autoDestroy: true,
        restful: true,
        proxy: proxy,
        reader: reader,
        writer: writer,
        autoLoad: false,
        autoSave: false
    });
    var fm = Ext.form;
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: "Склад", width: 200, dataIndex: 'storage', editor: new fm.ComboBox({
                mode : 'local',
                editable : false,
                allowBlank : false,
                typeAhead: true,
                triggerAction: 'all',
                lazyRender:true,
                displayField : 'storageDate',
                valueField : 'storageDate',
                store : new Ext.data.ArrayStore({
                    id: 0,
                    fields: [
                        'myId',
                        'storageDate'
                    ],
                    data: [[1, 'Склад 1'], [2, 'Склад 2'], [3, 'Склад 3']]
                })
                    }), sortable: true},
            {header: "Товар", width: 500, dataIndex: 'produck_name', sortable: true, editor: new fm.TextField({
                allowBlank: false
            })},
            {header: "Количество",  width: 100, editor: new fm.NumberField({
                decimalPrecision:2,
                allowBlank: false,
                allowNegative: false,
                maxValue: 100000
            }), renderer: function(value){ return Ext.util.Format.number(value, '0.00')}, dataIndex: 'quantity', sortable: true},
            {header: "Цена", width: 100, editor: new fm.NumberField({
                decimalPrecision:2,
                allowBlank: false,
                allowNegative: false,
                maxValue: 100000
            }), renderer: function(value){ return Ext.util.Format.number(value, '0.00')}, dataIndex: 'price',  sortable: true},
            {header: 'Стоимость', width: 100, sortable: true, renderer: function(v, params, record){
            return Ext.util.Format.number(record.data.quantity * record.data.price, '0.00')}},
            {
                header: "Delete",
                dataIndex: 'button',
                width: 100,
                renderer: renderBtn
            }
    ]
    });
    function renderBtn(val, p, record) {
        var contentId = Ext.id();
        createGridButton.defer(1, this, [val, contentId, record]);
        return('<div id="' + contentId + '"></div>');
    }
    function createGridButton(value, contentid, record) {
        new Ext.Button({text: 'Удалить', handler : function(btn, e) {
            if (confirm('Удалить запись?')) {
                Ext.Ajax.request({ url: '/controller/delete', method: 'DELETE', params: record.data.petshop_id , success: function (data){  store.remove(record);}});
            }
        }}).render(contentid);
    }
    var myAutoReload = new Ext.util.DelayedTask(function(){
        store.reload();
    });
    Ext.override(Ext.data.Record, {
        forceDirty: function(name, value) {
            this.set(name, 'forced dirty');
            this.set(name, value);
        }
    });
    var grid = new Ext.grid.EditorGridPanel({
        store: store,
        cm: cm,
        autoWidth:true,
        height: 400,
        title: 'Название',
        renderTo: 'example-grid',
        frame: true,
        clicksToEdit: 1,
        tbar: [{
                 text: 'Добавить',
                 iconCls: 'icon-user-add',
                 handler: function(){
                     var e = new PetShop({
                         produck_name: 'Продукт',
                         quantity: 0,
                         price: 0
                     });
                     grid.stopEditing();
                     store.insert(0, e);
                     grid.startEditing(0, 0);



                 }
        }, {
            iconCls: 'icon-user-save',
            text: 'Сохранить все изменения',
            handler: function () {
                store.save();
                myAutoReload.delay(1000);
                grid.getView().refresh();

            }
        }
        ]
     });
    store.load()
});
$(function() {
    var editBoxing = undefined;
    $('#dg').datagrid({
        url : 'doubleColorBall/query',
        rownumbers : true,
        pagination : true,
        fitColumns:true,
        height:500,
        pageSize:20,
        pageList : [20,50,100],
        idField : 'ID',
        columns : [[
            {
                field:'id',
                hidden:true
             },{
                field : 'monthNum',
                title : '期数',
                width : 80,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'red1',
                title : '红1',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'red2',
                title : '红2',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },
            {
                field : 'red3',
                title : '红3',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            }, {
                field : 'red4',
                title : '红4',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            }, {
                field : 'red5',
                title : '红5',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            }, {
                field : 'red6',
                title : '红6',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'blue',
                title : '蓝',
                width : 20,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'prize1Count',
                title : '一等奖注数',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'prize1Amount',
                title : '一等奖金额',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'prize2Count',
                title : '二等奖注数',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'prize2Amount',
                title : '二等奖金额',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'poolAmount',
                title : '奖池金额',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'putAmount',
                title : '投注金额',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            },{
                field : 'noticeDate',
                title : '开奖日期',
                width : 40,
                editor : {
                    type : "validatebox",
                    options : {
                        required : true
                    }
                }
            }
            ] ],
        toolbar : [{
            text : '新增',
            iconCls : 'icon-edit',
            handler : function() {
                if (editBoxing == undefined) {
                    editBoxing = 0;
                    $('#dg').datagrid("insertRow", {
                        index : editBoxing, // 索引从0开始
                        row : {
                            monthNum : ''
                        }
                    });
                    $('#dg').datagrid('beginEdit', editBoxing);
                } else {
                    $.messager.alert('警告', '尚有未编辑完成单元，请继续编辑', 'info');
                }
            }
        }, '-', {
            text : '保存',
            iconCls : 'icon-save',
            handler : function() {
                $('#dg').datagrid('endEdit', editBoxing);
                editBoxing = undefined;
            }
        }, '-', {
            text : '删除',
            iconCls : 'icon-remove',
            handler : function() {
                var array = $('#dg').datagrid('getSelections');
                if (array.length >= 1) {
                    var str =new Array();
                    for (var i = 0; i < array.length; i++) {
                        str[i]= array[i].id;
                    }
                    $.post('doubleColorBall/delete', {
                        ids : JSON.stringify(str)
                    }, function(data) {
                        if (data.success == true) {
                            $('#dg').datagrid('reload');
                            $('#dg').datagrid('clearSelections');
                            $.messager.show({
                                title : '更新消息',
                                msg : '删除成功',
                                timeout : 3000
                            });
                        }
                        // $('#dg').datagrid('reload');
                    });
                } else {
                    $.messager.alert('警告', '请选择一条记录');
                }
            }
        }, '-', {
            text : '取消编辑',
            iconCls : 'icon-redo',
            handler : function() {
                if (editBoxing == 0) {
                    $('#dg').datagrid('deleteRow', editBoxing);
                    editBoxing = undefined;
                    $('#dg').datagrid('reload');
                } else {
                    editBoxing = undefined;
                    $('#dg').datagrid('reload');

                }

            }
        }, '-', {
            text : '查看详细信息',
            iconCls : 'icon-search',
            handler : function() {
                var array = $('#dg').datagrid('getSelections');
                if(array.length>0){
                    $('#pc').dialog({
                        title: '人员基本信息',
                        width: 400,
                        height: 250,
                        closed: false,
                        cache: false,
                        content:"<div id='divdiv'></div>",

                        modal: true ,
                    });
                    var cc=document.createElement("table");
                    cc.setAttribute("id", "ttt");
                    document.getElementById("divdiv").appendChild(cc);
                    $('#ttt').propertygrid({
                        url : '    UC/selectById.action',
                        showGroup : true,
                        scrollbarSize : 0,
                        queryParams : {
                            id : array[0].ID,
                        },
                        loadFilter : function(data) {
                            var data_ = {
                                "rows" :[{"name":"账户","value":data.obj.id,},{"name":"登录名","value":data.obj.loginname},{"name":"密码","value":data.obj.password},
                                    {"name":"状态","value":data.obj.status},{"name":"创建时间","value":data.obj.createdate},
                                    {"name":"系统身份","value":data.obj.username}],

                            };
                            return data_;

                        }

                    });
                }else{
                    $.messager.alert('警告', '请选择一条记录');
                }


            }
        }, '-',{

            text : '导入数据',
            iconCls : 'icon-search',
            handler : function() {
                $("#import").window('open');
                document.getElementById('fileImport').value = null;
                document.getElementById('fileName').innerHTML = "";
                document.getElementById('uploadInfo').innerHTML = "";
            }
        } ],
        onAfterEdit : function(index, row, changes) {
            if (row.ID == undefined) {
                $.post('doubleColorBall/save', {
                    item:JSON.stringify(row)
                }, function(data) {
                    if (data.success == true) {
                        $.messager.show({
                            title : '更新消息',
                            msg : '新增成功',
                            timeout : 3000,
                        });
                        $('#dg').datagrid('reload');
                    } else {
                        $.messager.alert('警告', '未完成新增');
                    };

                });
                $('#dg').datagrid('load');
            }

        },
        onDblClickCell : function(index, field, value) {
            if (editBoxing == undefined) {
                editBoxing = index;
                $('#dg').datagrid('beginEdit', index);
                var ed = $(this).datagrid('getEditor', {
                    index : index,
                    field : field
                });
                $(ed.target).focus();
            } else {
                $('#dg').datagrid('endEdit', editBoxing);
                editBoxing = index;
                $('#dg').datagrid('beginEdit', index);
                var ed = $(this).datagrid('getEditor', {
                    index : index,
                    field : field
                });
                $(ed.target).focus();
            }
        }
    });
    $('#fileImport').filebox({
        buttonText: '选择文件',
        buttonAlign: 'left'
    })



});
function importFileClick() {
    var file = $('#fileImport').filebox("files")[0];
    if (file == null) { alert('错误，请选择文件'); return; }
    var fileName = file.name;
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
    if (file_typename == '.xlsx'||file_typename == '.xls'){
        var fileSize = 0;
        if (file.size > 1024 * 1024) {
            fileSize = Math.round(file.size * 100 / (1024 * 1024)) / 100;
            if (fileSize > 10) {
                alert('错误，文件超过10MB，禁止上传！'); return;
            }
            fileSize = fileSize.toString() + 'MB';
        }else {
            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
        }
        $('#fileName').innerHTML = "<span style='color:Blue'>文件名: " + file.name +
            ',大小: ' + fileSize + "</span>";
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            url: "/doubleColorBall/import",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                document.getElementById('fileImport').value = null;
                document.getElementById('uploadInfo').innerHTML = "<span style='color:Red'>" + data + "</span>";
            },
            error: function (returnInfo) {
                document.getElementById('uploadInfo').innerHTML = "<span style='color:Red'>" + data + "</span>";
            }
        });
    }else {
        alert("文件类型错误");
        $('#fileName').innerHTML = "<span style='color:Red'>错误提示:上传文件应该是.xlsx后缀而不应该是"
            + file_typename + ",请重新选择文件</span>"
    }
}
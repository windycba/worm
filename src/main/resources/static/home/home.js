

function addNewTab(tabname,url){
    //创建一个新的窗口，在mainlayout上
    if(!$("#mainTabs").tabs('getTab')) {
        $("#mainTabs").tabs('add', {
            title: tabname,
            selected: true,
            closable: true,
            content: "<iframe src='" +  url + "' style='width:100%;height:100%'  frameborder='no' border='0' marginwidth='0' marginheight='0'  />"
        });
    }
    else {
        $('#mainTabs').tabs('select', tabname);
    }
}

$(function () {
    $('#mytree').tree({
        //url: '/home/tree_data2.json'
        url: '/home/tree_data2.json'
    });
    //点击事件
    $('#mytree').tree({
        onClick: function(node){
            if($('#mytree').tree('isLeaf',node.target)){
                // alert node text property when clicked
                //alert(node.text);
                addNewTab(node.text,node.url);
            }

        }
    });
});

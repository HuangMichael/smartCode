/**
 * Created by Administrator on 2016/11/4.
 */


var mainObject = "";

var selectedIds = [];
var pointer = null;
var dataTableName = "";
var ids = [];//所有的ID的集合
var docName = "";
var formTab = null;
var locs = [];
var eqs = [];
var stations = [];
var units = []; //外委单位信息
var searchModel = [];
var appTypeArray = ["list", "tree"];
var defaultAppType = appTypeArray[0];
var appType = defaultAppType;
var newNode = null;
var parentNode = null;
var formReadOnly = true;
/**
 *
 * @returns {string}
 * 获取主对象
 */
function getMainObject() {
    return mainObject;
}


/**
 *根据id查询返回对象
 * */
function findById(id) {
    var mainObject = getMainObject();
    var object = null;
    if (!id) {
        id = selectedIds[pointer];
    }
    var url = mainObject + "/findById/" + id;
    $.getJSON(url, function (data) {
        object = data;
    });
    return object;
}


/**
 *查询所有的id
 * */
function findAllRecordId() {
    var url = getMainObject() + "/findAllIds";
    $.getJSON(url, function (data) {
        ids = data;
    });
    return ids;
}


/**
 *  初始化下拉选择组件
 */
function initSelect() {


    $("select").select2({
        theme: "bootstrap",
        tags: "true",
        placeholder: "请选择...",
        allowClear: true
    });
}


/**
 * 初始化bootgrid表格 并监听选择时间
 */
function initBootGrid(dataTableName) {
    var config = {
        selection: true,
        multiSelect: true,
        sorting: true
    }
    //初始化加载列表
    $(dataTableName).bootgrid(config).on("selected.rs.jquery.bootgrid", function (e, rows) {
        //如果默认全部选中
        var selected = $(dataTableName).bootgrid("getSelectedRows");
        pointer = 0;
        if (selected.length === 0) {
            selectedIds.clear();
            selectedIds = findAllRecordId();
        } else {
            selectedIds = selected.sort(function (a, b) {
                return a - b
            });
        }

    }).on("deselected.rs.jquery.bootgrid", function (e, rows) {
        var selected = $(dataTableName).bootgrid("getSelectedRows");
        pointer = 0;
        selectedIds = selected.sort(function (a, b) {
            return a - b
        });
    });






}

/**
 * 显示提示信息
 * @param data
 */
function showTips(data) {
    setTimeout(function () {
        var unique_id = $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: '温馨提示!',
            // (string | mandatory) the text inside the notification
            text: data["resultDesc"],
            // (string | optional) the image to display on the left
            image: 'img/gritter/cloud.png',
            // (bool | optional) if you want it to fade out on its own or just sit there
            sticky: true,
            // (int | optional) the time you want it to be alive for before fading out
            time: '1000',
            // (string | optional) the class name you want to apply to that specific message
            class_name: 'my-sticky-class'
        });
        // You can have it return a unique id, this can be used to manually remove it later using
        setTimeout(function () {
            $.gritter.remove(unique_id, {
                fade: true,
                speed: 'slow'
            });
        }, 6000);
    }, 2000);
}
$(function () {
    //初始化项目信息
    var allprojects = window['allprojects'] || [];
//    console.log(allprojects);
    var obj = {};
    obj.id = "0";
    obj.pname = "全部";
    allprojects.unshift(obj);
    $(".combobox").combobox(allprojects);

    //初始化时间
    $("#starttime").datepicker();
    $("#endtime").datepicker();

    var starttime = new Date(new Date().setDate(new Date().getDate() - 6)).setHours(0, 0, 0, 0);
    var endtime = new Date(new Date().setDate(new Date().getDate())).setHours(0, 0, 0, 0);
    $("#starttime").val(new Date(starttime).getMonth() + 1 + "/" + new Date(starttime).getDate() + "/" + new Date(starttime).getFullYear());
    $("#endtime").val(new Date(endtime).getMonth() + 1 + "/" + new Date(endtime).getDate() + "/" + new Date(endtime).getFullYear());

    var projecttimechart = echarts.init(document.getElementById('projecttimechart'));

    $("#filter-projecttime-qrybtn").bind("click", function () {
        var bean = $("#queryForm").serializeJson();
        bean.beginDate = new Date(bean.beginDate).format("yyyy-MM-dd hh:mm:ss");
        bean.endDate = new Date(new Date(bean.endDate).setHours(23, 59, 59, 999)).format("yyyy-MM-dd hh:mm:ss");
//        if(bean.project == 0) delete bean.project;

        if (bean.endDate < bean.beginDate) {
            toastr.options.positionClass = 'toast-top-center';
            toastr.options.closeButton = true;
            toastr.options.timeOut = 1200;
            toastr.warning("开始时间大于结束时间");
            return;
        }
//        console.log(bean);

        $.ajax({
            url: '/pipes/bug/querybycreated',
            cache: false,
            async: false,
            method: 'post',
            dataType: 'json',
            data: {
                "bean": JSON.stringify(bean)
            },
            success: function (result) {
                if (isEmptyObject(result)) {
                    toastr.options.positionClass = 'toast-top-center';
                    toastr.options.closeButton = true;
                    toastr.options.timeOut = 1200;
                    toastr.warning("无数据返回");
                    return;
                }

                if (result.retcode && result.retcode != 'undefined') {
                    toastr.options.positionClass = 'toast-top-center';
                    toastr.options.closeButton = true;
                    toastr.options.timeOut = 1200;
                    toastr.warning(result.retcode + ": " + result.msg);
                    return;
                }

                projecttimechart.setOption(getDefaultLineOption("缺陷按日统计", result));                
                window.onresize = function() {
                    projecttimechart.resize();
                };
                fillTable('projecttimetable', result, false);
            }
        });
    });
});


$(function () {
//    var modulemap = window['modulemap'] || [];
//    var alerttypemap = window['alerttypemap'] || [];
    var starttime = new Date(new Date().setDate(new Date().getDate() - 26)).setHours(0, 0, 0, 0);
    var endtime = new Date(new Date().setDate(new Date().getDate() - 1)).setHours(0, 0, 0, 0);

    $("#starttime").datepicker();
    $("#endtime").datepicker();
    $("#starttime").val(new Date(starttime).getMonth() + 1 + "/" + new Date(starttime).getDate() + "/" + new Date(starttime).getFullYear());
    $("#endtime").val(new Date(endtime).getMonth() + 1 + "/" + new Date(endtime).getDate() + "/" + new Date(endtime).getFullYear());

    var trendChart = echarts.init(document.getElementById("trendChart"));
    
    $("#filter-alertrecordtrend-qrybtn").click(function () {
        var env = $(".select").val();
        starttime = new Date($("#starttime").val()).getTime();
        endtime = new Date($("#endtime").val()).setHours(23, 59, 59, 999);
        
        var queryBean = {};
        var alerttimestamp = {};
        alerttimestamp.min = starttime;
        alerttimestamp.max = endtime;
        queryBean.alerttimestamp = alerttimestamp;
        queryBean.env = env;
        
//        console.log(starttime + " - " + endtime);
        
        $.ajax({
            url: '/pipes/alertrecord/querybyalerttime',
            cache: false,
            async: false,
            method: 'post',
            dataType: 'json',
            data: {
                "bean": JSON.stringify(queryBean)
            },
            success: function (result) {
                if (result && result != null && result != "undefined" && isEmptyObject(result)) {
                    toastr.options.positionClass = 'toast-top-center';
                    toastr.options.closeButton = true;
                    toastr.options.timeOut = 2000;
                    toastr.warning("无内容");
                    return;
                }

                if (result && result != null && result.retcode != 'undefined') {
//                    console.log(result);
                    trendChart.setOption(getDefaultBarOption("", result));
                    fillTable("trendDataTable", result, false);

                    window.onresize = function () {
                        trendChart.resize();
                    };
                } else {
                    toastr.options.positionClass = 'toast-top-center';
                    toastr.options.closeButton = true;
                    toastr.options.timeOut = 1200;
                    toastr.warning(data);
                }
            }
        });
    });
});



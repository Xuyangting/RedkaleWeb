$(function () {
    var modulemap = window['modulemap'] || [];
    var alerttypemap = window['alerttypemap'] || [];
    var starttime = new Date(new Date().setDate(new Date().getDate() - 15)).setHours(0, 0, 0, 0);
    var endtime = new Date().setHours(23, 59, 59, 999);

    var env = $(".select").val();
//        console.log(starttime + " - " + endtime);

    $("#starttime").datepicker();
    $("#endtime").datepicker();
    $("#starttime").val(new Date(starttime).getMonth() + 1 + "/" + new Date(starttime).getDate() + "/" + new Date(starttime).getFullYear());
    $("#endtime").val(new Date(endtime).getMonth() + 1 + "/" + new Date(endtime).getDate() + "/" + new Date(endtime).getFullYear());

    var modulechart = echarts.init(document.getElementById('modulechart'));
    var alertpiechart = echarts.init(document.getElementById('alerttypechart'));
    var alertrecordtable = $("#alertrecordtable");

    $("#filter-alertrecord-qrybtn").click(function () {
        env = $(".select").val();
        starttime = new Date($("#starttime").val()).getTime();
        endtime = new Date($("#endtime").val()).setHours(23, 59, 59, 999);

        if (endtime < starttime) {
            toastr.options.positionClass = 'toast-top-center';
            toastr.options.closeButton = true;
            toastr.options.timeOut = 1200;
            toastr.warning("开始时间大于结束时间");
            return;
        }
//            console.log(starttime + " - " + endtime);

        //隐藏按告警类型区域
        $('#alerttypechart').hide();
        $('#alerttypetable').hide();

        //隐藏dataTable wrapper div内容
        if (!$("#alertrecordtable_wrapper").is(":hidden")) {
            $("#alertrecordtable_wrapper").hide();
        }

        alertrecordtable.hide();

        var barbean = {};
        var alerttime = {};
        alerttime.min = starttime;
        alerttime.max = endtime;
        barbean.alerttime = alerttime;
        barbean.env = env;

        $.ajax({
            url: '/pipes/alertrecord/querybymodule',
            method: 'post',
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                "bean": JSON.stringify(barbean)
            },
            success: function (data) {
                if (data && data != null && (data.retcode == "undefined" || data.retcode)) {
                    toastr.options.positionClass = 'toast-top-center';
                    toastr.options.timeOut = 2000;
                    toastr.warning(data.retcode + ", " + data.retinfo);
                    return;
                }
                if (data && data != null && data != "undefined" && !isEmptyObject(data)) {
                    modulechart = echarts.init(document.getElementById('modulechart'));
                    modulechart.setOption(getDefaultBarOption("按模块", data));
                    window.onresize = modulechart.resize;
                    fillTable("moduletable", data, true);

                    modulechart.on('click', function (params) {
                        // 显示按告警类型区域
                        $('#alerttypechart').show();
                        $('#alerttypetable').show();
                        //隐藏告警信息记录
                        alertrecordtable.hide();
                        //隐藏dataTable wrapper div内容
                        if (!$("#alertrecordtable_wrapper").is(":hidden")) {
                            $("#alertrecordtable_wrapper").hide();
                        }

                        var moduleid = modulemap[params.name];

//                            console.log(moduleid);
                        var piebean = {};
                        piebean.alerttime = barbean.alerttime;
                        piebean.env = barbean.env;
                        piebean.moduleid = moduleid;
//                            console.log(barbean);


                        // 画饼图
                        $.ajax({
                            url: '/pipes/alertrecord/querybyalerttype',
                            method: 'post',
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                "bean": JSON.stringify(piebean)
                            },
                            success: function (piedata) {
//                                    console.log(piedata);
                                if (piedata && piedata != null && piedata != "undefined" && !isEmptyObject(piedata)) {
                                    alertpiechart = echarts.init(document.getElementById('alerttypechart'));
                                    alertpiechart.setOption(getDefaultPieOption("按告警类型", params.name, piedata));
                                    fillTable("alerttypetable", piedata, true);

                                    alertpiechart.on("click", function (params) {
                                        var alerttypeid = alerttypemap[params.name];
//                                            console.log(alerttypeid);

                                        var alertrecordbean = {};
                                        alertrecordbean.alerttime = piebean.alerttime;
                                        alertrecordbean.env = piebean.env;
                                        alertrecordbean.moduleid = piebean.moduleid;
                                        alertrecordbean.alerttypeid = alerttypeid;

                                        alertrecordtable.dataTable({
                                            paging: true,
                                            info: true,
                                            processing: true,
                                            pageLength: 20,
                                            destroy: true,
                                            ajax: {
                                                url: '/pipes/alertrecord/querysheet',
                                                method: 'post',
                                                async: false,
                                                cache: false,
                                                dataType: 'json',
                                                data: {
                                                    "bean": JSON.stringify(alertrecordbean)
                                                },
                                                dataSrc: "rows"
                                            },
                                            columns: [
                                                {
                                                    data: "modulename"
                                                },
                                                {data: "alerttime", orderSequence: ["desc", "asc"],
                                                    render: function (data, type, full, meta) {
                                                        return new Date(data);
                                                    }
                                                },
                                                {
                                                    data: "alerttypename"
                                                },
                                                {
                                                    data: "alertdescription"
                                                },
                                                {
                                                    data: "alertlevel"
                                                },
                                                {data: "env"},
                                                {data: "casename"},
                                                {data: "expectedresponse"},
                                                {data: "actualresponse"},
                                                {data: "requestid"},
                                                {data: "remark"}
                                            ]
                                        });
                                        alertrecordtable.show();
                                        alertrecordtable.resize();
                                    }
                                    );
                                    window.onresize = function () {
                                        alertpiechart.resize();
                                        modulechart.resize();
                                    };
                                }
                            }
                        });
                    });
                }
                if (data && data != null && data != "undefined" && isEmptyObject(data)) {
                    $('#modulechart').html("无数据");
                    $('#moduletable').html("无数据");
                }
            }
        });
    });
});
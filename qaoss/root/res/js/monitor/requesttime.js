$(function () {
    var allrequestname = window['allrequestname'] || [];
//        console.log(allrequestname);
    $(".combobox").combobox(allrequestname);

    var buildrequestchart = echarts.init(document.getElementById('buildrequestchart'));
    var buildrequestratechart = echarts.init(document.getElementById('buildrequestratechart'));

    var starttime = new Date(new Date().setDate(new Date().getDate() - 28)).setHours(0, 0, 0, 0);
    var endtime = new Date(new Date().setDate(new Date().getDate() - 1)).setHours(0, 0, 0, 0);

    $("#starttime").datepicker();
    $("#endtime").datepicker();
    $("#starttime").val(new Date(starttime).getMonth() + 1 + "/" + new Date(starttime).getDate() + "/" + new Date(starttime).getFullYear());
    $("#endtime").val(new Date(endtime).getMonth() + 1 + "/" + new Date(endtime).getDate() + "/" + new Date(endtime).getFullYear());

    $("#filter-requesttime-qrybtn").bind("click", function () {
        var requestname = $(".combobox").val();

        var name = "";
        starttime = new Date($("#starttime").val()).getTime();
        endtime = new Date($("#endtime").val()).getTime();

        if (endtime < starttime) {
            toastr.options.positionClass = 'toast-top-center';
            toastr.options.closeButton = true;
            toastr.options.timeOut = 1200;
            toastr.warning("开始时间大于结束时间");
            return;
        }

        var builddetailbean = {};
        var buildtimestamp = {};
        buildtimestamp.min = starttime;
        buildtimestamp.max = endtime;
        builddetailbean.buildtimestamp = buildtimestamp;
        builddetailbean.requestname = requestname;
//            console.log(JSON.stringify(buildjobbean));

        $.ajax({
            url: '/pipes/buildrecord/querydetailstatilist',
            method: 'post',
            dataType: 'json',
            cache: false,
            async: false,
            data: {
                "bean": JSON.stringify(builddetailbean)
            },
            success: function (result) {
                if (result && result != null && result.retcode != 'undefined') {
//                    console.log(result);
                    var tableData = result;

                    $.each(allrequestname, function (index, value, array) {
                        if (value.id == requestname) {
                            name = value.pname;
                            return;
                        }
                    });

                    var xAxis = [];
                    var yAxis100 = [];
                    var yAxis200 = [];
                    var yAxis500 = [];
                    var yAxis1000 = [];

                    var tableHtml = "<tr><th>日期</th><th>100毫秒(百分比)</th><th>200毫秒(百分比)</th><th>500毫秒(百分比)</th><th>1000毫秒(百分比)</th><th>请求成功率(百分比)</th></tr>";
                    $.each(result, function (index, value, array) {
                        xAxis.push(value.brdetaildate);
                        yAxis100.push(value.onehundred);
                        yAxis200.push(value.twohundreds);
                        yAxis500.push(value.fivehundreds);
                        yAxis1000.push(value.onethousand);
                        
                        if(new Date(value.brdetaildate).getTime() >= new Date().setHours(0,0,0,0) - 7 * 24 * 60 * 60 * 1000)
                            tableHtml += "<tr><td border:solid 1px; border-width:0px 1px 1px 0px; padding:10px 0px;>" + value.brdetaildate + "</td><td border:solid; border-width:0px 1px 1px 0px; padding:10px 0px;>" + value.onehundred + "</td><td border:solid; border-width:0px 1px 1px 0px; padding:10px 0px;>" + value.twohundreds + "</td><td border:solid; border-width:0px 1px 1px 0px; padding:10px 0px;>" + value.fivehundreds + "</td><td border:solid; border-width:0px 1px 1px 0px; padding:10px 0px;>" + value.onethousand + "</td><td border:solid; border-width:0px 1px 1px 0px; padding:10px 0px;>" + value.successrate + "</td></tr>";
                    });

                    buildrequestchart = echarts.init(document.getElementById('buildrequestchart'));
                    var option = {
                        title: {
                            text: name + "-响应时间",
                            top: 'top',
                            textStyle: {
                                color: '#333',
                                fontStyle: 'normal',
                                fontSize: 15,
                                fontWeight: 'normal'
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                magicType: {
                                    type: ['line', 'bar']
                                },
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {// 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        legend: {
                            data: ['100毫秒', '200毫秒', '500毫秒', '1000毫秒']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: xAxis
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                scale: true
                            }
                        ],
                        series: [
                            {
                                name: '100毫秒',
                                type: 'bar',
                                data: yAxis100
                            },
                            {
                                name: '200毫秒',
                                type: 'bar',
                                data: yAxis200
                            },
                            {
                                name: '500毫秒',
                                type: 'bar',
                                data: yAxis500
                            },
                            {
                                name: '1000毫秒',
                                type: 'bar',
                                data: yAxis1000
                            }
                        ]
                    };
                    buildrequestchart.setOption(option);
                    document.getElementById("buildrequesttable").innerHTML = tableHtml;


                } else {
                    toastr.options.positionClass = 'toast-top-center';
                    toastr.options.closeButton = true;
                    toastr.options.timeOut = 1200;
                    toastr.warning(result);
                }
            }
        });

        $.ajax({
            url: '/pipes/buildrecord/querydetailstatilist2',
            method: 'post',
            dataType: 'json',
            cache: false,
            async: false,
            data: {
                "bean": JSON.stringify(builddetailbean)
            },
            success: function (result) {
                console.log(result);
                if (result && result != null && result.retcode != 'undefined') {
                    console.log(result);
                    buildrequestratechart.setOption(getDefaultBarOption(name + "-请求成功率", result));
                }
            }
        });
    });

    window.onresize = function () {
        buildrequestchart.resize();
        buildrequestratechart.resize();
    };
});




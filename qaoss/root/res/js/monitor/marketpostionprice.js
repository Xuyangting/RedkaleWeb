$(function () {
    var mppchart = echarts.init(document.getElementById('mppchart'));

    var starttime = new Date(new Date().setDate(new Date().getDate() - 6)).setHours(0, 0, 0, 0);
    var endtime = new Date(new Date().setDate(new Date().getDate())).setHours(0, 0, 0, 0);

    $("#mppstarttime").datepicker();
    $("#mppendtime").datepicker();
    $("#mppstarttime").val(new Date(starttime).getMonth() + 1 + "/" + new Date(starttime).getDate() + "/" + new Date(starttime).getFullYear());
    $("#mppendtime").val(new Date(endtime).getMonth() + 1 + "/" + new Date(endtime).getDate() + "/" + new Date(endtime).getFullYear());

    $("#filter-mpp-qrybtn").bind("click", function () {
        var ticker = $("#ticker").val();

        starttime = new Date($("#mppstarttime").val()).getTime();
        endtime = new Date($("#mppendtime").val() + " 23:59:59").getTime();

        if (endtime < starttime) {
            toastr.options.positionClass = 'toast-top-center';
            toastr.options.closeButton = true;
            toastr.options.timeOut = 1200;
            toastr.warning("开始时间大于结束时间");
            return;
        }

        var mppbean = {};
        var mpptimestamp = {};
        mpptimestamp.min = starttime;
        mpptimestamp.max = endtime;
        mppbean.range = mpptimestamp;
        mppbean.ticker = ticker;

        $.ajax({
            url: '/pipes/mpp/querylist',
            method: 'post',
            dataType: 'json',
            cache: false,
            async: false,
            data: {
                "bean": JSON.stringify(mppbean)
            },
            success: function (data) {
                if (data && data != null && data.retcode != 'undefined') {
                    var xAxis = [];
                    var marketprices = [];
                    var postionprices = [];
                    $.each(data, function (index, value, array) {
                        xAxis.push(new Date(value.timestamp));
                        marketprices.push(value.marketprice);
                        postionprices.push(value.postionprice);
                    });

                    mppchart = echarts.init(document.getElementById('mppchart'));
                    var option = {
                        title: {
                            text: "价格走势",
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
                            data: ['行情价格', '持仓价格']
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
                                name: '行情价格',
                                type: 'line',
                                data: marketprices
                            },
                            {
                                name: '持仓价格',
                                type: 'line',
                                data: postionprices
                            }
                        ]
                    };
                    mppchart.setOption(option);
                    
                    window.onresize = mppchart.resize;
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



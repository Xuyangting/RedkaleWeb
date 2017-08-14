$(function () {
    var allbuildjob = window['allbuildjob'] || [];
//        console.log(allbuildjob);

    $(".combobox").combobox(allbuildjob);
    var buildrecordchart = echarts.init(document.getElementById('buildrecordchart'));

    var starttime = new Date(new Date().setDate(new Date().getDate() - 7)).setHours(0, 0, 0, 0);
    var endtime = new Date(new Date().setDate(new Date().getDate() - 1)).setHours(0, 0, 0, 0);

    $("#starttime").datepicker();
    $("#endtime").datepicker();
    $("#starttime").val(new Date(starttime).getMonth() + 1 + "/" + new Date(starttime).getDate() + "/" + new Date(starttime).getFullYear());
    $("#endtime").val(new Date(endtime).getMonth() + 1 + "/" + new Date(endtime).getDate() + "/" + new Date(endtime).getFullYear());

    $("#filter-jenkinsbuild-qrybtn").bind("click", function () {
        var buildjobid = $(".combobox").val();
//            console.log(buildjobid);

        starttime = new Date($("#starttime").val()).getTime();
        endtime = new Date($("#endtime").val()).getTime();

        if (endtime < starttime) {
            toastr.options.positionClass = 'toast-top-center';
            toastr.options.closeButton = true;
            toastr.options.timeOut = 1200;
            toastr.warning("开始时间大于结束时间");
            return;
        }

        var buildjobbean = {};
        var buildtimestamp = {};
        buildtimestamp.min = starttime;
        buildtimestamp.max = endtime;
        buildjobbean.timestamprange = buildtimestamp;
        buildjobbean.buildjobid = buildjobid;
//            console.log(JSON.stringify(buildjobbean));

        $.ajax({
            url: '/pipes/buildrecord/querystatilist',
            method: 'post',
            dataType: 'json',
            cache: false,
            async: false,
            data: {
                "bean": JSON.stringify(buildjobbean)
            },
            success: function (data) {
                if (data && data != null && data.retcode != 'undefined') {

                    var name = "";
                    $.each(allbuildjob, function (index, value, array) {
                        if (value.id == buildjobid) {
                            name = value.pname;
                            return;
                        }
                    });

                    buildrecordchart = echarts.init(document.getElementById('buildrecordchart'));
                    buildrecordchart.setOption(getDefaultBarOption(name, data));
                    window.onresize = buildrecordchart.resize;
                    fillTable("buildrecordtable", data, false);
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



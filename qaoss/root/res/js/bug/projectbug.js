$(document).ready(function () {
    var allprojects = window['allprojects'] || [];
    console.log(allprojects);
    console.log(window.allprojects);
    $(".combobox").combobox(allprojects);

    $("#filter-projectbug-qrybtn").click(function () {
        var projectId = $(".combobox").val();

        $.ajax({
            url: "/pipes/bug/queryprojectbugbycomp/" + projectId,
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data && data != null && data != "undefined" && !isEmptyObject(data)) {
                    console.log(data);
                    var myChart = echarts.init(document.getElementById('bymodelforproject'));
                    myChart.setOption(getDefaultBarOption("按模块", data));

                    var myPieChart = echarts.init(document.getElementById('bymodelforprojectpiechart'));
                    myPieChart.setOption(getDefaultPieOption("按模块", "", data));
                    window.onresize = function () {
                        myChart.resize();
                        myPieChart.resize();
                    };
                    fillTable("bymodelforprojecttable", data, true);
                }
                if (data && data != null && data != "undefined" && isEmptyObject(data)) {
                    $('#bymodelforproject').html("无数据");
                    $('#bymodelforprojectpiechart').html("无数据");
                    $('#bymodelforprojecttable').html("无数据");
                }
            },
            error: function (data) {
                console.log(data);
                alert("请求数据失败.");
            }
        });
    });
});



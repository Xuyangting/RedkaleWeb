/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function parseMap(map, xAxis, yAxis) {
    if (map == null) {
        xAxis = [];
        yAxis = [];
        return;
    }

    for (var key in map) {
        xAxis.push(key);
        yAxis.push(map[key]);
    }
}

$.prototype.combobox = function (data) {
    // 1. 先判断数据是否不为空，且数据为object
    if (data != null && typeof data == "object") {
        var innerHtml = "";
        $.each(data, function (key, value) {
            //2. 如果是数组对象，需要分步处理
            // 2.1 逐个获取数组对象中的对象
            if (data instanceof Array) {
                var arr = [];
                // 2.2 遍历对象
                for (var obj in value) {
//                    console.log(value[obj]);
                    // 2.3 将对象中的值存储到临时数组中
                    arr.push(value[obj]);
                }
                // 2.4 将数组值的前2个放入到combobox中，分别为key 与 value
                innerHtml = innerHtml + '<option value="' + arr[0] + '">' + arr[1] + '</option>';
            } else {
                // 3. 如果是一个非array的object，循环获取值后直接赋予
                innerHtml = innerHtml + '<option value="' + key + '">' + value + '</option>';
            }
        });
//        console.log(innerHtml);
        $(this).append(innerHtml);
    }
};

function fillTable(id, map, isNeedTotal) {
    var table = document.getElementById(id);
    var names = [];
    var values = [];
    parseMap(map, names, values);
    var total = 0;
    var tableHtml = "<tr>";
    for (var index in names) {
        tableHtml += "<td>" + names[index] + "</td>";
    }

    if (isNeedTotal && (isNeedTotal == true || isNeedTotal == "true"))
        tableHtml += "<td>总数</td>";

    tableHtml += "</tr><tr>";
    for (var index in values) {
        total += values[index];
        tableHtml += "<td>" + values[index] + "</td>";
    }

    if (isNeedTotal && (isNeedTotal == true || isNeedTotal == "true"))
        tableHtml += "<td>" + total + "</td>";
    table.innerHTML = tableHtml + "</tr>";
}

function isEmptyObject(e) {
    var t;
    for (t in e)
        return !1;
    return !0;
}

function getDefaultBarOption(title, map) {
    var xAxis = [];
    var yAxis = [];
    parseMap(map, xAxis, yAxis);

//    console.log(xAxis);
//    console.log(yAxis);

    var option = {
        tooltip: {
            show: true
        },
        title: {
            text: title,
            left: 'center',
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
        calculable: true,
        grid: {
            borderWidth: 0
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
                type: 'bar',
                smooth: true,
                data: yAxis,
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ],
                    lineStyle: {
                        normal: {
                            color: 'red'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        lineStyle: {
                            color: '#0088FF'
                        },
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex % 12];
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{b}\n{c}'
                        }
                    },
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0
                    }
                }
            }
        ]
    };
    return option;
}

function getDefaultLineOption(title, map) {
    var xAxis = [];
    var yAxis = [];
    parseMap(map, xAxis, yAxis);

//    console.log(xAxis);
//    console.log(yAxis);

    var option = {
        tooltip: {
            show: true,
            trigger: 'axis'
        },
        title: {
            text: title,
            left: 'center',
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
        calculable: true,
        grid: {
            borderWidth: 0
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
                name: '数量',
                type: 'line',
                smooth: true,
                data: yAxis,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ],
                    lineStyle: {
                        normal: {
                            color: '#60C0DD'
                        }
                    }
                },
                itemStyle: {
                    normal: {
//                        color: '#C1232B',
                        lineStyle: {
                            color: '#0088FF'
                        },
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex % 12];
                        }
//                        label: {
//                            show: true,
//                            interval: 0,
//                            position: 'top',
//                            formatter: '{b}\n{c}'
//                        }
                    },
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0
                    }
                }
            }
        ]
    };
    return option;
}

var getDefaultPieOption = function (title, name, map) {
    var xAxis = [];
    var yAxis = [];

    if (map == null) {
        xAxis = [];
        yAxis = [];
    } else {
        for (var key in map) {
            var y = {};
            y.value = map[key];
            y.name = key;
            xAxis.push(key);
            yAxis.push(y);
        }
    }

    var option = {
        title: {
            text: title,
            x: 'center',
            textStyle: {
                color: '#333',
                fontStyle: 'normal',
                fontSize: 15,
                fontWeight: 'normal'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: xAxis
        },
        toolbox: {
            show: true,
            feature: {
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: name,
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: yAxis,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    return option;
};

//日期格式化
//Date.prototype.Format = function (fmt) { //author: meizz 
//    var o = {
//        "M+": this.getMonth() + 1, //月份 
//        "d+": this.getDate(), //日 
//        "H+": this.getHours(), //小时 
//        "m+": this.getMinutes(), //分 
//        "s+": this.getSeconds(), //秒 
//        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
//        "S": this.getMilliseconds() //毫秒 
//    };
//    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//    for (var k in o)
//    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//    return fmt;
//};
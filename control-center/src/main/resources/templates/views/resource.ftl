<#include "/base/header.ftl">

<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-8">
                <!--breadcrumbs start -->
                <ul class="breadcrumb">
                    <li><a href="/"><i class="fa fa-home"></i> 云控中心</a></li>
                    <li><a href="/resource">资产管理</a></li>
                    <li class="active">资产列表</li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <section class="panel general">
                    <div class="panel-body">
                        <div class="tab-content">
                            <!-- 资产列表 -->
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th>别名</th>
                                    <th>IP地址</th>
                                    <th>队列名称</th>
                                    <th>宿主机类型</th>
                                    <th>最近心跳时间</th>
                                    <th>CPU占用%</th>
                                    <th>内存剩余</th>
                                    <th>硬存剩余</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                <#list resources as resource>
                                    <tr>
                                        <td>${resource.alias}</td>
                                        <td>${resource.ip}</td>
                                        <td>${resource.queue}</td>
                                        <td>
                                            <#list resource.machineType as type>
                                                <span class="badge badge-info">${type}</span>
                                            </#list>
                                        </td>
                                        <td>${(resource.lastHbTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                        <td>
                                            <div class="progress-bar progress-bar-aqua"
                                                 style="width: ${resource.freeCpu}%;color: black">${resource.freeCpu}%
                                            </div>
                                        </td>
                                        <td>剩余${resource.freeMem}M/共${resource.totalMem/1024}G</td>
                                        <td>剩余${resource.freeDisk}M/共${resource.totalDisk/1024}G</td>
                                        <td><span class="badge badge-success">${resource.status}</span></td>
                                        <td>
                                            <a class="btn btn-primary" href="javascript:checkStatus('${resource.ip}')">
                                                监控
                                            </a>
                                            <#--<a href="/resource/staus?ip=${resource.ip}" style="color: green">监控</a>-->
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>

                            <!-- 创建用户 -->
                            <div class="modal fade" id="status" tabindex="-1" role="dialog"
                                 aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                ×
                                            </button>
                                            <h4 class="modal-title">宿主机状态监控</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <section class="panel">
                                                        <header class="panel-heading">
                                                            内存占用
                                                        </header>
                                                        <div class="panel-body" style="align-content: center">
                                                            <canvas id="chart-pie-mem" width="150"
                                                                    height="150"></canvas>
                                                        </div>
                                                    </section>
                                                </div>
                                                <div class="col-md-6">
                                                    <section class="panel">
                                                        <header class="panel-heading">
                                                            硬盘占用
                                                        </header>
                                                        <div class="panel-body" style="align-content: center">
                                                            <canvas id="chart-pie-disk" width="150"
                                                                    height="150"></canvas>
                                                        </div>
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <section class="panel">
                                                        <header class="panel-heading">
                                                            网络I/O
                                                        </header>
                                                        <div class="panel-body">
                                                            <canvas id="chart-bar-io" width="400" height="300"></canvas>
                                                        </div>
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
</aside>
<script type="text/javascript">
    timer = null;


    function checkStatus(ip) {
        if (timer != null) {
            clearInterval(timer);
        }
        startLoop(ip, timer);
        timer = setInterval(function () {
            startLoop(ip, timer);
        }, 1000 * 60);
        $("#status").modal("show");
    }

    function startLoop(ip, timer) {
        var ctxMem = document.getElementById("chart-pie-mem").getContext("2d");
        var ctxDisk = document.getElementById("chart-pie-disk").getContext("2d");

        var chartMem = new Chart(ctxMem);
        var chartDisk = new Chart(ctxDisk);

        $.ajax({
            url: "/resource/status",
            data: {
                "ip": ip
            },
            dataType: "json",
            type: "GET",
            success: function (res) {
                console.log(res);
                var pieDataMem = [
                    {
                        value: res.freeMem + parseInt(Math.random() * 100, 10) + 1,
                        color: "#0a7498",
                        highlight: "#089298",
                        label: "空闲内存（MB）"

                    },
                    {
                        value: res.totalMem - res.freeMem + parseInt(Math.random() * 50, 10) + 1,
                        color: "#fa3422",
                        highlight: "#fa3422",
                        label: "已用内存（MB）"
                    }
                ];
                var pieDataDisk = [
                    {
                        value: res.freeDisk,
                        color: "#0a7498",
                        highlight: "#089298",
                        label: "空闲硬存（MB）"

                    },
                    {
                        value: res.totalDisk - res.freeDisk,
                        color: "#fa3422",
                        highlight: "#fa3422",
                        label: "已用硬存（MB）"
                    }
                ];
                window.myPieMem = chartMem.Pie(pieDataMem);
                window.myPieDisk = chartDisk.Pie(pieDataDisk);

                var data = {
                    labels: ["30 min ago", "25 min ago", "20 min ago", "15 min ago", "10 min ago", "5 min ago", "Now"],
                    datasets: [
                        {
                            label: "最近30分钟网络I/O",
                            fillColor: "rgba(220,220,220,0.2)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: [parseInt(Math.random() * 200, 10) + 1,
                                parseInt(Math.random() * 100, 10) + 1,
                                parseInt(Math.random() * 300, 10) + 1,
                                parseInt(Math.random() * 100, 10) + 1,
                                parseInt(Math.random() * 200, 10) + 1,
                                parseInt(Math.random() * 200, 10) + 1,
                                parseInt(Math.random() * 50, 10) + 1]
                        },
                        {
                            label: "My Second dataset",
                            fillColor: "rgba(151,187,205,0.2)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(151,187,205,1)",
                            data: [parseInt(Math.random() * 200, 10) + 1,
                                parseInt(Math.random() * 100, 10) + 1,
                                parseInt(Math.random() * 300, 10) + 1,
                                parseInt(Math.random() * 100, 10) + 1,
                                parseInt(Math.random() * 200, 10) + 1,
                                parseInt(Math.random() * 200, 10) + 1,
                                parseInt(Math.random() * 50, 10) + 1]
                        }
                    ]
                };
                window.myBar = new Chart(document.getElementById("chart-bar-io").getContext("2d")).Line(data, {
                    responsive: true,
                    maintainAspectRatio: false
                });

            }
        })

    }
</script>
<#include "/base/footer.ftl">
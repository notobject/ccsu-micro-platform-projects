<#include "/base/header.ftl">
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row" style="margin-bottom:5px;">
            <div class="col-md-3">
                <div class="sm-st clearfix">
                    <span class="sm-st-icon st-red"><i class="fa fa-users"></i></span>
                    <div class="sm-st-info">
                        <span>1 个</span>
                        资产数量
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="sm-st clearfix">
                    <span class="sm-st-icon st-violet"><i class="fa fa-arrow-up"></i></span>
                    <div class="sm-st-info">
                        <span>2 个</span>
                        微服务数量
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="sm-st clearfix">
                    <span class="sm-st-icon st-blue"><i class="fa fa-clock-o"></i></span>
                    <div class="sm-st-info">
                        <span>102 天</span>
                        系统累积运行
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="sm-st clearfix">
                    <span class="sm-st-icon st-green"><i class="fa fa-tasks"></i></span>
                    <div class="sm-st-info">
                        <span>2</span>
                        正在执行的任务数量
                    </div>
                </div>
            </div>
        </div>

        <!-- Main row -->
        <div class="row">
            <div class="col-md-12">
                <!--earning graph start-->
                <section class="panel">
                    <header class="panel-heading">
                        用户增长曲线
                    </header>
                    <div class="panel-body">
                        <canvas id="linechart" width="600" height="330"></canvas>
                    </div>
                </section>
                <!--earning graph end-->

            </div>
        </div>

        <div class="row">
            <div class="col-md-8">
                <section class="panel">
                    <header class="panel-heading">
                        操作日志
                    </header>
                    <div class="panel-body table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>日志类别</th>
                                <th>操作详情</th>
                                <th>用户</th>
                                <th>操作时间</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td><span class="label label-success">系统登录</span></td>
                                <td>通过IP[192.168.1.1]登录系统</td>
                                <td>曹孝双</td>
                                <td>2019-03-30 20:28:54</td>
                                <td><span class="badge badge-success">成功</span></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td><span class="label label-success">系统登录</span></td>
                                <td>通过IP[192.168.1.1]登录系统</td>
                                <td>曹孝双</td>
                                <td>2019-03-30 20:28:54</td>
                                <td><span class="badge badge-success">成功</span></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td><span class="label label-success">系统登录</span></td>
                                <td>通过IP[192.168.1.1]登录系统</td>
                                <td>曹孝双</td>
                                <td>2019-03-30 20:28:54</td>
                                <td><span class="badge badge-success">成功</span></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td><span class="label label-success">系统登录</span></td>
                                <td>通过IP[192.168.1.1]登录系统</td>
                                <td>曹孝双</td>
                                <td>2019-03-30 20:28:54</td>
                                <td><span class="badge badge-success">成功</span></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td><span class="label label-success">系统登录</span></td>
                                <td>通过IP[192.168.1.1]登录系统</td>
                                <td>曹孝双</td>
                                <td>2019-03-30 20:28:54</td>
                                <td><span class="badge badge-success">成功</span></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td><span class="label label-success">系统登录</span></td>
                                <td>通过IP[192.168.1.1]登录系统</td>
                                <td>曹孝双</td>
                                <td>2019-03-30 20:28:54</td>
                                <td><span class="badge badge-success">成功</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
            </div><!--end col-8 -->
            <div class="col-md-4">
                <section class="panel">
                    <header class="panel-heading">
                        系统信息
                    </header>
                    <div class="panel-body table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>设备</th>
                                <th>型号</th>
                                <th>剩余</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>CPU</td>
                                <td>Core i7</td>
                                <td>
                                    <div class="progress-bar progress-bar-primary" style="width: 63%">63%</div>
                                </td>
                                <td><span class="label label-primary">正常</span></td>
                            </tr>
                            <tr>
                                <td>内存</td>
                                <td>未知</td>
                                <td>
                                    <div class="progress-bar progress-bar-danger" style="width: 10%">10%</div>
                                </td>
                                <td><span class="label label-danger">不足</span></td>
                            </tr>
                            <tr>
                                <td>硬盘</td>
                                <td>三星</td>
                                <td>
                                    <div class="progress-bar progress-bar-success" style="width: 86%">86%</div>
                                </td>
                                <td><span class="label label-success">充足</span></td>
                            </tr>
                            <tr>
                                <td>CPU</td>
                                <td>Core i7</td>
                                <td>
                                    <div class="progress-bar progress-bar-primary" style="width: 63%">63%</div>
                                </td>
                                <td><span class="label label-primary">正常</span></td>
                            </tr>
                            <tr>
                                <td>内存</td>
                                <td>未知</td>
                                <td>
                                    <div class="progress-bar progress-bar-danger" style="width: 10%">10%</div>
                                </td>
                                <td><span class="label label-danger">不足</span></td>
                            </tr>
                            <tr>
                                <td>硬盘</td>
                                <td>三星</td>
                                <td>
                                    <div class="progress-bar progress-bar-success" style="width: 86%">86%</div>
                                </td>
                                <td><span class="label label-success">充足</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
            </div>

        </div>
    </section><!-- /.content -->

    <div class="footer-main">
        Copyright &copy NOTOBJECT.COM. 1995-2019.
    </div>
</aside><!-- /.right-side -->
<script type="text/javascript">
    $(function () {
        "use strict";
        //BAR CHART
        var data = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [
                {
                    label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                },
                {
                    label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [28, 48, 40, 19, 86, 27, 90]
                }
            ]
        };
        new Chart(document.getElementById("linechart").getContext("2d")).Line(data, {
            responsive: true,
            maintainAspectRatio: false,
        });

    });
    // Chart.defaults.global.responsive = true;
</script>
<#include "/base/footer.ftl">
<#include "/base/header.ftl">
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <ul class="breadcrumb">
                    <li><a href="/"><i class="fa fa-home"></i> 云控中心</a></li>
                    <li><a href="/service">服务管理</a></li>
                    <li class="active">${service.serviceName}</li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <section class="panel general">
                    <header class="panel-heading tab-bg-dark-navy-blue">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a data-toggle="tab" href="#mange">${service.id}-${service.serviceName}</a>
                            </li>
                        </ul>
                    </header>
                    <div class="panel-body">
                        <div class="tab-content">
                            <div id="mange" class="tab-pane active">
                                <a class="btn btn-primary" href="javascript:command('start',${service.id})">
                                    扩容
                                </a>
                                <a class="btn btn-primary" href="javascript:command('reduce',${service.id})">缩容</a>
                                <a class="btn btn-primary" href="javascript:command('config',${service.id})">编辑配置</a>
                                <p></p>
                                <div class="row">
                                    <!-- 搜索结果表格 -->
                                    <div class="col-md-12">
                                        <table class="table table-hover">
                                            <tbody>
                                            <tr>
                                                <th>ID</th>
                                                <th>服务名称</th>
                                                <th>服务版本</th>
                                                <th>实例地址</th>
                                                <th>实例状态</th>
                                                <th>负责人</th>
                                                <th>上次更新时间</th>
                                                <th>管理</th>
                                            </tr>
                                            <#list infos as info>
                                                <tr>
                                                    <td>${info.id}</td>
                                                    <td>${info.name}</td>
                                                    <td>${info.version}</td>
                                                    <td><a href="http://${info.address}"
                                                           target="_blank">${info.address}</a></td>
                                                    <td><span class="badge badge-success">${info.status}</span></td>
                                                    <td>${info.creator}</td>
                                                    <td>${(info.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                                    <td>
                                                        <a href="javascript:command('stop',${info.id})"> <span
                                                                    class="badge badge-danger">停止</span></a>
                                                        <a href="javascript:command('restart',${info.id})"><span
                                                                    class="badge badge-inverse">重启</span></a>
                                                        <a href="javascript:command('rollback',${info.id})"><span
                                                                    class="badge badge-primary">回滚</span></a>
                                                    </td>
                                                </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                    <div style="width:100%;height:600px;overflow-y:auto; padding:0 10px 0 10px;">
                                        <div id="content" style="width:100%;line-height:20px;">
                                            <p>...</p>
                                        </div>
                                        <span id="msg_end" style="overflow:hidden"></span>
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
    function command(action, sid) {
        $.ajax({
            url: "/service/manage",
            data: {
                "action": action,
                "sid": sid
            },
            dataType: "json",
            type: "POST",
            success: function (res) {
                timer = setInterval(function () {
                    startLoop(res.data.id, timer);
                }, 1000);
            }
        })
    }

    function startLoop(taskId, timer) {
        $.ajax({
            url: "/service/process",
            data: {
                "taskId": taskId
            },
            dataType: "json",
            type: "GET",
            success: function (res) {
                process = $("#content");
                var msg = "";
                if (res.status === "waiting") {
                    msg = "waiting for response...";
                } else if (res.status === "complete") {
                    msg = res.msg + "done.";
                    clearInterval(timer);
                } else if (res.status === "processing") {
                    msg = res.msg + "<br />"
                } else {
                    msg = ""
                }
                if (msg !== "") {
                    process.append("<p>" + msg + "</p>");
                }
            }
        })
    }
</script>
<#include "/base/footer.ftl">
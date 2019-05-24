<#include "/base/header.ftl">
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-8">
                <!--breadcrumbs start -->
                <ul class="breadcrumb">
                    <li><a href="/"><i class="fa fa-home"></i> 云控中心</a></li>
                    <li><a href="/service">服务管理</a></li>
                    <li class="active">服务治理</li>
                </ul>
                <!--breadcrumbs end -->
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <section class="panel general">
                    <header class="panel-heading tab-bg-dark-navy-blue">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a data-toggle="tab" href="#search">查询</a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#new">上线</a>
                            </li>
                        </ul>
                    </header>
                    <div class="panel-body">
                        <div class="tab-content">
                            <!-- 搜索页-->
                            <div id="search" class="tab-pane active">
                                <!-- 搜索框 -->
                                <div class="row">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-6 form-group">
                                        <div class="input-group m-b-5">
                                            <input type="text" class="form-control">
                                            <span class="input-group-btn">
                                                    <button class="btn btn-white" type="button">
                                                        <i class="fa fa-search"></i>
                                                    </button>
                                                </span>
                                        </div>
                                        <p class="text-center">
                                            <a href="#"><span class="label label-default">label</span></a>
                                            <a href="#"><span class="label label-primary">Primary</span></a>
                                            <a href="#"><span class="label label-success">Success</span></a>
                                            <a href="#"><span class="label label-info">Info</span></a>
                                            <a href="#"><span class="label label-inverse">Inverse</span></a>
                                            <a href="#"><span class="label label-warning">Warning</span></a>
                                            <a href="#"><span class="label label-danger">Danger</span></a>
                                        </p>
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                                <!-- 搜索结果-->
                                <div class="row">
                                    <!-- 搜索结果表格 -->
                                    <div class="col-md-12">
                                        <table class="table table-hover">
                                            <tbody>
                                            <tr>
                                                <th>ID</th>
                                                <th>服务名称</th>
                                                <th>服务类型</th>
                                                <th>负责人</th>
                                                <th>上线日期</th>
                                                <th>备注</th>
                                                <th>操作</th>
                                            </tr>
                                            <#list serviceList as service>
                                                <tr>
                                                    <td>${service.id}</td>
                                                    <td><a href="/service?sid=${service.id}">${service.serviceName}
                                                            :${service.versionName}</a></td>
                                                    <td><span class="badge badge-warning">${service.serviceType}</span>
                                                    </td>
                                                    <td>${service.creator}</td>
                                                    <td>${(service.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                                    <td>${service.note}</td>
                                                    <td><a href="/service?sid=${service.id}">管理</a></td>
                                                </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                        <!-- 分页组件 -->
                                        <div>
                                            <ul class="pagination pull-right">
                                                <li><a href="#">«</a></li>
                                                <li><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#">5</a></li>
                                                <li><a href="#">»</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <!--服务上线-->
                            <div id="new" class="tab-pane">
                                <div class="row">
                                    <div class="col-md-6">
                                        <form id="form-new" class="form-horizontal tasi-form" method="post"
                                              action="/service/new">
                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">服务名称:版本</label>
                                                <div class="col-sm-6">
                                                    <input type="text" name="serviceName" class="form-control"
                                                           value="ccsu-register-server">
                                                    <span class="help-block">服务名应根据不同的项目，填写相应的前缀，方便管理和查询。如：ccsu-register-server</span>
                                                </div>

                                                <div class="col-sm-4">
                                                    <input type="text" name="versionName" class="form-control"
                                                           value="1.0.0.RELEASE">
                                                    <span class="help-block">这里填写版本名,如：1.0.0.RELEASE</span>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">服务类型</label>
                                                <div class="col-sm-4">
                                                    <select name="serviceType" class="form-control">
                                                        <option value="base">基础</option>
                                                        <option value="busi">业务</option>
                                                        <option value="other">其它</option>
                                                    </select>
                                                    <span class="help-block">请正确选择服务类型，关系到资源分配。</span>
                                                </div>

                                                <label class="col-sm-2 col-sm-2 control-label">端口</label>
                                                <div class="col-sm-4">
                                                    <input type="text" name="servicePort" class="form-control"
                                                           value="8761" placeholder="">
                                                    <span class="help-block">服务端口，默认随机选择</span>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">仓库地址</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="repoUrl" class="form-control"
                                                           value="https://github.com/notobject/ccsu-micro-platform-projects.git">
                                                    <span class="help-block">这里填写项目代码仓库的地址,只支持git。如：https://github.com/notobject/ccsu-micro-platform-projects.git</span>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">构建命令</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="buildCmd"
                                                           value="mvn clean package -Dmaven.test.skip=true -Pprod"
                                                           class="form-control">
                                                    <span class="help-block">编译的时候执行的指令。如: mvn clean package -Dmaven.test.skip=true -Pprod</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 col-sm-2 control-label">备注</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="note"
                                                           value="这是一条正经的备注"
                                                           class="form-control">
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div style="width:100%;height:600px;overflow-y:auto; padding:0 10px 0 10px;">
                                                    <div id="content" style="width:100%;line-height:20px;">
                                                        <div id="stat-complete" class="stat" style="display: block">
                                                            <div class="stat-icon" style="color:#27C24C;">
                                                                <i class="fa fa-refresh fa-4x"></i>
                                                            </div>
                                                            <h5 class="stat-info">Waiting...</h5>
                                                        </div>
                                                    </div>
                                                    <span id="msg_end" style="overflow:hidden"></span>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary" id="new-submit">提交</button>
                                        <button type="button" class="btn btn-danger" id="new-cancel">取消</button>
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
                    msg = "";
                } else if (res.status === "complete") {
                    msg = res.msg + "done.";
                    clearInterval(timer);
                } else if (res.status === "processing") {
                    msg = res.msg
                } else {
                    msg = ""
                }
                if (msg !== "") {
                    process.append("<p>" + msg + "</p>");
                    p = process.find('p:last');
                    process.scrollTo = p.offset().top - process.offset().top + process.scrollTop
                }
            }
        })
    }

    $("#new-submit").on("click", function (event) {
        param = $('#form-new').serializeObject();
        $.ajax({
            url: "/service/build",
            data: param,
            dataType: "json",
            type: "POST",
            success: function (res) {
                // console.log(res);
                taskId = res.data.id;
                timer = setInterval(function () {
                    startLoop(taskId, timer);
                }, 1000);
            }
        })
    })
</script>
<#include "/base/footer.ftl">
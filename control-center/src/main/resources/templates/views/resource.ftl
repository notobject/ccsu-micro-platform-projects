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
                                    <th>名称</th>
                                    <th>IP地址</th>
                                    <th>队列名称</th>
                                    <th>集群类型</th>
                                    <th>最近心跳时间</th>
                                    <th>操作</th>
                                </tr>
                                <#list resources as resource>
                                    <tr>
                                        <td>${resource.alias}</td>
                                        <td>${resource.ip}</td>
                                        <td>${resource.queue}</td>
                                        <td>
                                            <#list resource.machineType as type>
                                                <span class="badge badge-success">${type}</span>
                                            </#list>
                                        </td>
                                        <td>${(user.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                        <td>
                                            <a href="#" style="color: green">查看</a>
                                        </td>
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
                            <!-- 创建用户 -->
                            <div class="modal fade" id="createUser" tabindex="-1" role="dialog"
                                 aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                ×
                                            </button>
                                            <h4 class="modal-title">用户管理</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form id="form-new" class="form-horizontal tasi-form" method="post"
                                                          action="/user/update">
                                                        <div class="form-group">
                                                            <label class="col-sm-2 col-sm-2 control-label">用户名</label>
                                                            <div class="col-sm-5">
                                                                <input type="text" name="username" class="form-control"
                                                                       value="longduping">
                                                                <span class="help-block">登录账户</span>
                                                            </div>
                                                            <div class="col-sm-5">
                                                                <input type="text" name="name" class="form-control"
                                                                       value="龙杜平">
                                                                <span class="help-block">真实姓名</span>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-sm-2 col-sm-2 control-label">用户角色</label>
                                                            <div class="col-sm-4">
                                                                <select name="role" class="form-control">
                                                                    <option value="developer">开发者</option>
                                                                    <option value="administrator">管理员</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 col-sm-2 control-label">联系方式</label>
                                                            <div class="col-sm-5">
                                                                <input type="email" name="email" class="form-control"
                                                                       value="610215675@qq.com">
                                                                <span class="help-block">邮箱</span>
                                                            </div>
                                                            <div class="col-sm-5">
                                                                <input type="tel" name="phone" class="form-control"
                                                                       value="15874215675">
                                                                <span class="help-block">手机号</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 col-sm-2 control-label">密码</label>
                                                            <div class="col-sm-5">
                                                                <input type="password" name="password"
                                                                       class="form-control" placeholder="登录密码"
                                                                       value="123456">
                                                            </div>
                                                            <div class="col-sm-5">
                                                                <input type="password" name="conformPassword"
                                                                       class="form-control" placeholder="确认密码"
                                                                       value="123456">
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button data-dismiss="modal" class="btn btn-default" type="button">取消
                                            </button>
                                            <button data-dismiss="modal" class="btn btn-success" type="button"
                                                    id="submit">保存
                                            </button>
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
<script language="JavaScript">

</script>
<#include "/base/footer.ftl">
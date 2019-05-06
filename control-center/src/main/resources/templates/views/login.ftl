<#include "/base/header.ftl">
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-8">
                <!--breadcrumbs start -->
                <ul class="breadcrumb">
                    <li><a href="/"><i class="fa fa-home"></i> 云控中心</a></li>
                    <li class="active">用户登录</li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <section class="panel general">
                    <form action="/user/login" method="post" class="form-horizontal tasi-form">
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">账号</label>
                            <div class="col-sm-6">
                                <input type="text" name="username" class="form-control"
                                       value="longduping">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">密码</label>
                            <div class="col-sm-6">
                                <input type="password" name="password" class="form-control"
                                       value="123456">
                            </div>
                        </div>
                        <center><input type="submit" class="btn btn-success" value="登录"/></center>
                    </form>
                </section>
            </div>
        </div>
    </section>
</aside>
<#include "/base/footer.ftl">
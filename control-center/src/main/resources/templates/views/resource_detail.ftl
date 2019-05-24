<#include "/base/header.ftl">
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <!--breadcrumbs start -->
                <ul class="breadcrumb">
                    <li><a href="/"><i class="fa fa-home"></i> 云控中心</a></li>
                    <li><a href="/resource">资产管理</a></li>
                    <li class="active">${resource.alias}</li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <canvas id="chart-pie-mem" width="350" height="350"></canvas>
            </div>
            <div class="col-md-6">
                <canvas id="chart-pie-disk" width="350" height="350"></canvas>
            </div>
        </div>
    </section>
</aside>
<script type="text/javascript">
    $(function () {
        var pieDataMem = [
            {
                value: ${resource.freeMem},
                color: "#0a7498",
                highlight: "#089298",
                label: "空闲内存（MB）"

            },
            {
                value:  ${resource.totalMem - resource.freeMem},
                color: "#fa3422",
                highlight: "#fa3422",
                label: "已用内存（MB）"
            }
        ];
        var pieDataDisk = [
            {
                value: ${resource.freeDisk},
                color: "#0a7498",
                highlight: "#089298",
                label: "空闲硬存（MB）"

            },
            {
                value:  ${resource.totalDisk - resource.freeDisk},
                color: "#fa3422",
                highlight: "#fa3422",
                label: "已用硬存（MB）"
            }
        ];
        var ctxMem = document.getElementById("chart-pie-mem").getContext("2d");
        var ctxDisk = document.getElementById("chart-pie-disk").getContext("2d");
        window.myPie = new Chart(ctxMem).Pie(pieDataMem);
        window.myPie = new Chart(ctxDisk).Pie(pieDataDisk);
    });
</script>
<#include "/base/footer.ftl">
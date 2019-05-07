<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="../static/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<div id="content">
</div>
</body>

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
                console.log(res);
                content = $("#content");
                msg = "";
                if (res.status === "waiting" && content.val() === "") {
                    msg = "waiting for response...\n";
                } else if (res.status === "complete") {
                    msg = res.msg + "done.\n";
                    clearInterval(timer);
                } else {
                    msg = res.msg
                }
                if (msg !== "") {
                    content.append("<p>" + msg.replace("\n", "<br />") + "</p>");
                }
            }
        })
    }

    taskId = "${taskId}";
    timer = setInterval(function () {
        startLoop(taskId, timer);
    }, 1000);

</script>
</html>
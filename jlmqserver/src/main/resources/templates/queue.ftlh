<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <#--<script>
        function changeValue() {
            var name = document.getElementById("name_input").value;
            document.getElementsByTagName('span')[0].innerText = "" + name;
        }
    </script>-->
</head>
<body>
<form action="/queue" method="post">
    <label for="">Queue</label>
    <input type="text" name="queueName" placeholder="Queue name">
    <input type="submit" value="Send">
</form>
</body>
</html>
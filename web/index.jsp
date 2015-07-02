<%--
  Created by IntelliJ IDEA.
  User: Vertigo633
  Date: 28.06.2015
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script class="jsbin" src="static/script.js"></script>
<head>
    <link rel="stylesheet" type="text/css" href="static/style.css">
    <title>Image Resizer 1.0</title>
</head>
<body>

<div align="center">
    <img src="static/images/ImageResize.png">
</div>
<br>

<form enctype="multipart/form-data" name="sendForm" method="POST" action="/">
    File to upload: <input type="file" name="image" accept=".jpg,.jpeg"><br/>
    Id: <input type="text" id="id" name="id"><br/> <br/>
    <input type="submit" value="upload" onclick="changeTextBox()"> Press here to upload the file!
</form>


<script language="JavaScript">
    function changeTextBox() {
        var image_id = document.getElementById("id").value;
        document.sendForm.action = "/api/v1/picture-resizer/" + image_id + "/upload";
        console.info(document.sendForm.action);
        return true;
    }

</script>

</body>
</html>
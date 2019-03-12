<%@page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; utf-8" %>
<% request.setAttribute("path",request.getContextPath()); %>
<html>
    <head>
        <title>文件的上传下载</title>
        <script type="text/javascript" src="${path}/js/jquery-1.8.3.js"></script>
        <script type="text/javascript">
                function verify() {
                    if ($("#file").val() != "") {
                        return true;
                    } else {
                        window.alert("请选择您要上传的文件");
                        return false;
                    }
                }
        </script>
    </head>
    <body>
        <form action="${path}/papers/upLoad" onsubmit="return verify()" method="post" enctype="multipart/form-data" id="fo">
            <table>
                <tr><td><input type="file" name="multipartFile" id="file"></td></tr>
                <tr><td><input type="submit" value="文件提交"></td></tr>
            </table>
        </form>
        <a href="${path}/downLoad.jsp">文件下载</a>
    </body>
</html>
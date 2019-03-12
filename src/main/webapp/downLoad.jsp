<%@page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<% request.setAttribute("path",request.getContextPath()); %>
<html>
    <head>
        <title>文件下载</title>
        <script type="text/javascript" src="${path}/js/jquery-1.8.3.js"></script>
        <script type="text/javascript">
            $(function () {
                $.ajax({
                    url:"${path}/papers/selectAll",
                    type:"post",
                    success:function(r){
                        var tb=$("#tb");
                        for (var i = 0; i <r.length ; i++) {
                            var t=$("<tr><td>"+r[i].name+"</td><td>"+r[i].path+"</td><td><a href=\"${path}/papers/downLoad?path="+r[i].path+"\">下载</a></td></tr>");
                            tb.append(t);
                        }
                    }
                });
            });
        </script>
    </head>
    <body>
        <table border="1px" bgcolor="#ffe4c4">
            <tr><td>文件名</td><td>下载路径</td><td>操作</td></tr>
            <tbody id="tb">
            </tbody>
        </table>
    </body>
</html>
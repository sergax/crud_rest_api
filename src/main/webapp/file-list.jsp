<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
 <title>Files</title>
</head>
<body>
 <center>
  <h1>Files Management</h1>
        <h2>
         <a href="file/new">Add New File</a>
          &nbsp;&nbsp;&nbsp;
        </h2>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Files</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Options</th>
            </tr>
            <c:forEach var="file" items="${listFile}">
                <tr>
                    <td><c:out value="${file.file_id}" /></td>
                    <td><c:out value="${file.fileName}" /></td>
                    <td>
                     <a href="edit?id=<c:out value='${file.file_id}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${file.file_id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
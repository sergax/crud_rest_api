<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title>User</title>
</head>
<body>
 <center>
  <h1>Fie Management</h1>
        <h2>
         <a href="file/new">Add New File</a>
         &nbsp;&nbsp;&nbsp;
         <a href="file/list">List All Files</a>
        </h2>
 </center>
    <div align="center">
  <c:if test="${file != null}">
   <form action="update" method="post">
        </c:if>
        <c:if test="${file == null}">
   <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${file != null}">
               Edit File
              </c:if>
              <c:if test="${file == null}">
               Add New File
              </c:if>
             </h2>
            </caption>
          <c:if test="${user != null}">
           <input type="hidden" name="id" value="<c:out value='${file.file_id}' />" />
          </c:if>
            <tr>
                <th>User Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${file.fileName}' />"
                  />
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>
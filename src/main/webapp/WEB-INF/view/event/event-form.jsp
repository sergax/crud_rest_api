<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>Event Management</title>
</head>
<body>
 <center>
  <h1>Event Management</h1>
        <h2>
         <a href="new">Add New Event</a>
         &nbsp;&nbsp;&nbsp;
         <a href="listEvent">List All Events</a>
        </h2>
 </center>
    <div align="center">
  <c:if test="${event != null}">
   <form action="update" method="post">
        </c:if>
        <c:if test="${event == null}">
   <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${event != null}">
               Edit Event
              </c:if>
              <c:if test="${event == null}">
               Add New Event
              </c:if>
             </h2>
            </caption>
          <c:if test="${event != null}">
           <input type="hidden" name="id" value="<c:out value='${event.event_id}' />" />
          </c:if>
            <tr>
                <th>Event Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${event.eventName}' />"
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
        <table border="1" cellpadding="5">
            <caption><h2>List of Events</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>File Name</th>
                <th>Options</th>
            </tr>
            <c:forEach var="event" items="${listEvent}">
                <tr>
                    <td><c:out value="${event.event_id}" /></td>
                    <td><c:out value="${event.eventName}" /></td>
                    <td><c:out value="${file.fileName}" /></td>
                    <td>
                     <a href="edit?id=<c:out value='${event.event_id}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${event.event_id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
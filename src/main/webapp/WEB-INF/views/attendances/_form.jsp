<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpt" value="${ForwardConst.CMD_UPDATE.getValue()}" />


<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>


<fmt:parseDate value="${attendance.attendanceDate}" pattern="yyyy-MM-dd" var="attendanceDay" type="date" />
<label for="${AttributeConst.ATT_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.ATT_DATE.getValue()}" value="<fmt:formatDate value='${attendanceDay}' pattern='yyyy-MM-dd' />" />
<br /><br />


<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />



        <form action="/?action=Attendance&command=atWork">
            <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_AT_WORK.getValue()}" value="${attendance.attendance_at_work}">出勤</button><br />
            <br /><br />
        </form>

        <form action="/?action=Attendance&command=breakStart">
            <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_BREAK_START.getValue()}" value="${attendance.attendance_break_start}">休憩開始</button><br />
            <br /><br />
        </form>

        <form action="/?action=Attendance&command=endOfBreak">
            <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_END_OF_BREAK.getValue()}" value="${attendance.attendance_end_of_break}">休憩終了</button><br />
            <br /><br />
        </form>

        <form action="/?action=Attendance&command=leavingWork">
            <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_LEAVING_WORK.getValue()}" value="${attendance.attendance_leaving_work}">退勤</button><br />
            <br /><br />
        </form>




<input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />




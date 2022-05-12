<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actAtt" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>勤怠　一覧</h2>

         <c:if test="${sessionScope.login_employee.id == attendance.employee.id}">
            <p>
                <li><a href="<c:url value='?action=${actAtt}&command=${commEdt}&id=${attendance.id}' />">休憩開始、休憩終了、退勤へ進む</a></li>
            </p>
        </c:if>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${attendance.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${attendance.attendanceDate}" pattern="yyyy-MM-dd" var="attendanceDay" type="date" />
                    <td><fmt:formatDate value='${attendanceDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>出勤時間</th>
                    <fmt:parseDate value="${attendance.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="atWorkDay" type="date" />
                    <td><fmt:formatDate value="${atWorkDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>休憩開始時間</th>
                    <fmt:parseDate value="${attendance.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="breakStartDay" type="date" />
                    <td><fmt:formatDate value="${breakStartDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>休憩終了時間</th>
                    <fmt:parseDate value="${attendance.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="endOfBreakDay" type="date" />
                    <td><fmt:formatDate value="${endOfBreakDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>退勤時間</th>
                    <fmt:parseDate value="${attendance.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="leavingWorkDay" type="date" />
                    <td><fmt:formatDate value="${leavingWorkDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>



        <p>
            <a href="<c:url value='?action=${actAtt}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>




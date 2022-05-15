<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action"  value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx"  value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commBre" value="${ForwardConst.CMD_BREAKSTART.getValue()}" />
<c:set var="commEnd" value="${ForwardConst.CMD_ENDOFBREAK.getValue()}" />
<c:set var="commLea" value="${ForwardConst.CMD_LEAVINGWORK.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>勤怠</h2>



         <form method="POST" action="<c:url value='?action=${action}&command=${commBre}&id=${attendance.id}' />">
           <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_BREAK_START.getValue()}" value="${attendance.attendance_break_start}">休憩開始</button><br />
           <br /><br />
        </form>

        <form method="POST" action="<c:url value='?action=${action}&command=${commEnd}&id=${attendance.id}' />">
            <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_END_OF_BREAK.getValue()}" value="${attendance.attendance_end_of_break}">休憩終了</button><br />
            <br /><br />
        </form>

        <form method="POST" action="<c:url value='?action=${action}&command=${commLea}&id=${attendance.id}' />">
            <button type="submit" name="${AttributeConst.ATT_ATTENDANCE_LEAVING_WORK.getValue()}" value="${attendance.attendance_leaving_work}">退勤</button><br />
            <br /><br />
         </form>


        <p><a href="<c:url value='?action=${action}&command=${commIdx}' />">戻る</a></p>



        <input type="hidden" name="${AttributeConst.ATT_ID.getValue()}" value="${attendance.id}" />
        <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />


    </c:param>
</c:import>
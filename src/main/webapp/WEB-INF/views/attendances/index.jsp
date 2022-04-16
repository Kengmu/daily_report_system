<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actAtt" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>勤怠</h2>
        <input type = "submit" value="出勤" onClick="return confirm('出勤しました')">
        <input type = "submit" value="退勤" onClick="return confirm('退勤しました')">
        <input type = "submit" value="休憩開始" onClick="return confirm('休憩開始しました')">
        <input type = "submit" value="休憩終了" onClick="return confirm('休憩終了しました')">

<p><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">戻る</a></p>

    </c:param>
</c:import>
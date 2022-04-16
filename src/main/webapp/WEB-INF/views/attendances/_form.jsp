<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_ATT.getValue()}" />
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
        <h2>日報　一覧</h2>

　　　　<ul>
                <li><a href="<c:url value='?action=${actAtt}&command=${commCreate}' />">出勤</a></li>
                <li><a href="<c:url value='?action=${actAtt}&command=${commUpdate}' />">休憩開始</a></li>
                <li><a href="<c:url value='?action=${actAtt}&command=${commUpdate}' />">休憩終了</a></li>
                <li><a href="<c:url value='?action=${actAtt}&command=${commUpdate}' />">退勤</a></li>
            </ul>



<p><a href="<c:url value='?action=${action}&command=${commIdx}' />">一覧に戻る</a></p>

    </c:param>
</c:import>
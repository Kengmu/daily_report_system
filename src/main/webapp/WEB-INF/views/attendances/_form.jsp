<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action"  value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commAtw" value="${ForwardConst.CMD_ATWORK.getValue()}" />
<c:set var="commBre" value="${ForwardConst.CMD_BREAKSTART.getValue()}" />
<c:set var="commEnd" value="${ForwardConst.CMD_ENDOFBREAK.getValue()}" />
<c:set var="commLea" value="${ForwardConst.CMD_LEAVINGWORK.getValue()}" />





<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>





<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />


        <form action="/?action=Attendance&command=atWork">

        </form>

        <form action="/?action=Attendance&command=breakStart">
            <br /><br />
        </form>

        <form action="/?action=Attendance&command=endOfBreak">

        </form>

        <form action="/?action=Attendance&command=leavingWork">

        </form>








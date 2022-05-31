<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springmvc1.servlet.domain.member.Member"%>
<%@ page import="springmvc1.servlet.domain.member.MemberRepository"%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");

    //request, response: JSP에서 선언 없이 바로 사용 가능
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
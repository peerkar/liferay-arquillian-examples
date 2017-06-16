<%@ include file="init.jsp" %>

<%
	int firstParameter = ParamUtil.getInteger(request, "firstParameter", 1);
	int secondParameter = ParamUtil.getInteger(request, "secondParameter", 1);
	int result = ParamUtil.getInteger(request, "result");
%>
 
<portlet:actionURL name="add" var="portletURL" />

<h1>Hello Arquillian</h1>

<p>This is a simple mvc portlet Arquillian test.</p>

<aui:form action="<%= portletURL %>" method="post" name="fm">

	<aui:input 
		inlineField="<%= true %>" 
		label="" name="firstParameter" 
		size="4" 
		type="int" 
		value="<%= firstParameter %>" 
	/>

	<span> + </span>

	<aui:input 
		inlineField="<%= true %>" 
		label="" 
		name="secondParameter" 
		size="4" type="int" 
		value="<%= secondParameter %>" 
	/>
	<span> = </span>
	<span class="result"><%= result %></span>

	<aui:button type="submit" value="add" />
</aui:form>
<%@ include file="init.jsp" %>

<%
	List<GuestbookEntry> entries = (List<GuestbookEntry>)request.getAttribute("entries");

%>
 
<portlet:actionURL name="addEntry" var="portletURL" />

<h1><liferay-ui:message key="guestbook"></liferay-ui:message></h1>

<%
	if (entries != null) {
		%>
		<div class="entries">
		<%
		for (GuestbookEntry entry : entries) {
			%>
				<div class="entry">
					<div class="text"><%=HtmlUtil.escape(entry.getText()) %></div>
					<div class="author"><%=HtmlUtil.escape(entry.getAuthor()) %>, <%=entry.getCreateDate() %></div>
				</div>
			<% 
		}
		%>
		</div>
		<%		
	}
%>

<div class="new-entry-form">
	<h2><liferay-ui:message key="add-new"></liferay-ui:message></h2>
	
	<aui:form action="<%= portletURL %>" method="post" name="fm">
	
		<aui:field-wrapper>
		<aui:input name="text" type="textarea">
			<aui:validator name="required"></aui:validator> 
			<aui:validator name="maxLength">100</aui:validator> 
		</aui:input>
		<liferay-ui:error key="error-author-cannot-be-empty" message="error-author-cannot-be-empty" />
		<liferay-ui:error key="error-author-too-long" message="error-author-too-long" />
		</aui:field-wrapper>
		
		<aui:input name="author" type="text">
			<aui:validator name="required"></aui:validator>
			<aui:validator name="maxLength"><%=EntryValidator.TEXT_MAX_LENGTH %></aui:validator> 
		</aui:input>
		<liferay-ui:error key="error-text-cannot-be-empty" message="error-text-cannot-be-empty" />
		<liferay-ui:error key="error-text-too-long" message="error-text-too-long" />
		
		<aui:button-row>
			<aui:button type="submit" value="add" />
		</aui:button-row>
	</aui:form>
</div>
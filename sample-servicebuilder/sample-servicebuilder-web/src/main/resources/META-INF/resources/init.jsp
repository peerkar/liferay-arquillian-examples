<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.DateUtil"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@page import="fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry"%>
<%@page import="fi.soveltia.arquillian.servicebuilder.service.GuestbookEntryLocalService"%>
<%@page import="fi.soveltia.arquillian.util.EntryValidator"%>

<portlet:defineObjects />

<liferay-theme:defineObjects />
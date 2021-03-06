<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%@ page contentType="text/html" isELIgnored="false" %>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<c:set var="BASEURL"><c:url value="/"/></c:set>


<div id="content">
    <ol>
        <c:forEach var="contact" items="${contactList}">
            <li class="ui-widget-content ui-corner-all">

                <jsp:directive.include file="/WEB-INF/jsp/contact.jsp"/>
                
            </li>
        </c:forEach>            
    </ol>
</div>

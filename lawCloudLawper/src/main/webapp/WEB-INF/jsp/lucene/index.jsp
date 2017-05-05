<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ include file="tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${pageContext.request.contextPath}/static/css/lucene_index.css" rel="stylesheet" type="text/css"/>
    <title>全文检索</title>

</head>
<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div id="m">
    <p id="lg">
        <img src="${pageContext.request.contextPath}/static/image/baidu_sylogo1.gif" width="270" height="129"
             usemap="#mp">
    </p>

    <div id="fm">
        <form name="f" action="lucene/search" method="get">
            <input type="text" name="keyword" id="kw" maxlength="100"/>
            <input type="hidden" name="type" value="">
            <input type="hidden" name="page" value="1">
            <span class="btn_wr"><input type="submit" value="搜索文档"
                                        id="su" class="btn" onMouseDown="this.className='btn btn_h'"
                                        onMouseOut="this.className='btn'">
					</span>
        </form>
    </div>

    <p id="lm"></p>

    <p id="lh">

    </p>
    <p id="cp">
        &copy;2012 Jsou&nbsp;|&nbsp;
        <a href="#">Powered By CoolBoy</a>
    </p>
</div>

</body>

</html>
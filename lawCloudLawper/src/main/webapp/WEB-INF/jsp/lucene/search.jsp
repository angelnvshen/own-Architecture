<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ include file="tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>全文检索</title>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/static/css/asCom.css"/>
    <style type="text/css">
        #wrapper {
            min-width: 800px;
        }
    </style>

    <link href="${ctx}/static/upload/css/default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/upload/css/uploadify.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/upload/js/swfobject.js"></script>
    <script type="text/javascript" src="${ctx}/static/upload/js/jquery.uploadify.v2.1.4.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            // var i = 1;
            $("#uploadify").uploadify({
                'uploader': 'ctx/static/upload/uploadify.swf',
                'script': 'ctx/biz/sou/util/Upload',
                'cancelImg': 'ctx/tools/upload/images/cancel.png',
                'folder': 'AttachmentDir',
                'queueID': 'fileQueue',
                'auto': false,
                'multi': true,
                'simUploadLimit': 2,
                'fileDesc': '图片文件',          //出现在上传对话框中的文件类型描述
                'fileExt': '*.txt;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.pdf',      //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
                'buttonImg': 'ctx/tools/upload/images/upload.png',
                'width': '155',
                'height': '34',
                'onComplete': function (event, queueID, fileObj, response, data) {
                    alert("文件:" + response + " 上传成功");
                    do_index();
                    //$("#picture").attr("value",response);
                    //$("#imgID").attr("src","http://localhost:8080/bolan"+response);
                },
                'onError': function (event, queueID, fileObj) {
                    alert("文件:" + fileObj.name + " 上传失败");
                }

            });

        });

    </script>

    <script type="text/javascript">
        function do_index() {
            //alert("here!");
            $.ajax({
                url: 'lucene/do_index',
                type: 'GET',
                //data:{album_id :  3},
                //dataType: 'json',
                //async: false
                //timeout: 10000,
                //error: function(){
                //alert('Error ');
                //},
                success: function (data) {
                    alert("ok you are here");
                }
            });
        }

    </script>
</head>
<body>
<div id="wrapper">
    <style type="text/css">
        #search {
            padding-bottom: 9px;
        }

        #page.page-nav {
            position: relative;
            overflow: visible;
        }

        #page .nums {
            position: absolute;
            right: -195px;
            top: 11px;
        }

        .tangram-suggestion {
            width: 550px;
        }

        @-moz-document url-prefix()
        {
        . tangram-suggestion {
            margin-left: 0px;
        }

        }
    </style>
    <div id="search" class="search-box">
        <div class="s_nav">
            <a href="${ctx}/lucene/index" class="s_logo"><img id="logoImg"
                                                               src="${ctx}/image/logo-wk.gif"
                                                               width="117" height="38" alt="捷搜文库">
            </a>
        </div>
        <form action="${ctx}/lucene/search" name="ftop" id="topSearchBox" method="get">
            <input type="hidden" name="page" value="1">
            <span class="s_ipt_wr"><input id="kw" name="keyword"
                                          class="s_ipt" maxlength="256" tabindex="1" value="${keyword}"
                                          maxlength="100" autocomplete="off"/>
					</span><span class="s_btn_wr"><input type="submit" id="sb"
                                                         value="搜索文档" class="s_btn"
                                                         onmouseover="this.className='s_btn s_btn_h'"
                                                         onmousedown="this.className='s_btn s_btn_d'"
                                                         onmouseout="this.className='s_btn'">
					</span>
            <div class="g-sl">
                <label for="all">
                    <input type="radio" name="type" value="" <c:if test="${type==''}"> checked="checked" </c:if>
                           id="all"/>
                    全部
                </label>
                <label for="doc">
                    <input type="radio" name="type" value="doc" <c:if test="${type=='doc'}"> checked="checked" </c:if>
                           id="doc"/>
                    DOC
                </label>
                <label for="pdf">
                    <input type="radio" name="type" value="pdf" <c:if test="${type=='pdf'}"> checked="checked" </c:if>
                           id="pdf"/>
                    PDF
                </label>
                <label for="ppt">
                    <input type="radio" name="type" value="ppt" <c:if test="${type=='ppt'}"> checked="checked" </c:if>
                           id="ppt"/>
                    PPT
                </label>
                <label for="xls">
                    <input type="radio" name="type" value="xls" <c:if test="${type=='xls'}"> checked="checked" </c:if>
                           id="xls"/>
                    XLS
                </label>
                <label for="txt">
                    <input type="radio" name="type" value="txt" <c:if test="${type=='txt'}"> checked="checked" </c:if>
                           id="txt"/>
                    TXT
                </label>
                <div style="clear: both"></div>
            </div>

        </form>
    </div>
    <div id="crumb-nav" class="crumb-nav">
        <a href="/" onmousedown="log.send('general','crumb1')">捷搜文库</a>&nbsp;&gt;&nbsp;
        搜索结果
    </div>
    <div id="main" class="main">

        <div class="filter">
            排序：
            <b>相关性</b>
            <!--  |<a href="#">最多下载</a>|
            <a href="#">最新上传</a>-->
        </div>
        <div class="cpmsg-wrap">
            <img class="cpmsg-icon" alt="" src="http://img.baidu.com/img/iknow/docshare/cpmsg-alert.gif"/>
            我们不允许任何未经著作权人同意而将其作品进行上传和公开分享的行为。
        </div>

        <c:forEach items="${attachment}" var="att">
            <dl>
                <dt>
                    <c:if test="${att.filetype=='txt'}">
                        <span title="txt" class="txt icon"></span>
                    </c:if>
                    <c:if test="${att.filetype=='doc'||att.filetype=='docx'}">
                        <span title="doc" class="doc icon"></span>
                    </c:if>
                    <c:if test="${att.filetype=='xls'||att.filetype=='xlsx'}">
                        <span title="xls" class="xls icon"></span>
                    </c:if>
                    <c:if test="${att.filetype=='ppt'||att.filetype=='pptx'}">
                        <span title="ppt" class="ppt icon"></span>
                    </c:if>
                    <c:if test="${att.filetype=='pdf'}">
                        <span title="pdf" class="pdf icon"></span>
                    </c:if>


                    <a href="${ctx}/lucene/Download?filepath=${att.filepath}&filename=${att.filename}.${att.filetype}"
                       target="_self"><em>${att.filename}</em>
                    </a>
                    <span class="ml12 gray">${att.filedate}</span>
                </dt>
                <dd>
                    <p class="summary">
                            ${att.hitword}
                    </p>
                    <p class="detail">
                        文件路径:
                            ${att.filepath}
                        <b>|</b>
                        文档得分：
                        <c:if test="${att.filescore >= 0.99}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.9 && att.filescore < 0.99}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-half" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.8 && att.filescore < 0.9}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.7 && att.filescore < 0.8}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-half" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.6 && att.filescore < 0.7}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.5 && att.filescore < 0.6}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-half" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.4 && att.filescore < 0.5}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.3 && att.filescore < 0.4}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-half" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0.2 && att.filescore < 0.3}">
                            <span class="icon star-small-on" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>
                        <c:if test="${att.filescore >= 0 && att.filescore < 0.2}">
                            <span class="icon star-small-half" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                            <span class="icon star-small-off" title="文档得分:${att.filescore}分"></span>
                        </c:if>


                    </p>
                </dd>
            </dl>

        </c:forEach>

        <c:if test="${page.tot_item>0}">
            <div id="page" class="page-nav page-center">
                <div class="page-inner">
                    <div class="page">


                        <c:set value="1" var="f_page"></c:set>
                        <c:set value="1" var="end_page"></c:set>

                        <a href="${ctx}/lucene/search?keyword=${keyword}&type=${type}&page=1" class="first">首页</a>

                        <c:if test="${page.curr_rs>1}">
                            <a href="${ctx}/lucene/search?keyword=${keyword}&type=${type}&page=${page.curr_rs-1}"
                               class="pre">&lt;上一页</a>
                        </c:if>

                        <c:if test="${page.curr_rs<5&&page.curr_rs>1}">
                            <c:forEach var="i" begin="1" end="${page.curr_rs-1}" step="1">
                                <c:choose><c:when test="${page.curr_rs==i}"><span
                                        class="cur no1">${i}</span></c:when><c:otherwise><a
                                        href="${ctx}/lucene/search?keyword=${keyword}&type=${type}&page=${i}"
                                        class="no1">${i}</a></c:otherwise></c:choose>
                            </c:forEach>
                        </c:if>

                        <c:if test="${page.curr_rs>5||page.curr_rs==5}">
                            <c:forEach var="i" begin="${page.curr_rs-4}" end="${page.curr_rs-1}" step="1">
                                <c:choose><c:when test="${page.curr_rs==i}"><span
                                        class="cur no1">${i}</span></c:when><c:otherwise><a
                                        href="${ctx}/lucene/search?keyword=${keyword}&type=${type}&page=${i}"
                                        class="no1">${i}</a></c:otherwise></c:choose>
                            </c:forEach>
                        </c:if>

                        <c:if test="${page.tot_page>page.curr_rs+10}">
                            <c:set value="${page.curr_rs+10}" var="end_page"></c:set>
                        </c:if>
                        <c:if test="${page.tot_page<=page.curr_rs+10}">
                            <c:set value="${page.tot_page}" var="end_page"></c:set>
                        </c:if>

                        <c:forEach var="i" begin="${page.curr_rs}" end="${end_page}" step="1">
                            <c:choose><c:when test="${page.curr_rs==i}"><span
                                    class="cur no1">${i}</span></c:when><c:otherwise><a
                                    href="${ctx}/lucene/search?keyword=${keyword}&type=${type}&page=${i}"
                                    class="no1">${i}</a></c:otherwise></c:choose>
                        </c:forEach>
                        <c:if test="${page.tot_page!=page.curr_rs}">
                            <a href="${ctx}/lucene/search?keyword=${keyword}&type=${type}&page=${page.curr_rs+1}"
                               class="next">下一页&gt;</a>
                        </c:if>


                    </div>
                </div>
                <span class="nums">找到相关文档&nbsp;<strong>${page.tot_item}</strong>&nbsp;篇</span>
            </div>


        </c:if>

        <c:if test="${page.tot_item==0}">
            <div style="text-align:center; width:100%">暂时没有任何信息</div>
        </c:if>

        <!-- <div id="page" class="page-nav page-center">
					<div class="page-inner">
						<div class="page">
							<span class="cur no1">1</span>
							<a href="/search?word=java&lm=0&od=0&pn=10" class="no1">2</a><a
								href="/search?word=java&lm=0&od=0&pn=20" class="no1">3</a><a
								href="/search?word=java&lm=0&od=0&pn=30" class="no1">4</a><a
								href="/search?word=java&lm=0&od=0&pn=40" class="no1">5</a><a
								href="/search?word=java&lm=0&od=0&pn=50" class="no1">6</a><a
								href="/search?word=java&lm=0&od=0&pn=60" class="no1">7</a><a
								href="/search?word=java&lm=0&od=0&pn=70" class="no1">8</a><a
								href="/search?word=java&lm=0&od=0&pn=80" class="no1">9</a><a
								href="/search?word=java&lm=0&od=0&pn=90" class="no2">10</a><a
								href="/search?word=java&lm=0&od=0&pn=10" class="next">下一页&gt;</a><a
								href="/search?word=java&lm=0&od=0&pn=750" class="last">尾页</a>
						</div>
					</div>
					<span class="nums">找到相关文档&nbsp;<strong>${page.tot_item}</strong>&nbsp;篇</span>
				</div>
 -->
    </div>
    <div id="aside" class="aside">
        <br/>
        <div class="upbox">
            <div class="content">
                <!--<div class="upload" id="upload"> </div> -->
                <div align="center">
                    <div id="fileQueue"></div>

                    <!--  <input id="picture" type="text" size="40" name="book_url"
                        value="" />-->

                    <input type="file" name="uploadify" id="uploadify"/>
                    <p>
                        <a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
                        <a
                                href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
                    </p>

                </div>
            </div>
        </div>
    </div>


    <div class="clear"></div>
    <div id="search_bot" class="search-box"></div>
    <div id="footer" class="footer-search">
        <p>
            <span class="cr">&copy;2012 Jsou</span>&nbsp;|&nbsp;
            <a href="#" class="Bidu" target="_blank" class="ml">Powered By CoolBoy</a>
        </p>
    </div>
</body>
</html>
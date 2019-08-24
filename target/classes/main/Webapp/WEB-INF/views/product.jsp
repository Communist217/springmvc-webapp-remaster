<%--
  Created by IntelliJ IDEA.
  User: MinhPC
  Date: 6/17/2019
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%--JSTL tags--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="/taglib/taglib.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><c:out value="${Product_Data.getProductname()}">N/A</c:out></title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Navigation_Content.css'/>" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Product_Content.css'/>" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/bootstrap-4.1.3-dist/css/bootstrap.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/star-rating-svg.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Sweet/sweetalert2.css'/>">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <%--Query--%>
        <sql:setDataSource var="shop_db" driver = "com.mysql.cj.jdbc.Driver"
                           url = "jdbc:mysql://localhost:3306/onlineshopdatabase?useSSL=false"
                           user = "root" password = "minhngoc61021"/>
        <c:set var="ProductID" value="${Product_Data.getProductID()}"
               scope="request" />
        <sql:query dataSource="${shop_db}" var="Product_Description">
            Select description
            from products
            where productid = ?;
            <sql:param value="${Product_Data.getProductID()}"/>
        </sql:query>
        <sql:query dataSource="${shop_db}" var="Product_Warranty">
            Select warranty
            from products
            where productid = ?;
            <sql:param value="${Product_Data.getProductID()}"/>
        </sql:query>
        <sql:query dataSource="${shop_db}" var="Review">
            Select re.PostID, re.RatingValue, re.ReviewComment, m.MemberID, m.Fullname
            from review re inner join members m
            on re.MemberID = m.MemberID
            where re.ProductID = ?;
            <sql:param value="${Product_Data.getProductID()}"/>
        </sql:query>
        <sql:query dataSource="${shop_db}" var="Avg_Rating">
            Select avg(RatingValue) as average_rating
            from review
            where ProductID = ?;
            <sql:param value="${Product_Data.getProductID()}"/>
        </sql:query>
    </head>
    <body style="font-family: 'Calibri', sans-serif; font-size: 16px;">
        <input type="hidden" class="UserID" value="<c:out value="${User.getUserID()}"/>">
        <!--Header of the page.-->
        <%@ include file="header.jsp" %>

        <%--Product Content--%>
        <section class="Product-Content-Area">
            <div id="Content-group">
                <c:forEach var="Overall_Ratings" items="${Avg_Rating.rows}">
                    <h2 style="display: inline-block;">${Product_Data.getProductname()}</h2>
                    <div id="Total_rating" style="display: inline;">
                        <div class="my-rating-1" data-rating="${Overall_Ratings.average_rating}" style="display: inline-block; padding-bottom: 5px; position: absolute; margin-top: 1.3rem; margin-left: 0.6rem;">
                        </div>
                        <c:if test="${((Overall_Ratings.average_rating / 5) * 100) > 60}">
                            <a href="#Comment" style="display: inline; position: absolute; margin-left: 119px; margin-top: 18px; text-decoration: none; color: #28a745;">
                                <strong>
                                    <fmt:formatNumber type = "number" maxFractionDigits="0" value = "${(Overall_Ratings.average_rating / 5) * 100}"></fmt:formatNumber>% Positive
                                </strong>
                            </a>
                        </c:if>
                        <c:if test="${((Overall_Ratings.average_rating / 5) * 100) <= 60 and ((Overall_Ratings.average_rating / 5) * 100) >= 40}">
                            <a href="#Comment" style="display: inline; position: absolute; margin-left: 119px; margin-top: 18px; text-decoration: none; color: #ffc107;">
                                <strong>
                                    <fmt:formatNumber type = "number" maxFractionDigits="0" value = "${(Overall_Ratings.average_rating / 5) * 100}"></fmt:formatNumber>% Positive
                                </strong>
                            </a>
                        </c:if>
                        <c:if test="${((Overall_Ratings.average_rating / 5) * 100) < 40}">
                            <a href="#Comment" style="display: inline; position: absolute; margin-left: 119px; margin-top: 18px; text-decoration: none; color: #dc3545;">
                                <strong>
                                    <fmt:formatNumber type = "number" maxFractionDigits="0" value = "${(Overall_Ratings.average_rating / 5) * 100}"></fmt:formatNumber>% Positive
                                </strong>
                            </a>
                        </c:if>
                    </div>
                </c:forEach>
                <div class="dropdown-divider"></div>
                <div style="display: table; margin: auto;">
                    <div class="Image">
                        <img src="${Product_Data.getImagesource()}" style="width: 400px;height:auto;">
                    </div>
                    <section class="Right_Content">

                        <%--Product description and rating section--%>
                        <div style="padding-bottom: 50px;">

                            <%--Product Price--%>
                            <h3>
                                <strong style="color: #e10c00;"><fmt:formatNumber type="number" maxFractionDigits="">${Product_Data.getPrice()}</fmt:formatNumber>₫</strong>
                            </h3>
                            <%--Product Description--%>
                            <h5><strong>Description:</strong></h5>
                            <div>
                                <c:forTokens items="${Product_Data.getDescription()}" delims="=" var="System_Description">
                                    <p style="margin-left: 10px;"> - <c:out value="${System_Description}"/></p>
                                </c:forTokens>
                            </div>

                            <%--Product Warranty--%>
                            <h5><strong>Warranty:</strong></h5>
                                <p style="margin-left: 10px;"> - <c:out value="${Product_Data.getWarranty()}">N/A</c:out></p>
                            <%--Product add to cart button--%>
                            <section class="Product_button">
                                <c:if test="${Product_Data.getStock() > 0}">
                                    <c:if test="${not empty User.getUserID()}">
                                        <a href="javascript:Add_To_Cart(${Product_Data.getProductID()});" class="Product_buy_button_succeeded" id="success" style="text-decoration: none;">Add to Cart</a>
                                    </c:if>
                                    <c:if test="${empty User.getUserID()}">
                                        <a href="javascript:void(0)" class="Product_buy_button_flopped" id="flopped" style="text-decoration: none;">Add to Cart</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${Product_Data.getStock() == 0}">
                                    <button class="Out_of_stock" disabled>Out of Stock</button>
                                </c:if>
                            </section>
                            </form>
                        </div>

                        <%-- User Reviews and comments--%>
                        <h5><strong>Reviews & Ratings:</strong></h5>
                        <section id="Comment">
                            <%--Progess rating bar--%>
                            <div style="margin-bottom: -5px;">
                                <c:forEach items="${ProgressBar}" var="entry">
                                    <!-- Positive -->
                                    <c:if test="${entry.key == 'Positive'}">
                                        <span>${entry.key}:</span>
                                        <div class="progress" style="height: 20px;">
                                            <c:if test="${entry.value > 0}">
                                                <div class="progress-bar bg-success" style="width: ${entry.value / RatingCount * 100}%">
                                                    <span style="display:inline; margin: 5px; font-size: 12px;">
                                                        <fmt:formatNumber type = "number" maxFractionDigits="1" value = "${entry.value / RatingCount * 100}"></fmt:formatNumber>% (${entry.value})
                                                    </span>
                                                </div>
                                            </c:if>
                                            <c:if test="${entry.value == 0}">
                                                <div class="progress-bar bg-success" style="width: 0%">
                                                    <span style="display:inline; color: black; margin: 5px; font-size: 12px;">
                                                        0% (0)
                                                    </span>
                                                </div>
                                            </c:if>
                                        </div><br>
                                    </c:if>
                                    <!-- Mixed -->
                                    <c:if test="${entry.key == 'Mixed'}">
                                        <span>${entry.key}:</span>
                                        <div class="progress" style="height: 20px;">
                                            <c:if test="${entry.value > 0}">
                                                <div class="progress-bar bg-warning" style="width: ${entry.value / RatingCount * 100}%">
                                                            <span style="display:inline; margin: 5px; font-size: 12px;">
                                                                <fmt:formatNumber type = "number" maxFractionDigits="1" value = "${entry.value / RatingCount * 100}"></fmt:formatNumber>% (${entry.value})
                                                            </span>
                                                </div>
                                            </c:if>
                                            <c:if test="${entry.value == 0}">
                                                <div class="progress-bar bg-warning" style="width: 0%;">
                                                            <span style="display:inline; color: black; margin: 5px; font-size: 12px;">
                                                                0% (0)
                                                            </span>
                                                </div>
                                            </c:if>
                                        </div><br>
                                    </c:if>
                                    <!-- Negative -->
                                    <c:if test="${entry.key == 'Negative'}">
                                        <span>${entry.key}:</span>
                                        <div class="progress" style="height: 20px;">
                                            <c:if test="${entry.value > 0}">
                                                <div class="progress-bar bg-danger" style="width: ${entry.value / RatingCount * 100}%">
                                                    <span style="display:inline; margin: 5px; font-size: 12px;">
                                                        <fmt:formatNumber type = "number" maxFractionDigits="1" value = "${entry.value / RatingCount * 100}"></fmt:formatNumber>% (${entry.value})
                                                    </span>
                                                </div>
                                            </c:if>
                                            <c:if test="${entry.value == 0}">
                                                <div class="progress-bar bg-danger" style="width: 0%;">
                                                    <span style="display:inline; color: black; margin: 5px; font-size: 12px;">
                                                        0% (0)
                                                    </span>
                                                </div>
                                            </c:if>
                                        </div><br>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <a href="javascript:Expand_Review_Form('${Product_Data.getProductname()}');" style="text-decoration: none; color: #005cbf;">Post Your Review</a>
                            <%--User Review Post form--%>
                            <div class="Post_review">
                                <div class="Opinion_form">
                                    <form class="Create_Review" style="margin-top: 5px;">
                                        <textarea id="Product_opinion" name="ReviewCmt" placeholder="Your Review..." class="Review-input"></textarea>
                                        <div style="height: 50px; margin-top: 9px;">
                                            <div id="rating">
                                                <p style="display: inline;">Your Rating:</p>
                                                <div class="my-rating" style="display: inline; position: absolute; margin-left: 4px;"></div>
                                                <p id="feedback" style=" display: inline; position: absolute; margin-left: 9rem;"></p>
                                            </div>
                                            <input type="hidden" name="Pid" value="${Product_Data.getProductID()}">
                                            <input type="hidden" name="Fullname" value="${User.getFullname()}">
                                            <input id="Userid" type="hidden" name="Uid" value="<c:out value="${User.getUserID()}">-1</c:out>">
                                            <input type="hidden" name="RatingVal" id="Product_rating">
                                            <a href="javascript:post_review();" class="Product_review_button" style="text-decoration: none;"><p>Post Review</p></a>
                                            <strong style="margin-top: 5px;" id="caution"></strong>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="dropdown-divider"></div>
                            <%--All Review Posts--%>
                            <c:if test="${Review.rowCount > 0 }">
                                <div class="User_Comments">
                                    <ul class="review_list">
                                        <c:forEach items="${Review.rows}" var="comment">
                                            <li>
                                                <fmt:requestEncoding value="UTF-8"/>
                                                <h6 style="display: inline-block;"><strong><c:out value="${comment.Fullname}">N/A</c:out></strong></h6>
                                                <div class="my-rating-1" data-rating="<c:out value="${comment.RatingValue}">0</c:out>" style="display: inline-block; margin-left: 0.6rem; position: absolute; padding-top: 10px;"></div>
                                                <div class="Reviews">
                                                    <p class="Reviews_Comments"><c:out value="${comment.ReviewComment}">N/A</c:out></p>
                                                    <a href="javascript:Reply_Button_Toggle('${comment.PostID}');" style="text-decoration: none; color: #007bff;">
                                                        <img id="Reply-button" src="template/IMG/reply-icon.png" style="max-width: 22px; max-height: 22px;" title="Reply"> <%--(<p style="display: inline;" id="CountReply<c:out value="${comment.PostID}"/>">)</p>--%>
                                                    </a>
                                                    <form class="Like_Submit" style="display: inline;">
                                                        <input type="hidden" name="UserId" value="${User.getUserID()}">

                                                            <%--Comment Later--%>
                                                        <sql:query dataSource="${shop_db}" var="Like_count">
                                                            Select count(MemberID) as total_like
                                                            from like_review
                                                            where PostID = <c:out value="${comment.PostID}"/>
                                                            group by PostID;
                                                        </sql:query>
                                                        <c:if test="${not empty User.getUserID()}">
                                                            <sql:query dataSource="${shop_db}" var="User_isLike">
                                                                Select *
                                                                from like_review
                                                                where PostID = <c:out value="${comment.PostID}"/>
                                                                and MemberID = ${User.getUserID()};
                                                            </sql:query>
                                                            <c:if test="${Like_count.rowCount > 0}">
                                                                <c:if test="${User_isLike.rowCount == 1}">
                                                                    <a class="like_click<c:out value="${comment.PostID}"/> like_disabled" href="javascript:Like_ReviewPost('${comment.PostID}');" style="margin-left: 8px; text-decoration: none;">
                                                                        <img id="Like<c:out value="${comment.PostID}"/>" src="template/IMG/like-button.png" style="max-width: 22px; max-height: 22px; margin-top: -5px;" title="Like" alt="#"> <%--(<p style="display: inline;" id="CountReply<c:out value="${comment.PostID}"/>">)</p>--%>
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${User_isLike.rowCount == 0}">
                                                                    <a class="like_click<c:out value="${comment.PostID}"/>" href="javascript:Like_ReviewPost('${comment.PostID}');" style="margin-left: 8px; text-decoration: none;">
                                                                        <img id="Like<c:out value="${comment.PostID}"/>" src="template/IMG/like-button.png" style="max-width: 22px; max-height: 22px; margin-top: -5px;" title="Like" alt="#"> <%--(<p style="display: inline;" id="CountReply<c:out value="${comment.PostID}"/>">)</p>--%>
                                                                    </a>
                                                                </c:if>
                                                                <c:forEach var="like_val" items="${Like_count.rows}">
                                                                    (<p id="Like_Count<c:out value="${comment.PostID}"/>" style="display: inline;" ><c:out value="${like_val.total_like}"></c:out></p>)
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${Like_count.rowCount == 0}">
                                                                <a class="like_click<c:out value="${comment.PostID}"/>" href="javascript:Like_ReviewPost('${comment.PostID}', '${User.getUserID()}');" style="margin-left: 8px; text-decoration: none;">
                                                                    <img id="Like<c:out value="${comment.PostID}"/>" src="template/IMG/like-button.png" style="max-width: 22px; max-height: 22px; margin-top: -3px;" title="Like" alt="#"> <%--(<p style="display: inline;" id="CountReply<c:out value="${comment.PostID}"/>">)</p>--%>
                                                                </a>
                                                                (<p id="Like_Count<c:out value="${comment.PostID}"/>" style="display: inline;" >0</p>)
                                                            </c:if>
                                                        </c:if>
                                                    </form>
                                                        <%--Reply form--%>
                                                    <form class="Create_Reply<c:out value="${comment.PostID}"/>">
                                                        <div id="Reply<c:out value="${comment.PostID}"/>" class="Reply_Section" style="margin-top: 6px;">
                                                            <textarea id="Review_Reply<c:out value="${comment.PostID}"/>" name="ReplyCmt" class="Reply-input" placeholder="Your Reply..."></textarea>
                                                            <input type="hidden" name="UserID" value="${User.getUserID()}" >
                                                            <input type="hidden" name="Name" id="Uid" value="${User.getFullname()}" >
                                                            <input type="hidden" name="PostID" value="${comment.PostID}">
                                                            <a href="javascript:Create_Reply('${comment.PostID}', '${User.getUserID()}')" class="Reply_Sent" ></a>

                                                                <%--users replies list--%>
                                                            <sql:query dataSource="${shop_db}" var="reply">
                                                                select ReplyComment, PostID, Fullname
                                                                from reply r
                                                                inner join members m
                                                                on r.MemberID = m.MemberID
                                                                where PostID = ?;
                                                                <sql:param value="${comment.PostID}"/>
                                                            </sql:query>
                                                            <ul id="User_Reply_List<c:out value="${comment.PostID}"/>">
                                                                <c:forEach items="${reply.rows}" var="replies">
                                                                    <li>
                                                                        <div>
                                                                            <h6><strong><c:out value="${replies.Fullname}"/></strong></h6>
                                                                            <p class="Reply_Comments"><c:out value="${replies.ReplyComment}"/></p>
                                                                        </div>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </form>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                            <c:if test="${Review.rowCount == 0}">
                                <p style="margin-top: 9px; opacity: 0.6;">There haven't been a review yet, you are the first user to write your review about ${Product_Data.getProductname()}.</p>
                            </c:if>
                        </section>
                    </section>
                </div>
            </div>
        </section>
    <!--Load jquery lib-->
    <script src="<c:url value='/template/script/JQuery/jquery-3.3.1.min.js'/>"></script>
    <!--Load bootstrap script-->
    <script src="<c:url value='/template/script/Bootstrap/popper.min.js'/>"></script>
    <script src="<c:url value='/template/script/Bootstrap/bootstrap.js'/>"></script>
    <%--Load SweetAlert Plugin--%>
    <script src="<c:url value='/template/script/Sweet/sweetalert2.min.js'/>"></script>
    <!--Load product page script-->
    <script src="<c:url value='/template/script/Product_Page.js'/>"></script>
    <%--Load Rating Plugin script--%>
    <script src="<c:url value='/template/script/JQuery/jquery.star-rating-svg.js'/>"></script>
    <script src="<c:url value='/template/script/Rating.js'/>"></script>
    </body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
    <link rel="StyleSheet" href="css/jquery-ui.css" type="text/css">
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
          media="screen" />
    <link rel="stylesheet" href="css/mouseover-inform.css" type="text/css"/>
    <link rel="stylesheet" href="css/my-style.css" type="text/css"/>
    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>MyPetStore</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="js/search-product.js" type="text/javascript"></script>

    <script src="js/TweenMax.js"></script>
</head>
    

<div id="cursor" class="cursor"></div>
<div id="cursor-follower" class="cursor-follower"></div>

<div id="Header">
                <div id="Logo">
                    <div id="LogoContent">
                        <a href="main"><img src="images/logo-topbar.gif" /></a>
                    </div>
                </div>
            <div id="Menu">
                <div id="MenuContent" >
                    <a href="viewCart">
                        <img align="middle" name="img_cart" src="images/cart.gif" />
                    </a>

                    <img align="middle" src="images/separator.gif" />
                     <c:if test="${sessionScope.account == null}">
                        <a href="signOnForm">Sign In</a>
                    </c:if>
                     <c:if test="${sessionScope.account != null}">
                        <a href="signOff">Sign Out</a>
                    </c:if>
                     <c:if test="${sessionScope.account != null}">
                        <img align="middle" src="images/separator.gif" />
                        <a href="editAccount">My Account</a>
                    </c:if>
                    <img align="middle" src="images/separator.gif" />
                    <a href="../help.html">?</a>
                </div>
            </div>
                <div id="Search">
                    <div id="SearchContent">
                        <div class="ui-widget">
                            <form action="searchProduct" method="post">
                                <input type="text" name="keyword" size="14" id="searchText" class="search"/> <input type="submit"
                                                                                                                    name="searchProducts" value="Search" />
                            </form>
                        </div>
                    </div>
                </div>
    </div>
    <div id="QuickLinks">
        <a href="viewCategory?categoryId=FISH">
            <img src="images/sm_fish.gif" /></a> <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=DOGS">
            <img src="images/sm_dogs.gif" /></a> <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=REPTILES">
            <img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=CATS">
            <img src="images/sm_cats.gif" /></a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=BIRDS">
            <img src="images/sm_birds.gif" />
        </a>
    </div>
</div>
<div id="Content">
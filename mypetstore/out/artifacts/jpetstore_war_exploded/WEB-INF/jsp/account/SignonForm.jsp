<%@ include file="../common/IncludeTop.jsp"%>
<div id="Content">
		<ul class="messages">
			<li>${sessionScope.messageSignOn}</li>
		</ul>

<div id="Catalog">
	<form action="signOn" method="post" id="register">
		<p>Please enter your username and password.</p>
		<p>
			Username:<input type="text" name="username" id="username" /><br />
			<div class = "usernameMsg"></div>
		    Password:<input type="password" name="password" id="password" /><br />
			VerificationCode:<input type="text" name="vCode" size="5" maxlength="4"/>
			<a href="signOn"><img border="0" src="verificationCode" name="checkcode"></a>
		</p>
		<input type="submit" name="signon" value="Login" />
	</form>
		Need a user name and password?
	    <a href="newAccountForm">Register Now!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>


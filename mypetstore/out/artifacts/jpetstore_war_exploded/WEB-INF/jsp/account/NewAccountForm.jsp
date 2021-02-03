<%@ include file="../common/IncludeTop.jsp"%>
${sessionScope.messageAccount}

<div id="Catalog">
	<form action="newAccount" method="post" id="signupForm">
		<h3>User Information</h3>

		<table>
			<tr>
				<td>User ID:</td>
				<td><input type="text" name="username" id="username" placeholder="Your ID"/>
					<div id="namemsg"></div>
				</td>

			</tr>
			<tr>
				<td >New password:</td>
				<td><input type="text" name="password" id="password" />
					<div id="pwdmsg"></div>
				</td>

			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="text" name="repeatedPassword" id="repeat" />
					<div id="repeatmsg"></div>
				</td>
			</tr>
			<tr>
				<td>VerificationCode:</td>
				<td>
					<input type="text" name="vCode" size="5" maxlength="4"/>
					<a href="newAccount"><img border="0" src="verificationCode" name="checkcode"></a>
				</td>
			</tr>
		</table>

		<%@ include file="IncludeAccountFields.jsp"%>
		<input type="submit" name="newAccount" value="Save Account Information" />
	</form>
	<script src="js/new-account-validation.js"></script>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
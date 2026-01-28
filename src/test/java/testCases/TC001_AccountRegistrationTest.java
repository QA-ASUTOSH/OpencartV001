package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test
	public void verify_account_registration() {

		logger.info("\"***** TC001_AccountRegistrationTest started *****\"");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");

			hp.clickRegister();
			logger.info("Clicked on Register");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details");

			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString().toLowerCase() + "@gmail.com"); // randomly generated the email
			regpage.setTelephone(randomNumber());

			String password = randomAlphaNumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message");
			String confmsg = regpage.getConfirmationMsg();
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Exception occurred during account registration test");
				logger.debug("Debug logs");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("***** TC001_AccountRegistrationTest finished *****");

	}

}

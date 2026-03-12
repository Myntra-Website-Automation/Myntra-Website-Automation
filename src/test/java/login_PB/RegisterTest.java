package login_PB;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;

public class RegisterTest extends BaseTest{
	private Register register;
	
	@BeforeClass
	public void initPage() {
		register = new Register(DriverFactory.getDriver());
	}

  @Test
  public void registerCandidateTest() {
    register.registerCandidate();
  }
}

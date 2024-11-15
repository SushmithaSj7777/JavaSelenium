package org.Tests;

import org.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {




    @Test(groups = {"ErrorValidation"})
    public void testCase2()
    {
        login.loginApplication("sushmithasj777@gmail.com", "9164078841Vj$");
        String errormessage= login.incorrectemailandpwdvalidation();
        Assert.assertEquals(errormessage,"Incorrect email or password.");
    }


}

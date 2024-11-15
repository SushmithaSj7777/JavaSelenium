Feature: Submit order in e comm website

Background: the e comm sit is launched

  Scenario Outline:Positive test of submitting order
    Given login with user <name> and <password>
    When add the <productname> to cart
    And Checkout the <productname> and submit order
    Then Verify the confirmation message "Thankyou for the order."



    Examples:
      | username                      |password       | productname
      |sushmithasj7777@gmail.com  | 9164078841Vj$ | Zara COAT 3



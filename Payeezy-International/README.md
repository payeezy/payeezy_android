# Payeezy International Supported Features

* Tokenised Transactions
* AVS,CVV, SoftDescriptor and 3DS Card Transactions
* Dynamic Currency Conversion and Dynamic Pricing
* TimeOutReversal
* GermanDirectDebit

* Tokenised Transactions- Token is generated for the card and the transactions are made using the token

* AVS,CVV, SoftDescriptor and 3DS Card Transactions- This feature is used to make credit card transactions [Non-Tokenised]

* Dynamic Currency Conversion and Dynamic Pricing-
There are two types of conversion possible "Merchant Rate" and "Card Rate".
Merchant Rate - Merchant sends the Amount and the Currency Code to which the exchange rate has to be applied
Card Rate - Merchant sends the Amount and the First 6 Digits of the credit card number(BIN). 
Based on BIN the Currency Code is figured out based on which the exchange rate is applied

The customer is provided with the option of paying in their own currency showing the amount, exchange rate, the source of the exchange rate and commission data.
The consumer then opts for which currency to pay in and the transaction continues from there in the chosen currency.

* TimeOutReversal- Any transaction can be reversed except for VOID.
We will have to send an additional attribute "reversal_id" in the request payload. 

* GermanDirectDebit- This method is applicable only to merchants domiciled in Germany.

For more documentation please refer to https://developer.payeezy.com/




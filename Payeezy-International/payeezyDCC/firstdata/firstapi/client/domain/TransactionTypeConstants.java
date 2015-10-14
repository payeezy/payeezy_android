package com.firstdata.firstapi.client.domain;


public interface TransactionTypeConstants {
    final String GGE4_POST = "00";
    final String GGE4_PREAUTH = "01";
    final String GGE4_V11_PREAUTH_COMPLETE = "02";
    final String GGE4_V11_REFUND = "04";
    
    final String GGE4_V11_FORCEDPOST = "03";
    final String GGE4_V11_PREAUTH = "05";
    final String GGE4_V11_PAYPAL = "07";
    
    final String GGE4_V11_VOID = "13";
    
    final String GGE4_V12_PREAUTH_COMPLETE = "32";
    final String GGE4_V12_VOID = "33";
    final String GGE4_V12_REFUND = "34";
    
    final String GGE4_V12_CASHOUT = "83";
    final String GGE4_V12_ACTIVATION = "85";
    final String GGE4_V12_DEACTIVATION = "89";
    final String GGE4_V12_BALANCEENQUIRY = "86";
    final String GGE4_V12_RELOAD = "88";
    
    /*
    *	83 = CashOut (ValueLink, v9 or higher end point only)
    *	85 = Activation (ValueLink, v9 or higher end point only)
    *	86 = Balance Inquiry (ValueLink, v9 or higher end point only)
    *	88 = Reload (ValueLink, v9 or higher end point only)
    *	89 = Deactivation (ValueLink, v9 or higher end point only
    *
    **	00 = Purchase
*	01 = Pre-Authorization
*	02 = Pre-Authorization Completion
*	03 = Forced Post
*	04 = Refund
*	05 = Pre-Authorization Only
*	07 = PayPal Order
*	13 = Void
*	32 = Tagged Pre-Authorization Completion
*	33 = Tagged Void
*	34 = Tagged Refund
*	83 = CashOut (ValueLink, v9 or higher end point only)
*	85 = Activation (ValueLink, v9 or higher end point only)
*	86 = Balance Inquiry (ValueLink, v9 or higher end point only)
*	88 = Reload (ValueLink, v9 or higher end point only)
*	89 = Deactivation (ValueLink, v9 or higher end point only)


    **/


    
    
    String getValue() ;
}

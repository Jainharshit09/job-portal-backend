package com.jobportal.Utility;

public class Data {
    public static String getMessageBody(String otp,String name) {
        return "<html><head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f7f7f7; }"
                + ".container { width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); text-align: center; }"
                + ".header { background-color: #4CAF50; padding: 15px; color: #ffffff; font-size: 24px; font-weight: bold; border-top-left-radius: 8px; border-top-right-radius: 8px; }"
                + ".content { padding: 20px; font-size: 16px; color: #333333; }"
                + ".otp { font-size: 32px; font-weight: bold; color: #4CAF50; margin: 20px 0; }"
                + ".instructions { font-size: 14px; color: #666666; margin-top: 10px; }"
                + ".footer { font-size: 12px; color: #999999; margin-top: 20px; padding: 10px; border-top: 1px solid #dddddd; }"
                + "</style>"
                + "</head><body>"
                + "<div class='container'>"
                + "<div class='header'>Your OTP Code</div>"
                + "<div class='content'>"
                + "Hello "+name
                + "<p>We have received a request to verify your email address. Your OTP code is:</p>"
                + "<div class='otp'>" + otp + "</div>"
                + "<p class='instructions'>This OTP code is valid for 5 minutes. If you did not request this, please ignore this email.</p>"
                + "<p>Thank you for using our service!</p>"
                + "</div>"
                + "<div class='footer'>"
                + "&copy; 2025  Job Hook . All rights reserved."
                + "</div>"
                + "</div>"
                + "</body></html>";
    }
}

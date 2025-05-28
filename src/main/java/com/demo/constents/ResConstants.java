package com.demo.constents;

import java.io.File;

public class ResConstants {

    public  static  final  String SOMETHING_WENT_WRONG = "Something Went Wrong";


    public  static final String INVALID_DATA = "Invalid Data";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";

    public static final String STORE_LOCATION = "./com.demo/bills/"; // ⚠️ Avoid

    static {
        File dir = new File(STORE_LOCATION);
        if (!dir.exists()) {
            dir.mkdirs(); // create the directory if it doesn't exist
        }
    }
//    private static final String STORE_LOCATION = "./videos/";

}

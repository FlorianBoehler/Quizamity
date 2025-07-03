package com.quizamity;
import com.quizamity.security.PasswordService;


// Or create a simple main method for testing
public class PasswordTester {
    public static void main(String[] args) {
        PasswordService ps = new PasswordService();

        // Test password hashing
        String password = "adminpass";
        String hash = ps.hash(password);
        System.out.println("Password: " + password);
        System.out.println("Hash: " + hash);
        System.out.println("Verification: " + ps.verify(password, hash));

        // Test with your database hash
        String dbHash = "$2a$10$uY0xKxgT/2e2HuHTF7zYqe3.zWYsQd9b8Zn0ygQma2UpqLxSmQaiu";
        System.out.println("DB Hash verification: " + ps.verify(password, dbHash));
    }
}
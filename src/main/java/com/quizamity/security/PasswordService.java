package com.quizamity.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.*;
import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;
import io.jsonwebtoken.Jwts;

@ApplicationScoped
public class PasswordService {

    private static final Logger LOGGER = Logger.getLogger(PasswordService.class.getName());
    // private static String SECRET_KEY="oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    private static String SECRET_KEY="$2a$10$uY0xKxgT/2e2HuHTF7zYqe3.zWYsQd9b8Zn0ygQma2UpqLxSmQaiu";
    public String hash(String plainPassword) {
        LOGGER.info("Hashing password of length: " + (plainPassword != null ? plainPassword.length() : "null"));
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        LOGGER.info("Hash generated: " + hashed);
        return hashed;
    }

    public boolean verify(String plainPassword, String hashedPassword) {
        LOGGER.info("=== Password Verification Debug ===");
        LOGGER.info("Plain password: '" + plainPassword + "'");
        LOGGER.info("Plain password length: " + (plainPassword != null ? plainPassword.length() : "null"));
        LOGGER.info("Hashed password: '" + hashedPassword + "'");
        LOGGER.info("Hashed password length: " + (hashedPassword != null ? hashedPassword.length() : "null"));

        if (plainPassword == null) {
            LOGGER.severe("ERROR: Plain password is null!");
            return false;
        }

        if (hashedPassword == null) {
            LOGGER.severe("ERROR: Hashed password is null!");
            return false;
        }

        // Check if the hash looks like a BCrypt hash
        if (!hashedPassword.startsWith("$2")) {
            LOGGER.warning("WARNING: Hashed password doesn't look like a BCrypt hash!");
            LOGGER.warning("Expected format: $2a$... or $2b$...");
            LOGGER.warning("Actual format: " + hashedPassword.substring(0, Math.min(10, hashedPassword.length())));
        }

        try {
            boolean result = BCrypt.checkpw(plainPassword, hashedPassword);
            LOGGER.info("BCrypt verification result: " + result);

            // Additional debug: Try to manually verify what the hash should be
            if (!result) {
                LOGGER.info("=== Manual Hash Check ===");
                String testHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
                LOGGER.info("New hash for same password: " + testHash);

                // Try some common variations
                LOGGER.info("Testing common password variations:");
                String[] variations = {
                        plainPassword.trim(),
                        plainPassword.toLowerCase(),
                        plainPassword.toUpperCase()
                };

                for (String variation : variations) {
                    if (!variation.equals(plainPassword)) {
                        boolean varResult = BCrypt.checkpw(variation, hashedPassword);
                        LOGGER.info("Variation '" + variation + "': " + varResult);
                        if (varResult) {
                            LOGGER.warning("PASSWORD MISMATCH: Database expects '" + variation + "' but got '" + plainPassword + "'");
                        }
                    }
                }
            }

            return result;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception during password verification", e);
            return false;
        }
    }

    public String createJWT(String id, String issuer, String subject, long ttlMillis) {
        LOGGER.info("Creating JWT - id: " + id + ", issuer: " + issuer + ", subject: " + subject);

        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            JwtBuilder builder = Jwts.builder().setId(id)
                    .setIssuedAt(now)
                    .setSubject(subject)
                    .setIssuer(issuer)
                    .signWith(signatureAlgorithm, signingKey);

            //if it has been specified, let's add the expiration
            if (ttlMillis > 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }

            //Builds the JWT and serializes it to a compact, URL-safe string
            String jwt = builder.compact();
            LOGGER.info("JWT created successfully, length: " + jwt.length());
            return jwt;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating JWT", e);
            return null;
        }
    }
}
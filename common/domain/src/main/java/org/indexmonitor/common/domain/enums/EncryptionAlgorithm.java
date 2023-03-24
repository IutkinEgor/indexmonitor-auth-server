package org.indexmonitor.common.domain.enums;

public enum EncryptionAlgorithm {
    BCRYPT("bcrypt"),
    LDAP("ldap"),
    MD4("MD4"),
    MD5("MD5"),
    NOOP("noop"),
    PBKDF2("pbkdf2"),
    PBKDF2_SPRING_SECURITY_5_8("pbkdf2@SpringSecurity_v5_8"),
    SCRYPT("scrypt"),
    SCRYPT_SPRING_SECURITY_5_8("scrypt@SpringSecurity_v5_8"),
    SHA_1("SHA-1"),
    SHA_256("SHA-256"),
    SHA256("sha256"),
    ARGON2("argon2"),
    ARGON2_SPRING_SECURITY_5_8("argon2@SpringSecurity_v5_8");
    private final String algorithm;

    EncryptionAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getAlgorithmBrackets() {
        return "{" + algorithm + "}";
    }

    public static EncryptionAlgorithm getDefaultAlgorithm(){return BCRYPT;}

    public static String getDefaultAlgorithmBrackets() { return "{" + BCRYPT.algorithm + "}"; }

}


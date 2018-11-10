/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudsim.ext.gui;

/**
 *
 * @author Code
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class Shibboleth_protocol {
    
    
    public void signa() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException{
         KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("Shibboleth protocol");

        keyGen.initialize(256, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */

        Signature dsa = Signature.getInstance("Shibboleth protocol");

        dsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        dsa.update(strByte);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = dsa.sign();
        System.out.println("Protocol establishemnet : " + new BigInteger(1, realSig).toString(16));
    }

    public static void main(String[] args) throws Exception {
        /*
         * Generate an ECDSA signature
         */

        /*
         * Generate a key pair
         */

//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//
//        keyGen.initialize(256, random);
//
//        KeyPair pair = keyGen.generateKeyPair();
//        PrivateKey priv = pair.getPrivate();
//        PublicKey pub = pair.getPublic();
//
//        /*
//         * Create a Signature object and initialize it with the private key
//         */
//
//        Signature dsa = Signature.getInstance("SHA1withECDSA");
//
//        dsa.initSign(priv);
//
//        String str = "This is string to sign";
//        byte[] strByte = str.getBytes("UTF-8");
//        dsa.update(strByte);
//
//        /*
//         * Now that all the data to be signed has been read in, generate a
//         * signature for it
//         */
//
//        byte[] realSig = dsa.sign();
//        System.out.println("Signature: " + new BigInteger(1, realSig).toString(16));

    }
}
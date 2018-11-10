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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ecc_algo{
     public void signa() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException, IOException{
          long start = Calendar.getInstance().getTimeInMillis();
        System.err.println("Start signature=="+start);
         KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        keyGen.initialize(256, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */

        Signature dsa = Signature.getInstance("SHA1withECDSA");

        dsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        dsa.update(strByte);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = dsa.sign();
         long encrpt = Calendar.getInstance().getTimeInMillis()- start;
    
      long start1 = Calendar.getInstance().getTimeInMillis();
        System.out.println("protocol authentication: " + new BigInteger(1, realSig).toString(16));
         JOptionPane.showMessageDialog(null, "Signature is:"+ new BigInteger(1, realSig).toString(16));
         
         long decrpt = Calendar.getInstance().getTimeInMillis() - start1;
    System.err.println("Encrypt milliseconds"+decrpt);
    
//    JOptionPane.showMessageDialog(null, "Encryption key generated at=>"+encrpt+"millisecods");
//    JOptionPane.showMessageDialog(null, "Decrption generated at =>"+decrpt+"millisecods");
//    
     BufferedWriter bw = new BufferedWriter(new FileWriter("EDTime.txt", true));
            bw.write(encrpt + "\n"+decrpt+"\n");
            bw.close();
         
         
    }
    public void result() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException, IOException{
        long start = Calendar.getInstance().getTimeInMillis();
        System.err.println("Start signature=="+start);
       // Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
    ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

    keyGen.initialize(ecSpec, new SecureRandom());

    KeyPair keyPair = keyGen.generateKeyPair();
     
    Signature signature = Signature.getInstance("ECDSA", "BC");
    signature.initSign(keyPair.getPrivate(), new SecureRandom());

    byte[] message = "abc".getBytes();
     
    signature.update(message);

    byte[] sigBytes = signature.sign();
    System.out.println("Signature is=>"+sigBytes);
    
    long encrpt = Calendar.getInstance().getTimeInMillis()- start;
    
      long start1 = Calendar.getInstance().getTimeInMillis();
    System.err.println("Encrypt milliseconds"+encrpt);
    
     JOptionPane.showMessageDialog(null, "->"+sigBytes);
    signature.initVerify(keyPair.getPublic());
    signature.update(message);
    
    long decrpt = Calendar.getInstance().getTimeInMillis() - start1;
    System.err.println("Encrypt milliseconds"+decrpt);
    
    
//    JOptionPane.showMessageDialog(null, "Encryption key generated at=>"+encrpt+"millisecods");
//    JOptionPane.showMessageDialog(null, "Decrption generated at =>"+decrpt+"millisecods");
    
     BufferedWriter bw = new BufferedWriter(new FileWriter("EDTime.txt", true));
            bw.write(encrpt + "\n"+decrpt+"\n");
            bw.close();
   /// System.out.println(signature.verify(sigBytes));
    
    
    }
//  public static void main(String[] args) throws Exception {
//    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("Protocol", "BC");
//    ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
//
//    keyGen.initialize(ecSpec, new SecureRandom());
//
//    KeyPair keyPair = keyGen.generateKeyPair();
//     
//    Signature signature = Signature.getInstance("Protocol", "BC");
//    signature.initSign(keyPair.getPrivate(), new SecureRandom());
//
//    byte[] message = "abc".getBytes();
//     
//    signature.update(message);
//
//    byte[] sigBytes = signature.sign();
//    System.out.println("dfsfsdfs==============>"+sigBytes);
//    JOptionPane.showMessageDialog(null, "Signature is:"+sigBytes);
//    signature.initVerify(keyPair.getPublic());
//    signature.update(message);
//    System.out.println(signature.verify(sigBytes));
//  }
}
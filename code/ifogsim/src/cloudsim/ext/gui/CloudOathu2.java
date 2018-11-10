/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cloud
 */
package cloudsim.ext.gui;
import java.io.File;
import java.util.ArrayList;
import sendspaceapi.Data.AnonymousFileEntry;
import sendspaceapi.Upload.Anonymous.AnonymousUpload;
import sendspaceapi.Data.Progress;
public class CloudOathu2 {

    private static String key = "HLBM608461";

    public static void main(String[] args) {
        try {
            AnonymousUpload au = new AnonymousUpload(key);
            System.out.println("uploading file(s)...");
            AnonymousFileEntry e = au.uploadFile(new File("D:\\enrolment_form.pdf"));
            print(e);
        } catch (Exception ex) {
        }
    }

    public static File[] createFileList(String[] files) {
        File[] f = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            f[i] = new File(files[i]);
        }
        return f;
    }

    public static void print(AnonymousFileEntry e) {
        System.out.println("File Download URL: " + e.downloadURL());
        System.out.println("File Delete URL: " + e.deleteURL());
        System.out.println("File ID: " + e.fileID());
        System.out.println("File Name: " + e.fileName());

    }

    public static ArrayList publicCloud(String filepath) {
        ArrayList al = new ArrayList();
        try {
            AnonymousUpload au = new AnonymousUpload(key);
            System.out.println("uploading file(s)...");
            AnonymousFileEntry e = au.uploadFile(new File(filepath));
            al.add(e.downloadURL());
            al.add(e.deleteURL());
            al.add(e.fileName());
            al.add(e.fileID());
            print(e);
            Progress p;

        } catch (Exception ex) {
        }
        return al;
    }
}

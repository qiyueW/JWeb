package system.base.Sc;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IK
 */
public class Sc {

    private int commonL = 0;
    protected final List<Class> cs = new ArrayList<>();

    protected Sc() {
        this.sc();
    }

    private void sc() {
        try {
            Enumeration<URL> rs = Thread.currentThread().getContextClassLoader().getResources("");
            int i = 0;
            while (rs.hasMoreElements()) {

                URL u = rs.nextElement();
                if (null != u) {
                    String protocol = u.getProtocol();
                    if (protocol.equalsIgnoreCase("file")) {
                        String path = URLDecoder.decode(u.getFile(), "UTF-8");
//                        path = path.startsWith("/") ? path.substring(1) : path;
                        path = mypath(path);
                        commonL = path.length();
//                        System.out.println(path + "-------------------------" + (i++));
                        doAddFile(new File(path));
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Sc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String mypath(String str) {
        return -1 == str.indexOf(":") ? str : (str.startsWith("/") ? str.substring(1) : str);
    }

    private void addClass(File f) {
        try {
//            System.out.println("file:" + f.getAbsolutePath() + "//" + f.getName());
            cs.add(Thread.currentThread().getContextClassLoader().loadClass(f.getAbsolutePath().substring(commonL).replace("\\", ".").replace("/", ".").replaceFirst(".class", "")));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doAddFile(File f) {
//        System.out.println("file " + f.isDirectory() + "//" + f.getAbsolutePath());
        if (f.isDirectory()) {
            File[] fs = f.listFiles();
            for (File obj : fs) {
                doAddFile(obj);
            }
        } else if (f.isFile() && f.getAbsolutePath().endsWith(".class")) {
            addClass(f);
        }
    }
}

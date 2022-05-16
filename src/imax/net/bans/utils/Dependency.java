package imax.net.bans.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Dependency {
    public Dependency(String link, File to) throws MalformedURLException, IOException {
        if (to.exists() || to.mkdir()) {
            URL url = new URL(link);
            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            InputStream in = urlc.getInputStream();
            FileOutputStream f = new FileOutputStream(new File(to, url.getFile().split("/")[url.getFile().split("/").length - 1]));
            byte[] b = new byte[1024];

            int i;
            while((i = in.read(b)) > 0) {
                f.write(b, 0, i);
            }

            f.close();
            in.close();
        }
    }
}

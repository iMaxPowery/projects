package imax.net.upgrade.faction;

import java.util.HashMap;
import java.util.Map;

public class Methods {

    public static HashMap<String, Manager> fac = new HashMap<>();

    public Manager getFacManager() {
        Manager manager = null;
        for (Map.Entry<String, Manager> m : fac.entrySet())
            manager = m.getValue();
        return manager;
    }

    public boolean hasFac() {
        return (fac.size() >= 1);
    }

    public String getFacTag() {
        String tag = "nenhuma";
        for (Map.Entry<String, Manager> m : fac.entrySet())
            tag = m.getValue().getTagFac();
        return tag;
    }

    public void removeFac() {
        fac.clear();
    }
}

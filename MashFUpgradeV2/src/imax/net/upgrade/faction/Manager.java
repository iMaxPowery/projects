package imax.net.upgrade.faction;

import com.massivecraft.factions.entity.Faction;

public class Manager extends Timer {

    static double timeLeft, timeStart;
    String tagFac;

    public Manager(Faction fac) {
        super(System.currentTimeMillis() + 60 * 5 * 1000, System.currentTimeMillis(), fac.getTag());
        tagFac = fac.getTag();
        timeStart = System.currentTimeMillis();

        Methods.fac.put(tagFac, this);
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public double getTimeStart() {
        return timeStart;
    }

    public String getTagFac() {
        return tagFac;
    }
}

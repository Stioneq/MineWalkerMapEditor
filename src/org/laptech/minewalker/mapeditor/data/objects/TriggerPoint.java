package org.laptech.minewalker.mapeditor.data.objects;

/**
 * Trigger point , used as start point or as end point
 * @author rlapin
 */
public class TriggerPoint extends GameObject {
    private TriggerType triggerType;

    public TriggerPoint(double x, double y, double width, double height, TriggerType type) {
        super(x, y, width, height);
        triggerType = type;
    }

    @Override
    public String getType() {
        return "triggerpoint";
    }

    public enum TriggerType {
        SPAWN, FINISH;
    }

    public TriggerType getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(TriggerType triggerType) {
        this.triggerType = triggerType;
    }
}

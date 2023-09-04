package Backend.Utils;

import Backend.Callbacks.VisibilityCallBack;

public class TrapResource extends Resource {
    public int visibleTime;
    public int invisbleTime;
    public boolean visible;
    private int ticksCount;
    char tilechar;
    public VisibilityCallBack visibilityCallBack;

    public TrapResource(int healthCapacity, int visibleTime, int invisibleTime,char tile){
        super(healthCapacity,healthCapacity);
        this.visibleTime = visibleTime;
        this.invisbleTime = invisibleTime;
        visible = true;
        ticksCount = 0;
        tilechar =tile;
        //this.visibilityCallBack = v;

    }
    public void visibilityOnTick()
    {
        visible = ticksCount<visibleTime;
        if(visible)
            visibilityCallBack.call(tilechar);
        else
            visibilityCallBack.call('.');
        if(ticksCount==(visibleTime+invisbleTime))
            ticksCount=0;
        else
            ticksCount = ticksCount+1;
    }

    public void setVisibilityCallBack(VisibilityCallBack v) { this.visibilityCallBack=v; }

}

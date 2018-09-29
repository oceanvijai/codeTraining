public abstract class IEmployee {
    private ICall assignedCall;
    abstract public LevelEnum getLevel();
    public void setCAll(ICall call){
        this.assignedCall = Call;
    }
    abstract public void engage();
    public void disconnect(){
        assignedCall.disconnect();
    }
    abstract public void esculate();
}
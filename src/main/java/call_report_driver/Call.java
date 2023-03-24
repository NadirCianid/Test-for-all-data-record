package call_report_driver;

public class Call {
    //класс звонка
    private String  callType;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;

    Call(String  callType, String startTime, String endTime) {
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
        calculateDurationCost();
    }

    private void calculateDurationCost() {
        //TODO: написать логику подсчета длительности и стоимости звонка
    }

    @Override
    public String toString() {
        //TODO: написать правильный вывод информации о звонке
        return "[" + getCallType() + " | " + getStartTime() + " | " + getEndTime() + " | " + getDuration() + " | " + getCost() + "]";
    }

    public String getCallType() {
        return callType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }
}

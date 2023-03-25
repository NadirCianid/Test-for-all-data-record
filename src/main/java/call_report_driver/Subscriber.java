package call_report_driver;

import java.util.Map;
import java.util.TreeMap;

public class Subscriber {
    //класс для хранения информации об абоненте

    private String subscriberNumber;
    private TariffType  tariffType;
    private TreeMap<String, Call> callHistory = new TreeMap<>(); // ключ - startTime
    private double totalCoast;

    Subscriber(String subscriberNumber, String tariffType, String  callType, String startTime, String endTime) {
        this.subscriberNumber = subscriberNumber;
        switch (tariffType){
            case "06":
                this.tariffType = TariffType.UNLIMITED;
                //this.tariffType.
                break;
            case "03":
                this.tariffType = TariffType.MINUTE_BY_MINUTE;
                break;
            case "11":
                this.tariffType = TariffType.REGULAR;
                break;
        }
        newCallRecord(callType, startTime, endTime, this.tariffType);
    }

    public void newCallRecord(String  callType, String startTime, String endTime, TariffType tariffType) {
        callHistory.put(startTime, new Call(callType, startTime, endTime, tariffType));
        totalCoast += getCallHistory().lastEntry().getValue().getCost();
    }

    @Override
    public String toString() { //TODO: написать логику создания нового отчета
       String returnString = "";
        for (Map.Entry<String, Call> entry: callHistory.entrySet()) {
            returnString += entry.getValue() + "\n";
       }
        returnString += "\n" + totalCoast;
        return returnString;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public TreeMap<String, Call> getCallHistory() {
        return callHistory;
    }

    public double getTotalCoast() {
        return totalCoast;
    }
}

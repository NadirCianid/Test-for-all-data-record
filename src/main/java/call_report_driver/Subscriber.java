package call_report_driver;

import java.util.Map;
import java.util.TreeMap;

public class Subscriber {
    //класс для хранения информации об абоненте

    private String subscriberNumber;
    private String  tariffType;
    private TreeMap<String, Call> callHistory = new TreeMap<>(); // ключ - startTime
    private double totalCoast;

    Subscriber(String subscriberNumber, String tariffType, String  callType, String startTime, String endTime) {
        this.subscriberNumber = subscriberNumber;
        this.tariffType = tariffType;
        newCallRecord(callType, startTime, endTime);
    }

    public void newCallRecord(String  callType, String startTime, String endTime) {
        System.out.print("adding new call record, callHistory : "  );
        callHistory.put(startTime, new Call(callType, startTime, endTime));
        System.out.println(callHistory.size());
        //totalCoast += getCallHistory().lastEntry().getValue().getCost();
    }

    @Override
    public String toString() {
       String returnString = "";
        for (Map.Entry<String, Call> entry: callHistory.entrySet()) {
            returnString += entry.getValue() + ";\n";
       }
        return returnString;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public String getTariffType() {
        return tariffType;
    }

    public TreeMap<String, Call> getCallHistory() {
        return callHistory;
    }

    public double getTotalCoast() {
        return totalCoast;
    }
}

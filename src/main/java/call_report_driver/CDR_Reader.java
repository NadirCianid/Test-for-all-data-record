package call_report_driver;

public class CDR_Reader {
    //класс для обработки информации из cdr файла
    String subscriberNumber;
    String startTime;
    String endTime;
    String tariffType;
    String callType;
    Subscriber currentSubscriber;

    public void processCdrRecord(String sdrRecord) {
        callType = sdrRecord.substring(0,2);
        subscriberNumber = sdrRecord.substring(4,15);
        startTime = sdrRecord.substring(17,31);
        endTime = sdrRecord.substring(33,47);
        tariffType = sdrRecord.substring(49,51);

        if(Main.subscribersBase.containsKey(subscriberNumber)) {
            currentSubscriber =  Main.subscribersBase.get(subscriberNumber);
           currentSubscriber.newCallRecord(callType, startTime, endTime, currentSubscriber.getTariffType());
        } else {
            Subscriber newSubscriber = new Subscriber(subscriberNumber, tariffType, callType, startTime, endTime);
            Main.subscribersBase.put(subscriberNumber, newSubscriber);
        }
    }
}

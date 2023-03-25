package call_report_driver;

import java.util.Map;
import java.util.TreeMap;

public class Subscriber {
    //класс для хранения информации об абоненте
    private String phoneNumber;
    private TariffType  tariffType;
    private  int bonusPeriod;
    private TreeMap<String, Call> callHistory = new TreeMap<>(); // ключ - startTime
    private double totalCost;

    Subscriber(String phoneNumber, String tariffType, String  callType, String startTime, String endTime) {
        this.phoneNumber = phoneNumber;
        switch (tariffType){
            case "06":
                this.tariffType =  TariffType.UNLIMITED;
                totalCost += 100;
                break;
            case "03":
                this.tariffType = TariffType.MINUTE_BY_MINUTE;
                break;
            case "11":
                this.tariffType = TariffType.REGULAR;
                break;
        }
        bonusPeriod = this.tariffType.getBONUS_PERIOD();

        newCallRecord(callType, startTime, endTime);
    }

    public void newCallRecord(String  callType, String startTime, String endTime) {
        callHistory.put(startTime, new Call(callType, startTime, endTime, this));
    }

    public void calculateCallsCost(){
        for (Map.Entry<String, Call> entry: callHistory.entrySet()) {
            try {
                totalCost += entry.getValue().calculateDurationCost(tariffType);
            } catch (Exception e) {
                System.out.println("отрицательная длительность разговора");
            }
        }
    }

    @Override
    public String toString() {
       String returnString = "Tariff index: " + tariffType.getTARIFF_CODE() + "\n" +
               "------------------------------------------------------------------------------\n" +
               "Report for phone number " + phoneNumber + ":\n" +
               "------------------------------------------------------------------------------\n" +
               "| Call Type |   Start Time        |     End Time        | Duration |   Cost  |\n" +
               "------------------------------------------------------------------------------\n";
       for (Map.Entry<String, Call> entry: callHistory.entrySet()) {
            returnString += entry.getValue() + "\n";
       }
        returnString += "------------------------------------------------------------------------------\n" +
                "|                                           Total Cost: |" + formattedTotalCost(totalCost) + "|\n" +
                "------------------------------------------------------------------------------\n";
        return returnString;
    }

    private String formattedTotalCost(double totalCost) {
        String formattedTotalCoast = "";
        String totalCoastString = String.valueOf(totalCost);
        switch (totalCoastString.length()){
            case 3:
                formattedTotalCoast = "         " + totalCoastString + " rubles ";
                break;
            case 4:
                formattedTotalCoast = "        " + totalCoastString + " rubles ";
                break;
            case 5:
                formattedTotalCoast = "       " + totalCoastString + " rubles ";
                break;
            case 6:
                formattedTotalCoast = "      " + totalCoastString + " rubles ";
                break;
            case 7:
                formattedTotalCoast = "     " + totalCoastString + " rubles ";
                break;
        }
        return formattedTotalCoast;
    }

    public int getBonusPeriod() {
        return bonusPeriod;
    }

    public void useBonusPeriod(int time, boolean setZero) {
        bonusPeriod -= time;
        if(setZero) {
            bonusPeriod = 0;
        }
    }
}

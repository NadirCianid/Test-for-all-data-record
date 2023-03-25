package call_report_driver;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Call {
    //класс звонка
    private String  callType;
    private StringBuilder startTime;
    private int startTimeSeconds;
    private StringBuilder endTime;
    private int endTimeSeconds;
    private int duration;
    private double cost;

    Call(String  callType, String startTime, String endTime, TariffType tariffType) {
        this.callType = callType;
        this.startTime = new StringBuilder(startTime);
        this.endTime = new StringBuilder(endTime);
        try {
            calculateDurationCost(tariffType);
        } catch (Exception e) {
            System.out.println("отрицательная длительность разговора");
        }
    }

    private void calculateDurationCost(TariffType tariffType) throws Exception {
        duration = processTime(endTime) - processTime(startTime);
        if (duration < 0) {
            throw new Exception();
        }

        int durationInMinutes = duration / 60;

        if(tariffType == TariffType.REGULAR && callType.equals("02")) {
            cost = 0;
        } else {
            double remainingBonusPeriod = tariffType.useBonusPeriod(durationInMinutes);

            if(remainingBonusPeriod > 0) {
                cost = durationInMinutes * tariffType.getBONUS_PERIOD_RATE();

            } else if(remainingBonusPeriod < 0) { // бонусный период закончился, а значит метод useBonusPeriod вернул
                remainingBonusPeriod = -remainingBonusPeriod; // остаток длительности звонка вне бонусного периода со знаком минус

                cost = (durationInMinutes - remainingBonusPeriod)*tariffType.getBONUS_PERIOD_RATE() //часть звонка по цене бонусного периода
                        + remainingBonusPeriod * tariffType.getPRICE_RATE();  // остаток звонка вне бонусного периода по стандартной цене
            } else {
                cost = durationInMinutes * tariffType.getPRICE_RATE();
            }
        }
    }

    public int processTime(StringBuilder time) {
        //20230725 141448
        int hoursInSeconds = Integer.parseInt(time.substring(8, 10)) * 60 * 60;
        int minutesInSeconds = Integer.parseInt(time.substring(10, 12)) * 60;
        int seconds = Integer.parseInt(time.substring(12, 14));

        time.insert(4,'-');
        time.insert(7,'-');
        time.insert(10,' ');
        time.insert(13,':');
        time.insert(16,':');

        return  hoursInSeconds + minutesInSeconds + seconds;
    }

    @Override
    public String toString() {
        //TODO: написать правильный вывод информации о звонке
        return "|" + getCallType() + " | " + getStartTime() + " | " + getEndTime() + " | " + getDuration() + " | " + getCost(true) + "|";
    }

    public String getCallType() {
        return callType;
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public String getEndTime() {
        return endTime.toString();
    }

    public String getDuration() {
        DecimalFormat dF = new DecimalFormat( "00" );

        String formattedDuration = String.valueOf(dF.format(duration/3600)) + ":" +
                                    String.valueOf(dF.format(duration%3600/60)) + ":" +
                                    String.valueOf(dF.format(duration%3600%60));
        return formattedDuration;
    }
    public double  getCost() {
        return cost;
    }

    public String  getCost(boolean isStringFormat) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat dF = new DecimalFormat( "00.00" );
        dF.setDecimalFormatSymbols(dfs);
        return  dF.format(cost);
    }
}

package call_report_driver;

public enum TariffType {
    UNLIMITED("06", 1, 300, 0),
    MINUTE_BY_MINUTE("03", 1.5, 0, 0),
    REGULAR("11", 1.5, 100, 0.5);

    TariffType(String tariffCode, double priceRate, int bonusPeriod, double bonusPeriodRate) {
        this.TARIFF_CODE = tariffCode;
        this.PRICE_RATE = priceRate;
        this.bonusPeriod = bonusPeriod;
        this.BONUS_PERIOD_RATE = bonusPeriodRate;
    }
    private final String TARIFF_CODE;
    private final double PRICE_RATE;
    private final double BONUS_PERIOD_RATE;
    private  int bonusPeriod;

    public int useBonusPeriod(int callDuration) {
        if(bonusPeriod > 0) {
            bonusPeriod -= callDuration;
        } else if(bonusPeriod < 0) {
            bonusPeriod  = 0;
        }
        return bonusPeriod; // если остаток бонусного периода меньше, чем длительность звонка,
                            // то метод вернет количество минут не попавших в бонусный период со знаком минус (-5)
    }

    public double getPRICE_RATE() {
        return PRICE_RATE;
    }

    public double getBONUS_PERIOD_RATE() {
        return BONUS_PERIOD_RATE;
    }

    @Override
    public String toString() {
        return TARIFF_CODE;
    }
}

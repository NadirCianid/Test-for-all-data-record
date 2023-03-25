package call_report_driver;

public enum TariffType {
    UNLIMITED("06", 1, 300, 0),
    MINUTE_BY_MINUTE("03", 1.5, 0, 0),
    REGULAR("11", 1.5, 100, 0.5);

    TariffType(String tariffCode, double priceRate, int bonusPeriod, double bonusPeriodRate) {
        this.TARIFF_CODE = tariffCode;
        this.PRICE_RATE = priceRate;
        this.BONUS_PERIOD = bonusPeriod;
        this.BONUS_PERIOD_RATE = bonusPeriodRate;
    }
    private final String TARIFF_CODE;
    private final double PRICE_RATE;
    private final double BONUS_PERIOD_RATE;
    private final int BONUS_PERIOD;

    public double getPRICE_RATE() {
        return PRICE_RATE;
    }

    public double getBONUS_PERIOD_RATE() {
        return BONUS_PERIOD_RATE;
    }

    public String getTARIFF_CODE() {
        return TARIFF_CODE;
    }

    @Override
    public String toString() {
        return TARIFF_CODE;
    }

    public int getBONUS_PERIOD() {
        return BONUS_PERIOD;
    }
}

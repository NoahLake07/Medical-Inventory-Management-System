package mims.data;

public class Date implements Comparable {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * Returns whether this date is sooner than date2.
     * @return -1 if this date is sooner than date2.
     * @returns 0 if this date is the same as date2.
     * @returns 1 if this date is later than date2, which means date2 is sooner.
     * returns -2 if an error occurs.
     */
    @Override
    public int compareTo(Object date2) {
        Date o1 = (Date) date2;
        return -2; //todo finish this
    }
}

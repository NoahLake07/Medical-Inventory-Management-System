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
    public int compareTo(Object obj) {
        if (obj instanceof Date) {
            Date date2 = (Date) obj;

            // Compare year first
            if (this.year < date2.year) {
                return -1;
            } else if (this.year > date2.year) {
                return 1;
            }

            // If years are the same, compare months
            if (this.month < date2.month) {
                return -1;
            } else if (this.month > date2.month) {
                return 1;
            }

            // If months are also the same, compare days
            if (this.day < date2.day) {
                return -1;
            } else if (this.day > date2.day) {
                return 1;
            }

            // All fields are equal
            return 0;
        } else {
            // Typically, you would throw an exception for comparing different types
            throw new IllegalArgumentException("Object must be of type Date");
        }
    }

}

/*  Java Data Analysis
 *  John R. Hubbard
 *  Sep 13, 2017
 */

package ch02.mergefile;

import java.util.Scanner;

class Country {
    protected String name;
    protected int population;

    /*  Constructs a new Country object from the next line being scanned.
        If there are no more lines, the new object's fields are left null.
    */
    public Country(Scanner in) {
        if (in.hasNextLine()) {
            this.name = in.next();
            this.population = in.nextInt();
        }
    }

    public Country(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public boolean isNull() {
        return this.name == null;
    }

    public int compareTo(Object object) {
        Country that = (Country) object;
        return this.population - that.population;
    }

    @Override
    public String toString() {
        return String.format("%-10s%,12d",
                name, population);
    }
}

package parserCSV;

/**
 * Created by Michal on 2015-11-30.
 */
public class Row {
    private String pesel;
    private String name;
    private String surname;
    private String email;
    private String country;
    private String card;

    public Row(String pesel, String name, String surname, String email, String country, String card) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.country = country;
        this.card = card;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", pesel, name, surname, email, country, card);
    }

    public String getPesel(){
        return pesel;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){
        return email;
    }

    public String getCountry(){
        return country;
    }

    public String getCard(){
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        return !(pesel != null ? !pesel.equals(row.pesel) : row.pesel != null);
    }

    @Override
    public int hashCode() {
        return pesel != null ? pesel.hashCode() : 0;
    }
}

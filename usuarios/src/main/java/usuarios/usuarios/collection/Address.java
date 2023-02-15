package usuarios.usuarios.collection;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")//coleccion
public class Address {

    private String address1;//atributos
    private String address2;
    private String city;

    public Address(String address1, String address2, String city) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

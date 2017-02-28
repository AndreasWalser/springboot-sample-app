package de.codecentric.springbootsample;

import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;

@NamedQueries(value = {
        @NamedQuery(name="DBPersons",query="select o from DBPerson o")
})

@Entity
@Table(name = "person")
public class DBPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        // Neu eingeführt weil es sonst nicht funktioner wenn das Object ein Proxy ist
        o = getImplementation(o);
        if (!(o instanceof DBPerson)) return false;
        if (!(this.getClass().isInstance(o))) return false;

        DBPerson that = (DBPerson) o;

        // If you get the following exception, you're likely trying to add an entity
        // created using the new operator to a collection
        // Use the generate() method of the entity's respective DAO instead to
        // generate an entity with a valid id
        //if (getId() == null)
        //    throw new IllegalStateException("id not set; use generation instead of creation to obtain an entity with a valid id");

        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);

    }

    @Override
    public int hashCode() {

        // If you get the following exception, you're likely trying to add an entity
        // created using the new operator to a collection
        // Use the generate() method of the entity's respective DAO instead to
        // generate an entity with a valid id
        //if (getId() == null)
        //    throw new IllegalStateException("id not set; use generation instead of creation to obtain an entity with a valid id");

        int result;
        result = (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    public static <T> T getImplementation(T obj){
        // Neu eingeführt weil es sonst nicht funktionert wenn das Object ein Proxy ist
        if (obj != null && obj instanceof HibernateProxy) {
            obj = (T)((HibernateProxy) obj).getHibernateLazyInitializer().getImplementation();
        }
        return obj;
    }
}

package api.commerce.express.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ronald
 */
@Entity
public class Customer implements IUser, Serializable {

    private static final long SVERSION_UID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String userID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String gender;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AccessDetail accessDetail;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_ID")
    private List<Address> addresses;
    @Embedded
    private Contact contact;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_ID")
    private List<Order> orders;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private WishList wishList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public AccessDetail getAccessDetails() {
        return accessDetail;
    }

    @Override
    public void setAccessDetails(AccessDetail accessDetail) {
        this.accessDetail = accessDetail;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public void setUserID(String customerNumber) {
        this.userID = customerNumber;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer other)) {
            return false;
        }
        return ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.store.feed.domain.Customer[ id=" + id + " ]";
    }
}

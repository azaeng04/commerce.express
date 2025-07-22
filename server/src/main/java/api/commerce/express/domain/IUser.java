package api.commerce.express.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ronald
 */
public interface IUser extends Serializable {

    List<Address> getAddresses();

    void setAddresses(List<Address> addresses);

    Contact getContact();

    void setContact(Contact contact);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getMiddleName();

    void setMiddleName(String middleName);

    String getDateOfBirth();

    void setDateOfBirth(String dateOfBirth);

    String getGender();

    void setGender(String gender);

    String getUserID();

    void setUserID(String userID);

    AccessDetail getAccessDetails();

    void setAccessDetails(AccessDetail user);
}
